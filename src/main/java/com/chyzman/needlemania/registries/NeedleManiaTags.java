package com.chyzman.needlemania.registries;

import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class NeedleManiaTags {
    public static final Tag<Block> NEEDLEABLE = TagFactory.BLOCK.create(new Identifier("needlemania", "needle_bearing"));
    public static final Tag<Block> SPLINTERABLE = TagFactory.BLOCK.create(new Identifier("needlemania", "splinter_bearing"));
}
