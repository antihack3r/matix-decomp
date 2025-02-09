// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.module.external;

import java.net.URLClassLoader;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.List;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ServiceLoader;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import de.paxii.clarinet.module.Module;
import de.paxii.clarinet.Wrapper;
import de.paxii.clarinet.util.module.store.ModuleStore;
import java.io.File;

public class ExternalModuleLoader
{
    private final File moduleFolder;
    
    public ExternalModuleLoader() {
        (this.moduleFolder = ModuleStore.getModuleFolder()).mkdirs();
    }
    
    public void loadModules() {
        this.loadModules(null);
    }
    
    public void loadModules(final Runnable callback) {
        new Thread(() -> {
            try {
                final Module[] loadExternalModules;
                final Module[] externalModules = loadExternalModules = this.loadExternalModules(this.getModuleJars());
                int i = 0;
                for (int length = loadExternalModules.length; i < length; ++i) {
                    final Module externalModule = loadExternalModules[i];
                    externalModule.setPlugin(true);
                    Wrapper.getModuleManager().addModule(externalModule);
                }
            }
            catch (final Exception x) {
                x.printStackTrace();
            }
            finally {
                if (callback != null) {
                    callback.run();
                }
            }
        }).start();
    }
    
    private File[] getModuleJars() {
        final ArrayList<File> moduleJars = new ArrayList<File>(Arrays.asList(this.moduleFolder.listFiles(f -> f.getName().endsWith(".jar"))));
        final ArrayList<File> ignoredModules = new ArrayList<File>();
        return moduleJars.stream().filter(moduleJar -> {
            if (!ignoredModules.contains(moduleJar)) {
                moduleJars.iterator();
                final Iterator iterator;
                while (iterator.hasNext()) {
                    final File otherModuleJar = iterator.next();
                    if (!moduleJar.getName().equals(otherModuleJar.getName())) {
                        if (ignoredModules.contains(otherModuleJar)) {
                            continue;
                        }
                        else {
                            final String[] moduleJarName = moduleJar.getName().split("-");
                            final String[] otherModuleJarName = otherModuleJar.getName().split("-");
                            if (moduleJarName[0].equals(otherModuleJarName[0])) {
                                ignoredModules.add(moduleJar);
                                ModuleStore.getModulesToDelete().add(moduleJar.getName().replace(".jar", ""));
                                return false;
                            }
                            else {
                                continue;
                            }
                        }
                    }
                }
            }
            return true;
        }).toArray(File[]::new);
    }
    
    private Module[] loadExternalModules(final File[] moduleJars) {
        final ArrayList<Module> moduleList = new ArrayList<Module>();
        final Iterator<Module> it = ServiceLoader.load(Module.class, this.getPluginClassLoader(moduleJars)).iterator();
        while (it.hasNext()) {
            try {
                moduleList.add(it.next());
            }
            catch (final Exception x) {
                x.printStackTrace();
            }
        }
        return moduleList.toArray(new Module[moduleList.size()]);
    }
    
    private ClassLoader getPluginClassLoader(final File[] jarFiles) {
        final Function<File, URL> toUrl = (Function<File, URL>)(file -> {
            try {
                return file.toURI().toURL();
            }
            catch (final MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        });
        return URLClassLoader.newInstance(Arrays.stream(jarFiles).map((Function<? super File, ?>)toUrl).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()).toArray(new URL[jarFiles.length]));
    }
}
