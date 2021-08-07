package gamehunt.lzlib.items;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AbstractItem extends Item {
    protected AbstractItem(String registryName){
        this.setRegistryName(registryName);
        this.setUnlocalizedName(registryName);
        ItemRegistry.addInstance(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerModel(){
        final ResourceLocation regName = getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        final Item item = this;
        ModelBakery.registerItemVariants(item, mrl);
        ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
    }
}
