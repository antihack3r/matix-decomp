// 
// Decompiled by Procyon v0.6.0
// 

package net.minecraft.advancements.critereon;

import java.util.Iterator;
import java.util.List;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.common.collect.Maps;
import net.minecraft.advancements.PlayerAdvancements;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.advancements.ICriterionTrigger;

public class EffectsChangedTrigger implements ICriterionTrigger<Instance>
{
    private static final ResourceLocation ID;
    private final Map<PlayerAdvancements, Listeners> listeners;
    
    public EffectsChangedTrigger() {
        this.listeners = Maps.newHashMap();
    }
    
    @Override
    public ResourceLocation getId() {
        return EffectsChangedTrigger.ID;
    }
    
    @Override
    public void addListener(final PlayerAdvancements playerAdvancementsIn, final Listener<Instance> listener) {
        Listeners effectschangedtrigger$listeners = this.listeners.get(playerAdvancementsIn);
        if (effectschangedtrigger$listeners == null) {
            effectschangedtrigger$listeners = new Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, effectschangedtrigger$listeners);
        }
        effectschangedtrigger$listeners.add(listener);
    }
    
    @Override
    public void removeListener(final PlayerAdvancements playerAdvancementsIn, final Listener<Instance> listener) {
        final Listeners effectschangedtrigger$listeners = this.listeners.get(playerAdvancementsIn);
        if (effectschangedtrigger$listeners != null) {
            effectschangedtrigger$listeners.remove(listener);
            if (effectschangedtrigger$listeners.isEmpty()) {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }
    
    @Override
    public void removeAllListeners(final PlayerAdvancements playerAdvancementsIn) {
        this.listeners.remove(playerAdvancementsIn);
    }
    
    @Override
    public Instance deserializeInstance(final JsonObject json, final JsonDeserializationContext context) {
        final MobEffectsPredicate mobeffectspredicate = MobEffectsPredicate.deserialize(json.get("effects"));
        return new Instance(mobeffectspredicate);
    }
    
    public void trigger(final EntityPlayerMP player) {
        final Listeners effectschangedtrigger$listeners = this.listeners.get(player.getAdvancements());
        if (effectschangedtrigger$listeners != null) {
            effectschangedtrigger$listeners.trigger(player);
        }
    }
    
    static {
        ID = new ResourceLocation("effects_changed");
    }
    
    public static class Instance extends AbstractCriterionInstance
    {
        private final MobEffectsPredicate effects;
        
        public Instance(final MobEffectsPredicate effects) {
            super(EffectsChangedTrigger.ID);
            this.effects = effects;
        }
        
        public boolean test(final EntityPlayerMP player) {
            return this.effects.test(player);
        }
    }
    
    static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        private final Set<Listener<Instance>> listeners;
        
        public Listeners(final PlayerAdvancements playerAdvancementsIn) {
            this.listeners = Sets.newHashSet();
            this.playerAdvancements = playerAdvancementsIn;
        }
        
        public boolean isEmpty() {
            return this.listeners.isEmpty();
        }
        
        public void add(final Listener<Instance> listener) {
            this.listeners.add(listener);
        }
        
        public void remove(final Listener<Instance> listener) {
            this.listeners.remove(listener);
        }
        
        public void trigger(final EntityPlayerMP player) {
            List<Listener<Instance>> list = null;
            for (final Listener<Instance> listener : this.listeners) {
                if (listener.getCriterionInstance().test(player)) {
                    if (list == null) {
                        list = Lists.newArrayList();
                    }
                    list.add(listener);
                }
            }
            if (list != null) {
                for (final Listener<Instance> listener2 : list) {
                    listener2.grantCriterion(this.playerAdvancements);
                }
            }
        }
    }
}
