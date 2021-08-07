package gamehunt.lzlib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.HashSet;

public class AbstractBlock extends Block {
    public AbstractBlock(String registryName, Material materialIn){
        super(materialIn);
        this.setRegistryName(registryName);
        this.setUnlocalizedName(registryName);
        BlockRegistry.addInstance(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerModel(){
        final ResourceLocation regName = getRegistryName();
        final ModelResourceLocation mrl = new ModelResourceLocation(regName, "inventory");
        final Item item = Item.getItemFromBlock(this);
        ModelBakery.registerItemVariants(item, mrl);
        ModelLoader.setCustomModelResourceLocation(item, 0, mrl);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        try {
            return getTileEntityClass(state) == null ? null : getTileEntityClass(state).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return getTileEntityClass(state) != null;
    }

    public HashSet<IBlockState> getTEStates(){
        IBlockState s = getDefaultState();
        HashSet<IBlockState> set = new HashSet<>();
        set.add(s);
        return set;
    }

    public Class<? extends AbstractTileEntity> getTileEntityClass(IBlockState s){
        return null;
    }
}
