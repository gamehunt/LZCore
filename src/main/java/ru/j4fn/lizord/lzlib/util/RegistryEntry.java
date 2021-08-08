package ru.j4fn.lizord.lzlib.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) //Вешаем на класс
@Retention(RetentionPolicy.RUNTIME) // Видна только в рантайме, на этапе компиляции ее не видно
public @interface RegistryEntry
{

}
