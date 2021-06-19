package com.LockOriginalMods.refinedforage.core.init;

import net.minecraft.item.Food;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodInit {

    public static final Food WILDBERRIES = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.REGENERATION, 00, 0), 0f)
            .fastToEat()
            .hunger(2)
            .saturation(0.2f)
            .setAlwaysEdible()
            .build();

    public static final Food GOLDEN_CHERRY = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.REGENERATION, 20, 2), 0.5f)
            .fastToEat()
            .hunger(5)
            .saturation(0.6f)
            .setAlwaysEdible()
            .build();

    public static final Food CHERRY = new Food.Builder()
            .effect(() -> new EffectInstance(Effects.REGENERATION, 00, 0), 0f)
            .fastToEat()
            .hunger(2)
            .saturation(0.2f)
            .setAlwaysEdible()
            .build();

}
