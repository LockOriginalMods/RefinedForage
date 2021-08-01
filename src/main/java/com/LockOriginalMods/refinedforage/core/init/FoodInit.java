package com.LockOriginalMods.refinedforage.core.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodInit {

    public static final Food WILDBERRIES = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.REGENERATION, 00, 0), 0f)
            .fast()
            .nutrition(2)
            .saturationMod(0.2f)
            .alwaysEat()
            .build();

    public static final Food GOLDEN_CHERRY = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.REGENERATION, 20, 2), 0.5f)
            .fast()
            .nutrition(5)
            .saturationMod(0.6f)
            .alwaysEat()
            .build();

    public static final Food CHERRY = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.REGENERATION, 00, 0), 0f)
            .fast()
            .nutrition(2)
            .saturationMod(0.2f)
            .alwaysEat()
            .build();

}
