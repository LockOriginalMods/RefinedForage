package com.LockOriginalMods.refinedforage;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.Block;
import net.minecraft.command.arguments.BlockStateParser;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;

import static com.LockOriginalMods.refinedforage.RefinedForage.MOD_ID;

public class Config {

    private static final Logger LOGGER = LogManager.getLogger();

    public static final String CATEGORY_GENERAL = "general";
    public static final String CATEGORY_POWER = "power";

    public static final String SUBCATEGORY_INCINERATOR = "incinerator";
    public static final String SUBCATEGORY_PELTIER = "peltier_generator";

    public static final String SUBCATEGORY_FIRSTBLOCK = "firstblock";

    public static ForgeConfigSpec.IntValue FIRSTBLOCK_MAXPOWER;
    public static ForgeConfigSpec.IntValue FIRSTBLOCK_GENERATE;
    public static ForgeConfigSpec.IntValue FIRSTBLOCK_SEND;
    public static ForgeConfigSpec.IntValue FIRSTBLOCK_TICKS;


    public static ForgeConfigSpec.DoubleValue ROTATION_SPEED;



    public static ForgeConfigSpec SERVER_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;


    public static ForgeConfigSpec.IntValue INCINERATOR_MAXPOWER;
    public static ForgeConfigSpec.IntValue INCINERATOR_GENERATE;
    public static ForgeConfigSpec.IntValue INCINERATOR_TRANSFER;
    public static ForgeConfigSpec.IntValue INCINERATOR_COOLDOWN;
    public static ForgeConfigSpec.IntValue PELTIER_MAXPOWER;
    public static ForgeConfigSpec.DoubleValue PELTIER_GENERATE;
    public static ForgeConfigSpec.IntValue PELTIER_TRANSFER;

    private static final ForgeConfigSpec.Builder CFG = new ForgeConfigSpec.Builder();
    private static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec.IntValue TIMER_COOLDOWN;
    public static ForgeConfigSpec.IntValue MAX_STAGE;
    public static ForgeConfigSpec.BooleanValue BREAKABLE_HANDLE;
    public static ForgeConfigSpec.BooleanValue AUTOMATION_ALLOWED;
    static {
        initConfig();
    }

    private static void initConfig() {
        CFG.comment("General settings").push(MOD_ID);
        AUTOMATION_ALLOWED = CFG.comment("\r\nTrue means automation is allowed as normal; false will disable the capabilities so hoppers, cables, pipes will not connect")
                .define("allowAutomation", true);
        BREAKABLE_HANDLE = CFG.comment("\r\nCan the handle break if its used too many times while the input is empty")
                .define("breakableHandle", true);
        TIMER_COOLDOWN = CFG.comment("\r\nHow many ticks must be in between two player interactions; this is the cooldown in between spins at maximum speed (20 ticks in one second)")
                .defineInRange("timerCooldown", 5, 1, 80);
        MAX_STAGE = CFG.comment("\r\nHow many stages (player uses) trigger a recipe. Each stage is one cardinal direction, meaning 4 stages is one full rotation")
                .defineInRange("stagesPerRecipe", 4, 1, 444);
        CFG.pop(); // one pop for every push
        COMMON_CONFIG = CFG.build();
    }

