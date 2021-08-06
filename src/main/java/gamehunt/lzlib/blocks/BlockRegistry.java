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


}
