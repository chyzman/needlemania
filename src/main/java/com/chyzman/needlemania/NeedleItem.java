package com.chyzman.needlemania;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.PufferfishEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class NeedleItem extends Item {
    public float damage;
    public float reach;
    public int breakchance;
    public int backfirechance;
    public int cooldown;

    public NeedleItem(float Damage, float Reach, int BreakChance, int BackFireChance, int Cooldown, Settings settings) {
        super(settings);
        damage = Damage;
        reach = Reach;
        breakchance = BreakChance;
        backfirechance = BackFireChance;
        cooldown = Cooldown;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (user.isSneaking() && !user.getItemCooldownManager().isCoolingDown(this)) {
            user.getItemCooldownManager().set(this, cooldown);
            int int_random = user.world.random.nextInt();
            if (int_random == 1 && !user.isCreative()) {
                if (!user.world.isClient) {
                    user.sendToolBreakStatus(Hand.MAIN_HAND);
                    user.getMainHandStack().decrement(1);
                }
            }
            user.damage(new NeedleDamageSource(), damage);
            return TypedActionResult.success(user.getMainHandStack());
        }
        return TypedActionResult.pass(user.getMainHandStack());
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity target, Hand hand) {
        if (target.canTakeDamage() && !user.getItemCooldownManager().isCoolingDown(this) && user.distanceTo(target) >= reach) {
            user.getItemCooldownManager().set(this, cooldown);
            int int_random = user.world.random.nextInt(breakchance);
            if (int_random == 1 && !user.isCreative()) {
                if (!user.world.isClient) {
                    user.sendToolBreakStatus(Hand.MAIN_HAND);
                    user.getMainHandStack().decrement(1);
                }
            }
            int int_random2 = user.world.random.nextInt(backfirechance);
            if (int_random2 == 1 && !user.isCreative()) {
                if (!user.world.isClient) {
                    user.damage(new NeedleDamageSource(), damage);
                    if (target instanceof ZombieEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 40, 2));
                    }
                    if (target instanceof ShulkerEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40, 2));
                    }
                    if (target instanceof CaveSpiderEntity || target instanceof PufferfishEntity || target instanceof BeeEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 40, 2));
                    }
                    if (target instanceof WitherEntity || target instanceof WitherSkeletonEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 40, 2));
                    }
                    if (target instanceof DolphinEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 40, 2));
                    }
                    if (target instanceof VillagerEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.HERO_OF_THE_VILLAGE, 40, 2));
                    }
                    if (target instanceof IllagerEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.BAD_OMEN, 40, 2));
                    }
                    if (target instanceof StrayEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40, 0));
                    }
                    if (target instanceof BlazeEntity || target instanceof MagmaCubeEntity) {
                        user.setFireTicks(100);
                    }
                    if (target instanceof GuardianEntity) {
                        user.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 80, 4));
                    }
                }
            }
            target.damage(new NeedleDamageSource().setIntentional(), damage);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