    private static ForgeConfigSpec.ConfigValue<ArrayList<String>> TEMP_VALUES;
    private static final ArrayList<String> DEFAULT_TEMP_VALUES = new ArrayList<>();
    @SuppressWarnings("unchecked")
    private static final Predicate<Object> TEMP_VALUES_VALIDATOR = (array) -> {
        if(array instanceof ArrayList<?>) {
            for (String s : (ArrayList<String>) array) {
                try {
                    Integer.parseInt(s.split("=")[1]);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }
        return false;
    };

    private static final ArrayList<String> INCINERATOR_TEMP_VALUES = new ArrayList<>();
    @SuppressWarnings("unchecked")
    private static final Predicate<Object> INCINERATOR_VALUES_VALIDATOR = (array) -> {
        if(array instanceof ArrayList<?>) {
            for (String s : (ArrayList<String>) array) {
                try {
                    Integer.parseInt(s.split("=")[1]);
                } catch (NumberFormatException e) {
                    return false;
                }
            }
            return true;
        }
        return false;
    };

    static {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        CLIENT_BUILDER.comment("General Settings").push(CATEGORY_GENERAL);
        CLIENT_BUILDER.pop();

        CLIENT_BUILDER.comment("General settings").push(CATEGORY_GENERAL);
        ROTATION_SPEED = CLIENT_BUILDER.comment("Rotation speed of the magic block").defineInRange("rotationSpeed", 100.0, 0.0, 1000000.0);
        CLIENT_BUILDER.pop();



        SERVER_BUILDER.comment("Power Settings").push(CATEGORY_POWER);

        setupInceneratorConfig(SERVER_BUILDER);
        setupPeltierGeneratorConfig(SERVER_BUILDER);
        setupFirstBlockConfig(SERVER_BUILDER, CLIENT_BUILDER);



        SERVER_BUILDER.pop();

        SERVER_CONFIG = SERVER_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();

    }



    private static void setupInceneratorConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Incinerator Settings").push(SUBCATEGORY_INCINERATOR);

        INCINERATOR_MAXPOWER = SERVER_BUILDER.comment("Base capacity")
                .defineInRange("maxPower", 5000, 0, Integer.MAX_VALUE);
        INCINERATOR_GENERATE = SERVER_BUILDER.comment("Power generation per item destroyed")
                .defineInRange("generate", 40, 0, Integer.MAX_VALUE);
        INCINERATOR_TRANSFER = SERVER_BUILDER.comment("Power transfer per tick")
                .defineInRange("transfer", 5, 0, Integer.MAX_VALUE);
        INCINERATOR_COOLDOWN = SERVER_BUILDER.comment("Time to incinerate items in ticks")
                .defineInRange("cooldown", 200, 1, Integer.MAX_VALUE);


        SERVER_BUILDER.pop();
    }

    private static void setupFirstBlockConfig(ForgeConfigSpec.Builder SERVER_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        SERVER_BUILDER.comment("CoalGenerator settings").push(SUBCATEGORY_FIRSTBLOCK);

        FIRSTBLOCK_MAXPOWER = SERVER_BUILDER.comment("Maximum power for the CoalGenerator generator")
                .defineInRange("maxPower", 100000, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_GENERATE = SERVER_BUILDER.comment("Power generation per coal")
                .defineInRange("generate", 30, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_SEND = SERVER_BUILDER.comment("Power generation to send per tick")
                .defineInRange("send", 100, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_TICKS = SERVER_BUILDER.comment("Ticks per coal")
                .defineInRange("ticks", 20, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_GENERATE = SERVER_BUILDER.comment("Power generation per charcoal")
                .defineInRange("generate", 20, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_GENERATE = SERVER_BUILDER.comment("Power generation per coal_block")
                .defineInRange("generate", 50, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_TICKS = SERVER_BUILDER.comment("Ticks per charcoal")
                .defineInRange("ticks", 15, 0, Integer.MAX_VALUE);
        FIRSTBLOCK_TICKS = SERVER_BUILDER.comment("Ticks per coal_block")
                .defineInRange("ticks", 50, 0, Integer.MAX_VALUE);


        SERVER_BUILDER.pop();
    }



    private static void setupPeltierGeneratorConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Peltier Generator Settings").push(SUBCATEGORY_PELTIER);

        PELTIER_MAXPOWER = SERVER_BUILDER.comment("Base capacity")
                .defineInRange("maxPower", 64000, 0, Integer.MAX_VALUE);
        PELTIER_GENERATE = SERVER_BUILDER.comment("Power generation multiplier. Can be decimal")
                .defineInRange("generate", 5, 0, Double.MAX_VALUE);
        PELTIER_TRANSFER = SERVER_BUILDER.comment("Power transfer per tick")
                .defineInRange("transfer", 1000, 0, Integer.MAX_VALUE);

        DEFAULT_TEMP_VALUES.add("minecraft:lava=60");
        DEFAULT_TEMP_VALUES.add("minecraft:fire=60");
        DEFAULT_TEMP_VALUES.add("minecraft:water=-10");
        DEFAULT_TEMP_VALUES.add("minecraft:ice=-50");
        DEFAULT_TEMP_VALUES.add("minecraft:packed_ice=-100");
        DEFAULT_TEMP_VALUES.add("minecraft:blue_ice=-200");

        TEMP_VALUES = SERVER_BUILDER.comment("Temperature values. You may add your own entries")
                .define("temp_values", DEFAULT_TEMP_VALUES, TEMP_VALUES_VALIDATOR);

        SERVER_BUILDER.pop();
    }



    public static HashMap<Block, Integer> readTempArray() {
        HashMap<Block, Integer> map = new HashMap<>();
        for(String s: TEMP_VALUES.get()) {
            String[] sArray = s.split("=");
            StringReader reader = new StringReader(sArray[0]);
            BlockStateParser parser = new BlockStateParser(reader, false);
            try{
                map.put(parser.parse(true).getState().getBlock(), Integer.parseInt(sArray[1]));
            } catch (NullPointerException | CommandSyntaxException e) {
                LOGGER.warn(sArray[0] + " not recognized");
            }
        }
        return map;
    }

    public static void setup() {
        final CommentedFileConfig configData = CommentedFileConfig.builder(FMLPaths.CONFIGDIR.get().resolve(MOD_ID + ".toml"))
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();
        configData.load();
        COMMON_CONFIG.setConfig(configData);
    }



    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {
    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }

}