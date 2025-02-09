// 
// Decompiled by Procyon v0.6.0
// 

package de.paxii.clarinet.util.reflection;

import java.util.Iterator;
import java.util.List;
import java.util.Enumeration;
import java.nio.file.Path;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.Collection;
import java.net.URL;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.net.URISyntaxException;
import java.io.IOException;
import java.util.stream.Stream;

public class ReflectionHelper
{
    public static <T> Class<? extends T>[] getClassesInPackageBySuperType(final String packageName, final Class<T> superClass) throws ClassNotFoundException, IOException, URISyntaxException {
        return Stream.of((Class[])getClasses(packageName)).filter(foundClass -> foundClass.getSuperclass().equals(superClass)).toArray(Class[]::new);
    }
    
    public static Class[] getClasses(final String packageName) throws ClassNotFoundException, IOException, URISyntaxException {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        final URL url = ReflectionHelper.class.getProtectionDomain().getCodeSource().getLocation();
        final Path dir = Paths.get(url.toURI());
        final ArrayList<Class> classes = new ArrayList<Class>();
        if (Files.isDirectory(dir, new LinkOption[0])) {
            final String path = packageName.replace('.', '/');
            final Enumeration<URL> resources = classLoader.getResources(path);
            final List<File> dirs = new ArrayList<File>();
            while (resources.hasMoreElements()) {
                final URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            for (final File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        }
        else {
            try {
                final JarFile jarFile = new JarFile(dir.toFile());
                final Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    final JarEntry entry = entries.nextElement();
                    final String name = entry.getName();
                    if (name.endsWith(".class")) {
                        String className = name.replaceAll("/", ".");
                        className = className.substring(0, className.length() - 6);
                        if (!className.contains(packageName)) {
                            continue;
                        }
                        final Class<?> clazz = classLoader.loadClass(className);
                        classes.add(clazz);
                    }
                }
            }
            catch (final Throwable exception) {
                exception.printStackTrace();
            }
        }
        return classes.toArray(new Class[classes.size()]);
    }
    
    private static List<Class> findClasses(final File directory, final String packageName) throws ClassNotFoundException {
        final List<Class> classes = new ArrayList<Class>();
        final File[] files;
        if (!directory.exists() || (files = directory.listFiles()) == null) {
            return classes;
        }
        for (final File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            }
            else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
