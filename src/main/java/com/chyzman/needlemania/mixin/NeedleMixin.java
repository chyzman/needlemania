package com.chyzman.needlemania.mixin;

import com.chyzman.needlemania.registries.NeedleManiaItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class NeedleMixin extends LivingEntity {
    protected NeedleMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "attack", at = @At(value = "HEAD"), cancellable = true)
    private void MakeNeedlePoke(Entity target, CallbackInfo ci) {
        if (this.getMainHandStack().isOf(NeedleManiaItems.NEEDLE)) {
            ci.cancel();
        }
    }
}