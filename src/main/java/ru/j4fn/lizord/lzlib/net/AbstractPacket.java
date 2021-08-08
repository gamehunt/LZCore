package ru.j4fn.lizord.lzlib.net;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

import java.lang.reflect.Field;

public class AbstractPacket implements IMessage {



    @Override
    public void fromBytes(ByteBuf buf) {
        for(Field f : getClass().getDeclaredFields()){
            if(f.isAnnotationPresent(Serialize.class)){
                try {
                    f.set(this, FieldSerializer.getSerializer(f.getClass()).deserialize(buf));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for(Field f : getClass().getDeclaredFields()){
            if(f.isAnnotationPresent(Serialize.class)){
                FieldSerializer.getSerializer(f.getClass()).serialize(getClass(), buf);
            }
        }
    }
}
