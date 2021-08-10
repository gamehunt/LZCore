package ru.j4fn.lizord.lzlib.net;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import ru.j4fn.lizord.lzlib.LZLib;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.TreeMap;

public class AbstractPacket<T extends AbstractPacket> implements IMessage {

    private static class FieldComparator implements Comparator<Field>{
        @Override
        public int compare(Field o1, Field o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    private TreeMap<Field, FieldSerializer> serializableFields = null;
    private T castedInstance;

    public AbstractPacket(){
        serializableFields = new TreeMap<>(new FieldComparator());
        castedInstance = (T)this;
        for(Field f : castedInstance.getClass().getDeclaredFields()) {
            FieldSerializer sr = FieldSerializer.getSerializer(f.getType());
            if (f.isAnnotationPresent(Serialize.class)) {
                if(sr != null) {
                    serializableFields.put(f, sr);
                }else{
                    LZLib.instance.getLogger().warn("Can't find serializer for type: "+f.getType().getName());
                }
            }
        }
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        for (Field f : serializableFields.descendingKeySet()) {
            try {
                f.set(castedInstance, serializableFields.get(f).deserialize(buf));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        for (Field f : serializableFields.keySet()) {
            try {
                serializableFields.get(f).serialize(f.get(castedInstance), buf);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
