package ru.j4fn.lizord.lzlib.blocks;

import ru.j4fn.lizord.lzlib.Constants;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashMap;
import java.util.HashSet;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class BlockRegistry {
    private static HashMap<String, HashSet<AbstractBlock>> instances = new HashMap<>();

    public static void addInstance(AbstractBlock b){
        if(instances.containsKey(b.getRegistryName().getResourceDomain())) {
            instances.get(b.getRegistryName().getResourceDomain()).add(b);
        }else{
            HashSet<AbstractBlock> set = new HashSet<>();
            set.add(b);
            instances.put(b.getRegistryName().getResourceDomain(), set);
        }
    }

    public static HashSet<AbstractBlock> getInstances(String modid){
        HashSet<AbstractBlock> inst = instances.get(modid);
        return inst == null ? new HashSet<AbstractBlock>(){} : inst;
    }

    public static void registerModBlocks(IForgeRegistry<Block> registry, String modid){
        for(AbstractBlock b : getInstances(modid)) {
            registry.register(b);
            for(IBlockState s : b.getTEStates()) {
                if (b.hasTileEntity(s)) {
                    GameRegistry.registerTileEntity(b.getTileEntityClass(s), b.getRegistryName());
                }
            }
        }
    }

    public static void registerModItemBlocks(IForgeRegistry<Item> registry, String modid){
        for(AbstractBlock b : getInstances(modid)) {
            registry.register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
        }
    }

    public static void registerModBlockModels(String modid){
        for(AbstractBlock b : getInstances(modid)){
            b.registerModel();
        }
    }
}
