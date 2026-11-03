/*
 * PROJECT ABYSS
 * Debug-only test floor.
 *
 * This file is part of a derivative of Shattered Pixel Dungeon and remains
 * subject to the repository's GPL-3.0-or-later license.
 */
package com.shatteredpixel.shatteredpixeldungeon.levels;


/**
 * An isolated development floor. It uses the inherited room generator and
 * terrain rules, but intentionally starts without ordinary enemies or loot.
 * Future test zones can be added here without changing the regular dungeon.
 */
public class AbyssTestLevel extends SewerLevel {

    @Override
    protected void createMobs() {
        // Test floor starts empty; debug tests can add mobs deliberately.
    }

    @Override
    protected void createItems() {
        // Keep the floor clear; items are supplied through the Debug Toolkit.
    }
}
