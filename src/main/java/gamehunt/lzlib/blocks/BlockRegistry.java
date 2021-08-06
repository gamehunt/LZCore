package gamehunt.lzlib.blocks;

import gamehunt.lzlib.Constants;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.HashSet;

@Mod.EventBusSubscriber(modid = Constants.MODID)
public class BlockRegistry {
    private static HashSet<AbstractBlock> instances = new HashSet<>();

    public static void addInstance(AbstractBlock b){
        instances.add(b);
    }

    public static HashSet<AbstractBlock> getInstances(){
        return instances;
    }

    @SubscribeEvent
    public static void onRegisteringBlocks(RegistryEvent.Register<Block> e){
        for(Block b : instances) {
            e.getRegistry().register(b);
        }
    }

    @SubscribeEvent
    public static void onRegisteringItems(RegistryEvent.Register<Item> e){
        for(Block b : instances) {
            e.getRegistry().register(new ItemBlock(b).setRegistryName(b.getRegistryName()));
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisteringModels(ModelRegistryEvent e){
        for(AbstractBlock b : instances) {
            b.registerModel();
        }
    }
}
