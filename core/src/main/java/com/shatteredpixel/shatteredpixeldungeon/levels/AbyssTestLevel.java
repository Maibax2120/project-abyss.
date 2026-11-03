/*
 * PROJECT ABYSS
 * Debug-only test floor.
 *
 * This file is part of a derivative of Shattered Pixel Dungeon and remains
 * subject to the repository's GPL-3.0-or-later license.
 */
package com.shatteredpixel.shatteredpixeldungeon.levels;

import com.shatteredpixel.shatteredpixeldungeon.levels.features.LevelTransition;


/**
 * An isolated development floor. It uses the inherited room generator and
 * terrain rules, but intentionally starts without ordinary enemies or loot.
 * Future test zones can be added here without changing the regular dungeon.
 */
public class AbyssTestLevel extends SewerLevel {

    @Override
    public void create() {
        super.create();

        // Keep this a single isolated test floor. Both stair directions reload
        // the same branch/depth, so a test run cannot wander into normal floors.
        for (LevelTransition transition : transitions) {
            transition.destDepth = 1;
            transition.destBranch = 2;
        }
    }

    @Override
    protected void createMobs() {
        // Test floor starts empty; debug tests can add mobs deliberately.
    }

    @Override
    protected void createItems() {
        // Keep the floor clear; items are supplied through the Debug Toolkit.
    }
}
