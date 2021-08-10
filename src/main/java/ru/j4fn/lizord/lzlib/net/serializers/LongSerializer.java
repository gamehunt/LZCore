package ru.j4fn.lizord.lzlib.net.serializers;

import io.netty.buffer.ByteBuf;
import ru.j4fn.lizord.lzlib.net.FieldSerializer;

public class LongSerializer extends FieldSerializer<Long> {

    @Override
    public ByteBuf serialize(Long value, ByteBuf buff) {
        buff.writeLong(value);
        return buff;
    }

    @Override
    public Long deserialize(ByteBuf buff) {
        return buff.readLong();
    }
}
