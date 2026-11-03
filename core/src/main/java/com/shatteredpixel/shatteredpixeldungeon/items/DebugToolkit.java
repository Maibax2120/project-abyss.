/*
 * PROJECT ABYSS
 * Debug-only item access tool.
 *
 * This file is part of a derivative of Shattered Pixel Dungeon and remains
 * subject to the repository's GPL-3.0-or-later license.
 */
package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.messages.Messages;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.systems.abyss.AbyssDebug;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;

/**
 * A development-only menu for generating items from the inherited item
 * generator. It deliberately does not alter the generator or normal loot.
 */
public class DebugToolkit extends Item {

    private static final String AC_OPEN = "OPEN";

    private static final Generator.Category[] CATEGORIES = {
            Generator.Category.WEAPON,
            Generator.Category.ARMOR,
            Generator.Category.MISSILE,
            Generator.Category.WAND,
            Generator.Category.RING,
            Generator.Category.ARTIFACT,
            Generator.Category.POTION,
            Generator.Category.SCROLL,
            Generator.Category.FOOD,
            Generator.Category.SEED,
            Generator.Category.STONE,
            Generator.Category.TRINKET,
            Generator.Category.GOLD
    };

    private static final String[] CATEGORY_NAMES = {
            "Weapons",
            "Armor",
            "Missile weapons",
            "Wands",
            "Rings",
            "Artifacts",
            "Potions",
            "Scrolls",
            "Food",
            "Seeds",
            "Runestones",
            "Trinkets",
            "Gold"
    };

    public DebugToolkit() {
        image = ItemSpriteSheet.ARTIFACT_HOLDER;
        unique = true;
        levelKnown = true;
        defaultAction = AC_OPEN;
    }

    @Override
    public java.util.ArrayList<String> actions(Hero hero) {
        java.util.ArrayList<String> actions = super.actions(hero);
        if (AbyssDebug.isEnabled()) actions.add(AC_OPEN);
        return actions;
    }

    @Override
    public String actionName(String action, Hero hero) {
        if (AC_OPEN.equals(action)) return "Open item catalogue";
        return super.actionName(action, hero);
    }

    @Override
    public void execute(Hero hero, String action) {
        if (!AC_OPEN.equals(action)) {
            super.execute(hero, action);
            return;
        }

        if (!AbyssDebug.isEnabled()) return;

        GameScene.cancel();
        GameScene.show(new WndOptions(
                "Debug Toolkit",
                "Choose a category. An item from that category will be generated.",
                CATEGORY_NAMES
        ) {
            @Override
            protected void onSelect(int index) {
                if (!AbyssDebug.isEnabled() || index < 0 || index >= CATEGORIES.length) return;

                Item item = Generator.random(CATEGORIES[index]);
                if (item == null) return;

                item.identify();
                if (!item.collect()) {
                    Dungeon.level.drop(item, hero.pos).sprite.drop(hero.pos);
                }
                GameScene.updateScene();
            }
        });
    }
}
