package gamehunt.lzlib.blocks;

import gamehunt.lzlib.LZLib;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.GameData;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;

public class AbstractBlock extends Block {
    public AbstractBlock(String registryName, Material materialIn){
        super(materialIn);
        this.setRegistryName(registryName);
        //Такой костыль нужен для того, чтоб фордж не ругался на нестандартный префикс
        try {
            FieldUtils.writeField(this, "registryName", GameData.checkPrefix(registryName, false), true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setUnlocalizedName(registryName);
        BlockRegistry.addInstance(this);
    }

    public AbstractBlock(String registryName, String modid, Material materialIn){
        this(modid+":"+registryName, materialIn);
    }

    @SideOnly(Side.CLIENT)
    public void registerModel(){
        final ResourceLocation regName = getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        final Item item = Item.getItemFromBlock(this);
        ModelBakery.registerItemVariants(item, mrl);
        ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
    }
}
