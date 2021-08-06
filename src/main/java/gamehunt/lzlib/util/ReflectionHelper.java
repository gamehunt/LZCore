package gamehunt.lzlib.util;

import gamehunt.lzlib.LZLib;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ReflectionHelper {
    private static Reflections reflectionsInstance;
    private static HashSet<URL> reflectionPrefixes = new HashSet<>();

    public static void  addPrefix(String p){
        reflectionPrefixes.addAll(ClasspathHelper.forPackage(p));
    }

    public static void init(){
        reflectionsInstance = new Reflections(new ConfigurationBuilder().setUrls(reflectionPrefixes).setScanners(new TypeAnnotationsScanner(), new SubTypesScanner()));
        Set<Class<?>> toPreconstruct = findAnnotatedClasses(RegistryEntry.class);
        for(Class<?> clazz : toPreconstruct){
            LZLib.instance.getLogger().info("Preconstructing class: "+clazz.getSimpleName());
            try {
                clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static Set<Class<?>> findAnnotatedClasses(Class<? extends Annotation> annotation){
        return reflectionsInstance.getTypesAnnotatedWith(RegistryEntry.class);
    }
}
