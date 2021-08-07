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
    private Reflections reflectionsInstance;
    private HashSet<URL> reflectionPrefixes = new HashSet<>();

    protected void addPrefix(String p){
        reflectionPrefixes.addAll(ClasspathHelper.forPackage(p));
    }

    public ReflectionHelper(String... prefixes){
        for(String p : prefixes){
            addPrefix(p);
        }
        reflectionsInstance = new Reflections(new ConfigurationBuilder().setUrls(reflectionPrefixes).setScanners(new TypeAnnotationsScanner(), new SubTypesScanner()));

    }

    public void constructClasses(){
        Set<Class<?>> toPreconstruct = findAnnotatedClasses(RegistryEntry.class);
        if(toPreconstruct != null && toPreconstruct.size() > 0) {
            for (Class<?> clazz : toPreconstruct) {
                try {
                    LZLib.instance.getLogger().info("Preconstructing class: " + clazz.getSimpleName());
                    clazz.newInstance();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{
            LZLib.instance.getLogger().info("Nothing to preconstruct...");
        }
    }

    protected Set<Class<?>> findAnnotatedClasses(Class<? extends Annotation> annotation){
        return reflectionsInstance.getTypesAnnotatedWith(RegistryEntry.class);
    }
}
