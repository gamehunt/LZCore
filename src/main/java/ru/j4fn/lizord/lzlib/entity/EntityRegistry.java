package ru.j4fn.lizord.lzlib.entity;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import ru.j4fn.lizord.lzlib.Constants;

import java.util.HashMap;
import java.util.HashSet;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class EntityRegistry {
    private static HashMap<String, HashSet<IAbstractEntity>> instances = new HashMap<>();

    public static void addInstance(IAbstractEntity b){
        if(instances.containsKey(b.getEntry().getRegistryName().getResourceDomain())) {
            instances.get(b.getEntry().getRegistryName().getResourceDomain()).add(b);
        }else{
            HashSet<IAbstractEntity> set = new HashSet<>();
            set.add(b);
            instances.put(b.getEntry().getRegistryName().getResourceDomain(), set);
        }
    }

    public static HashSet<IAbstractEntity> getInstances(String modid){
        HashSet<IAbstractEntity> inst = instances.get(modid);
        return inst == null ? new HashSet<IAbstractEntity>(){} : inst;
    }

    public static void registerModEntities(IForgeRegistry<EntityEntry> registry, String modid){
        for(IAbstractEntity e : getInstances(modid)){
            registry.register(e.getEntry());
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerModEntityModels(String modid){
        for(IAbstractEntity e : getInstances(modid)){
            RenderingRegistry.registerEntityRenderingHandler(e.getEntityClass(), e.getRenderFactory());
        }
    }
}
