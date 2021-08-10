package ru.j4fn.lizord.lzlib.net.serializers;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;
import ru.j4fn.lizord.lzlib.net.FieldSerializer;

public class BlockPosSerializer extends FieldSerializer<BlockPos> {
    @Override
    public ByteBuf serialize(BlockPos value, ByteBuf buff) {
        buff.writeLong(value.toLong());
        return buff;
    }

    @Override
    public BlockPos deserialize(ByteBuf buff) {
        return BlockPos.fromLong(buff.readLong());
    }
}
