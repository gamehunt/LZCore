package ru.j4fn.lizord.lzlib.net.serializers;

import io.netty.buffer.ByteBuf;
import ru.j4fn.lizord.lzlib.net.FieldSerializer;

public class FloatSerializer extends FieldSerializer<Float> {
    @Override
    public ByteBuf serialize(Float value, ByteBuf buff) {
        buff.writeFloat(value);
        return buff;
    }

    @Override
    public Float deserialize(ByteBuf buff) {
        return buff.readFloat();
    }
}
