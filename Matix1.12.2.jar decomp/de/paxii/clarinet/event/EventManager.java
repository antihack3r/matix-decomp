// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.event;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import de.paxii.clarinet.event.events.type.EventCancellable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import de.paxii.clarinet.event.events.game.KeyPressedEvent;
import java.util.List;
import de.paxii.clarinet.event.events.Event;
import java.util.Map;

public class EventManager
{
    private static final Map<Class<? extends Event>, List<MethodData>> REGISTRY_MAP;
    private static KeyPressedEvent lastCalledEvent;
    private static long lastCalledMS;
    
    private static void cleanMap(final boolean onlyEmptyEntries) {
        final Iterator<Map.Entry<Class<? extends Event>, List<MethodData>>> mapIterator = EventManager.REGISTRY_MAP.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (!onlyEmptyEntries || ((List)mapIterator.next().getValue()).isEmpty()) {
                mapIterator.remove();
            }
        }
    }
    
    private static boolean isMethodBad(final Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(EventHandler.class);
    }
    
    private static boolean isMethodBad(final Method method, final Class<? extends Event> eventClass) {
        return isMethodBad(method) || !method.getParameterTypes()[0].equals(eventClass);
    }
    
    public static <T extends Event> T call(final T event) {
        if (event instanceof KeyPressedEvent) {
            final KeyPressedEvent keyPressedEvent = (KeyPressedEvent)event;
            if (EventManager.lastCalledMS != 0L) {
                if (EventManager.lastCalledMS + 10L >= System.currentTimeMillis() && keyPressedEvent.getKey() == EventManager.lastCalledEvent.getKey()) {
                    return event;
                }
            }
            else {
                EventManager.lastCalledEvent = keyPressedEvent;
                EventManager.lastCalledMS = System.currentTimeMillis();
            }
        }
        final List<MethodData> dataList = EventManager.REGISTRY_MAP.get(event.getClass());
        if (dataList != null) {
            if (event instanceof EventCancellable) {
                final EventCancellable cancellable = (EventCancellable)event;
                for (final MethodData data : dataList) {
                    invoke(data, event);
                    if (cancellable.isCancelled()) {
                        break;
                    }
                }
            }
            else {
                for (final MethodData data2 : dataList) {
                    invoke(data2, event);
                }
            }
        }
        return event;
    }
    
    private static void invoke(final MethodData data, final Event argument) {
        try {
            data.getTarget().invoke(data.getSource(), argument);
        }
        catch (final ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
    
    public void register(final Object object) {
        for (final Method method : object.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method)) {
                this.register(object, method);
            }
        }
    }
    
    public void register(final Object object, final Class<? extends Event> eventClass) {
        for (final Method method : object.getClass().getDeclaredMethods()) {
            if (!isMethodBad(method, eventClass)) {
                this.register(object, method);
            }
        }
    }
    
    public void unregister(final Object object) {
        for (final List<MethodData> dataList : EventManager.REGISTRY_MAP.values()) {
            dataList.stream().filter(data -> data.getSource().equals(object)).forEach(dataList::remove);
        }
        cleanMap(true);
    }
    
    public void unregister(final Object object, final Class<? extends Event> eventClass) {
        if (EventManager.REGISTRY_MAP.containsKey(eventClass)) {
            EventManager.REGISTRY_MAP.get(eventClass).stream().filter(data -> data.getSource().equals(object)).forEach((List)EventManager.REGISTRY_MAP.get(eventClass)::remove);
            cleanMap(true);
        }
    }
    
    private void register(final Object object, final Method method) {
        final Class<? extends Event> indexClass = (Class<? extends Event>)method.getParameterTypes()[0];
        final MethodData methodData = new MethodData(object, method, method.getAnnotation(EventHandler.class).priority());
        methodData.getTarget().setAccessible(true);
        if (EventManager.REGISTRY_MAP.containsKey(indexClass)) {
            if (!EventManager.REGISTRY_MAP.get(indexClass).contains(methodData)) {
                EventManager.REGISTRY_MAP.get(indexClass).add(methodData);
                this.sortListValue(indexClass);
            }
        }
        else {
            final CopyOnWriteArrayList<MethodData> methodDataList = new CopyOnWriteArrayList<MethodData>(new MethodData[] { methodData });
            EventManager.REGISTRY_MAP.put(indexClass, methodDataList);
        }
    }
    
    private void sortListValue(final Class<? extends Event> indexClass) {
        final List<MethodData> sortedList = new CopyOnWriteArrayList<MethodData>();
        for (final int priority : EventPriority.getValues()) {
            EventManager.REGISTRY_MAP.get(indexClass).stream().filter(data -> data.getPriority() == priority).forEach(sortedList::add);
        }
        EventManager.REGISTRY_MAP.put(indexClass, sortedList);
    }
    
    static {
        REGISTRY_MAP = new HashMap<Class<? extends Event>, List<MethodData>>();
    }
}
