package ru.j4fn.lizord.lzlib.items;

import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;
import java.util.HashSet;

@Mod.EventBusSubscriber
public class ItemRegistry {
    private static HashMap<String, HashSet<AbstractItem>> instances = new HashMap<>();

    public static void addInstance(AbstractItem b){
        if(instances.containsKey(b.getRegistryName().getResourceDomain())) {
            instances.get(b.getRegistryName().getResourceDomain()).add(b);
        }else{
            HashSet<AbstractItem> set = new HashSet<>();
            set.add(b);
            instances.put(b.getRegistryName().getResourceDomain(), set);
        }
    }

    public static HashSet<AbstractItem> getInstances(String modid){
        HashSet<AbstractItem> inst = instances.get(modid);
        return inst == null ? new HashSet<AbstractItem>(){} : inst;
    }

    public static void registerModItems(IForgeRegistry<Item> registry, String modid){
        for(AbstractItem b : getInstances(modid)) {
            registry.register(b);
        }
    }

    public static void registerModItemModels(String modid){
        for(AbstractItem b : getInstances(modid)){
            b.registerModel();
            TileEntityItemStackRenderer renderer = b.getSpecialRenderer();
            if(renderer != null){
                b.setTileEntityItemStackRenderer(b.getSpecialRenderer());
            }
        }
    }
}
