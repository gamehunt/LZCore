package ru.j4fn.lizord.lzlib.net.serializers;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import ru.j4fn.lizord.lzlib.net.FieldSerializer;

public class StringSerializer extends FieldSerializer<String> {

    @Override
    public ByteBuf serialize(String value, ByteBuf buff) {
        ByteBufUtils.writeUTF8String(buff, value);
        return buff;
    }

    @Override
    public String deserialize(ByteBuf buff) {
        return ByteBufUtils.readUTF8String(buff);
    }
}
