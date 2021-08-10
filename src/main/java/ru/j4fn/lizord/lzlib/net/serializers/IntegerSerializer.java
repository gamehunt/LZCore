package ru.j4fn.lizord.lzlib.net.serializers;

import io.netty.buffer.ByteBuf;
import ru.j4fn.lizord.lzlib.net.FieldSerializer;

public class IntegerSerializer extends FieldSerializer<Integer> {

    @Override
    public ByteBuf serialize(Integer value, ByteBuf buff) {
        buff.writeInt(value);
        return buff;
    }

    @Override
    public Integer deserialize(ByteBuf buff) {
        return buff.readInt();
    }
}
