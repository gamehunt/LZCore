package ru.j4fn.lizord.lzlib.multiblocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.World;

public abstract class AbstractMultiblock {
    public abstract IMultiblockPattern getPattern();

    protected boolean checkForFacing(World w, BlockPos trigger, EnumFacing facing){
        IMultiblockPattern p = getPattern();
        BlockPos leftDownCorner = trigger.offset(EnumFacing.DOWN, p.getTriggerOffset().getY()).offset(facing.rotateY(), p.getTriggerOffset().getX()).offset(facing.getOpposite(), p.getTriggerOffset().getZ());
        for (int y = 0; y < p.getSizeY(); y++){
            for(int x = 0; x < p.getSizeX(); x++){
                for(int z = 0; z < p.getSizeZ(); z++){
                    Block cur = w.getBlockState(leftDownCorner.offset(EnumFacing.UP, y).offset(facing, z).offset(facing.rotateYCCW(), x)).getBlock();
                    if(cur != p.getBlockFor(p.getPattern()[y][z][x])){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean check(World w, BlockPos trigger){
        for(EnumFacing face : EnumFacing.HORIZONTALS){
            if(checkForFacing(w, trigger, face)){
                return true;
            }
        }
        return false;
    }

    public boolean trigger(World w, BlockPos triggerPos, EntityPlayer p){
        if(check(w, triggerPos)){
            return construct(w, triggerPos, p);
        }
        return false;
    }

    public abstract boolean construct(World w, BlockPos triggerPos, EntityPlayer p);
}
