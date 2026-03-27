package com.logicjo.pygmalion;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class Pygmalion implements ModInitializer {
	public static final String MOD_ID = "pygmalion";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.


        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("pygmalion")
                    .then(Commands.argument("value_one", IntegerArgumentType.integer())
                            .executes(context -> executeCommon(IntegerArgumentType.getInteger(context, "value_one"), 0, context))
                            .then(Commands.argument("value_two", IntegerArgumentType.integer())
                                    .executes(context -> executeCommon(
                                            IntegerArgumentType.getInteger(context, "value_one"),
                                            IntegerArgumentType.getInteger(context, "value_two"),
                                            context)))));
        });

		LOGGER.info("Pygmalion loadet");
        LOGGER.info("Open source project by LogicJo /Shadow_Shards");

	}

    private static int executeCommon(int value1, int value2, CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Called /pygmalion with value 1 = %s and value 2 = %s".formatted(value1, value2)), false);
        return 1;
    }
}