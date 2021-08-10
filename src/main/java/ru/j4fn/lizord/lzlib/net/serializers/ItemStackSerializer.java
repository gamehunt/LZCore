package ru.j4fn.lizord.lzlib.net.serializers;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import ru.j4fn.lizord.lzlib.net.FieldSerializer;

public class ItemStackSerializer extends FieldSerializer<ItemStack> {
    @Override
    public ByteBuf serialize(ItemStack value, ByteBuf buff) {
        ByteBufUtils.writeItemStack(buff, value);
        return buff;
    }

    @Override
    public ItemStack deserialize(ByteBuf buff) {
        return ByteBufUtils.readItemStack(buff);
    }
}
