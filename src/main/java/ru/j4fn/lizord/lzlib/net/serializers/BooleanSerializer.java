package ru.j4fn.lizord.lzlib.net.serializers;

import io.netty.buffer.ByteBuf;
import ru.j4fn.lizord.lzlib.net.FieldSerializer;

public class BooleanSerializer extends FieldSerializer<Boolean> {
    @Override
    public ByteBuf serialize(Boolean value, ByteBuf buff) {
        buff.writeBoolean(value);
        return buff;
    }

    @Override
    public Boolean deserialize(ByteBuf buff) {
        return buff.readBoolean();
    }
}
