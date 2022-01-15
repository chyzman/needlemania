package com.chyzman.needlemania;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

public class NeedleDamageSource extends DamageSource {
    private boolean isintentional = true;

    public NeedleDamageSource() {
        super("player");
        this.setBypassesArmor();
    }

    @Override
    public Text getDeathMessage(LivingEntity entity) {
        LivingEntity livingEntity = entity.getPrimeAdversary();
        String baseKey = "death.attack.needle";
        String intentionalkey = baseKey + ".intentional";
        if (isintentional) {
            return new TranslatableText(baseKey, entity.getDisplayName(), entity.getMainHandStack());
        } else {
            assert livingEntity != null;
            return new TranslatableText(intentionalkey, entity.getDisplayName(), livingEntity.getDisplayName(), entity.getMainHandStack());
        }
    }

    public DamageSource setIntentional() {
        isintentional = false;
        return this;
    }
}