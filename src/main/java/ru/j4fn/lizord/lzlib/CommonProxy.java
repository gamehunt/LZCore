package ru.j4fn.lizord.lzlib;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import ru.j4fn.lizord.lzlib.net.FieldSerializer;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e){

    }

    public void init(FMLInitializationEvent e){
        FieldSerializer.Init();
    }

    public void postInit(FMLPostInitializationEvent e){

    }
}
