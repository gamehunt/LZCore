package ru.j4fn.lizord.lzlib.entity;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.common.registry.EntityEntry;

public interface IAbstractEntity<T extends Entity> {
    EntityEntry getEntry();
    IRenderFactory<T> getRenderFactory();
    Class<T> getEntityClass();
}
