package ru.j4fn.lizord.lzlib.net;

import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageHandler<T extends AbstractPacket<T>> implements IMessageHandler<T, IMessage> {
    @Override
    public IMessage onMessage(T message, MessageContext ctx) {
        return message.handle(ctx);
    }
}
