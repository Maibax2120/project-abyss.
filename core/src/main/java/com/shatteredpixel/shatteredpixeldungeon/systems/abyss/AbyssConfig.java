/*
 * PROJECT ABYSS
 *
 * This file is part of a derivative work based on Shattered Pixel Dungeon.
 * Keep the upstream GPL-3.0 notices and attribution when distributing.
 */
package com.shatteredpixel.shatteredpixeldungeon.systems.abyss;

/**
 * Central, project-specific identity values.
 *
 * This class deliberately has no gameplay side effects. It provides a stable
 * home for Abyss-specific configuration while inherited gameplay systems are
 * migrated incrementally.
 */
public final class AbyssConfig {

    public static final String GAME_NAME = "Project Abyss";
    public static final String VERSION_NAME = "0.1.0-dev";
    public static final String BUILD_CHANNEL = "development";

    private AbyssConfig() {
        // Utility class.
    }
}
