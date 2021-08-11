package ru.j4fn.lizord.lzlib.net;

import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

import java.util.HashMap;

public class NetworkManager {
    private static HashMap<String, SimpleNetworkWrapper> wrappers = new HashMap<>();

    private static int __discriminator = 0;

    public static SimpleNetworkWrapper getInstance(){
        return getInstance("");
    }

    public static SimpleNetworkWrapper getInstance(String modid){
        if(modid.isEmpty()){
            modid = Loader.instance().activeModContainer().getModId();
        }
        SimpleNetworkWrapper wrapper = wrappers.get(modid);
        if(wrapper == null){
            wrapper = NetworkRegistry.INSTANCE.newSimpleChannel(modid);
            wrappers.put(modid, wrapper);
        }
        return wrapper;
    }

    public static <T extends AbstractPacket<T>> void registerPacket(Class<T> clazz){
        NetPacket annt = clazz.getAnnotation(NetPacket.class);
        MessageHandler mh = new MessageHandler<T>();
        PacketSide side = annt.side();
        SimpleNetworkWrapper wrapper = getInstance();
        if(side == PacketSide.CLIENT || annt.side() == PacketSide.BOTH) {
            wrapper.registerMessage(mh, clazz, ++__discriminator, Side.CLIENT);
        }
        if(side == PacketSide.SERVER || side == PacketSide.BOTH) {
            wrapper.registerMessage(mh, clazz, ++__discriminator, Side.SERVER);
        }
    }
}
