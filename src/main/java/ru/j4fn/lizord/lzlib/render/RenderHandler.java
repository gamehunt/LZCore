package ru.j4fn.lizord.lzlib.render;

import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT)
public class RenderHandler {
    @SubscribeEvent
    public static void onRenderingViewport(RenderWorldLastEvent ev){
        //TODO
        //Minecraft.getMinecraft().entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
    }
}
