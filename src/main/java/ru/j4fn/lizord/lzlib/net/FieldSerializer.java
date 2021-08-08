package ru.j4fn.lizord.lzlib.net;

import io.netty.buffer.ByteBuf;

import java.util.HashMap;

public abstract class FieldSerializer<T> {
    private static HashMap<Class<?>, FieldSerializer> serializers = new HashMap<>();

    public static void Init(){
        //TODO init basic types
    }

    public static void registerSerializer(Class<?> clazz, FieldSerializer serializer){
        serializers.put(clazz, serializer);
    }

    public static FieldSerializer getSerializer(Class<?> type){
        return serializers.get(type);
    }

    abstract ByteBuf serialize(T type, ByteBuf buff);
    abstract T       deserialize(ByteBuf buff);
}
