package ru.j4fn.lizord.lzlib.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import ru.j4fn.lizord.lzlib.net.serializers.*;

import java.util.HashMap;

public abstract class FieldSerializer<T> {
    private static HashMap<Class<?>, FieldSerializer> serializers = new HashMap<>();

    public static void Init(){
        registerSerializer(int.class, new IntegerSerializer());
        registerSerializer(boolean.class, new BooleanSerializer());
        registerSerializer(float.class, new FloatSerializer());
        registerSerializer(long.class, new LongSerializer());
        registerSerializer(Integer.class, new IntegerSerializer());
        registerSerializer(Boolean.class, new BooleanSerializer());
        registerSerializer(Float.class, new FloatSerializer());
        registerSerializer(Long.class, new LongSerializer());
        registerSerializer(String.class, new StringSerializer());
        registerSerializer(BlockPos.class, new BlockPosSerializer());
        registerSerializer(ItemStack.class, new ItemStackSerializer());
    }

    public static void registerSerializer(Class<?> clazz, FieldSerializer serializer){
        serializers.put(clazz, serializer);
    }

    public static FieldSerializer getSerializer(Class<?> type){
        return serializers.get(type);
    }

    public abstract ByteBuf serialize(T value, ByteBuf buff);
    public abstract T       deserialize(ByteBuf buff);
}
