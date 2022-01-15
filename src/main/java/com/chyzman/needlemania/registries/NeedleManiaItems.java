package com.chyzman.needlemania.registries;

import com.chyzman.needlemania.NeedleItem;
import com.chyzman.needlemania.NeedleMania;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

import static com.chyzman.needlemania.NeedleMania.id;

public class NeedleManiaItems {
    private static Item register(String name, Item item) {return Registry.register(Registry.ITEM, id(name), item);}
    public static final Item NEEDLE = register("needle", new NeedleItem(1, 1, 20, 5, 10, new Item.Settings().group(NeedleMania.NEEDLEMANIA).maxCount(16)));
    public static final Item SPLINTER = register("splinter", new NeedleItem(1, 1, 5, 2, 5, new Item.Settings().group(NeedleMania.NEEDLEMANIA).maxCount(64)));
}
