package ru.j4fn.lizord.lzlib.multiblocks;

import net.minecraft.block.Block;
import net.minecraft.util.math.Vec3i;

public interface IMultiblockPattern {
    char[][][] getPattern();
    Block getBlockFor(char c);
    int getSizeX();
    int getSizeY();
    int getSizeZ();
    Vec3i getTriggerOffset();
}
