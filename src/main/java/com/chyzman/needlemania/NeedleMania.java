package com.chyzman.needlemania;

import com.chyzman.needlemania.registries.NeedleManiaItems;
import com.chyzman.needlemania.registries.NeedleManiaTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class NeedleMania implements ModInitializer {
    public static String MODID = "needlemania";
    public static final ItemGroup NEEDLEMANIA = FabricItemGroupBuilder.build(new Identifier("needlemania", "needles"), () -> new ItemStack(NeedleManiaItems.NEEDLE));
    @Override
    public void onInitialize() {
        var items = NeedleManiaItems.class;
        var tags = NeedleManiaTags.class;
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (NeedleManiaTags.NEEDLEABLE.contains(world.getBlockState(hitResult.getBlockPos()).getBlock())) {
                if (player.getMainHandStack().isEmpty()) {
                    int int_random = world.random.nextInt(100);
                    if (int_random == 1) {
                        if (!player.world.isClient) {
                            player.giveItemStack(new ItemStack(NeedleManiaItems.NEEDLE, 1));
                            player.damage(new NeedleDamageSource(), 1);
                        }
                    }
                    return ActionResult.SUCCESS;
                }
                if (player.getMainHandStack().isOf(NeedleManiaItems.NEEDLE)) {
                    player.getMainHandStack().decrement(1);
                    return ActionResult.SUCCESS;
                }
            }
            if (NeedleManiaTags.SPLINTERABLE.contains(world.getBlockState(hitResult.getBlockPos()).getBlock())) {
                if (player.getMainHandStack().isEmpty()) {
                    int int_random = world.random.nextInt(10);
                    if (int_random == 1) {
                        if (!player.world.isClient) {
                            player.giveItemStack(new ItemStack(NeedleManiaItems.SPLINTER, 1));
                            player.damage(new NeedleDamageSource(), 1);
                        }
                    }
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.PASS;
        });
    }

    public static Identifier id(String name) {
        return new Identifier(MODID, name);
    }
}
