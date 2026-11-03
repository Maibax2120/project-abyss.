/*
 * PROJECT ABYSS
 * Debug-only item access tool.
 *
 * This file is part of a derivative of Shattered Pixel Dungeon and remains
 * subject to the repository's GPL-3.0-or-later license.
 */
package com.shatteredpixel.shatteredpixeldungeon.items;

import com.shatteredpixel.shatteredpixeldungeon.Dungeon;
import com.shatteredpixel.shatteredpixeldungeon.actors.hero.Hero;
import com.shatteredpixel.shatteredpixeldungeon.scenes.GameScene;
import com.shatteredpixel.shatteredpixeldungeon.sprites.ItemSpriteSheet;
import com.shatteredpixel.shatteredpixeldungeon.systems.abyss.AbyssDebug;
import com.shatteredpixel.shatteredpixeldungeon.windows.WndOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * A development-only item browser. It exposes concrete classes registered in
 * the inherited item generator, without changing normal loot generation.
 */
public class DebugToolkit extends Item {

    private static final String AC_OPEN = "OPEN";
    private static final int PAGE_SIZE = 5;

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
    public ArrayList<String> actions(Hero hero) {
        ArrayList<String> actions = super.actions(hero);
        if (AbyssDebug.isEnabled()) actions.add(AC_OPEN);
        return actions;
    }

    @Override
    public String actionName(String action, Hero hero) {
        if (AC_OPEN.equals(action)) return "Open item catalogue";
        return super.actionName(action, hero);
    }

    @Override
    public String name() {
        return "Debug Toolkit";
    }

    @Override
    public String desc() {
        return "Opens a catalogue of item types available in the development build.";
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
                "Choose an item category.",
                CATEGORY_NAMES
        ) {
            @Override
            protected void onSelect(int index) {
                if (!AbyssDebug.isEnabled() || index < 0 || index >= CATEGORIES.length) return;
                showItemPage(hero, CATEGORIES[index], 0);
            }
        });
    }

    private static void showItemPage(Hero hero, Generator.Category category, int page) {
        if (!AbyssDebug.isEnabled()) return;

        List<Class<? extends Item>> classes = classesFor(category);
        if (classes.isEmpty()) {
            giveItem(hero, Generator.random(category));
            return;
        }

        int start = page * PAGE_SIZE;
        int end = Math.min(start + PAGE_SIZE, classes.size());
        ArrayList<String> options = new ArrayList<>();
        for (int i = start; i < end; i++) {
            options.add(classes.get(i).getSimpleName());
        }
        if (page > 0) options.add("Previous page");
        if (end < classes.size()) options.add("Next page");

        GameScene.show(new WndOptions(
                "Debug Toolkit",
                category.name() + " — choose an item (" + (page + 1) + ")",
                options.toArray(new String[0])
        ) {
            @Override
            protected void onSelect(int index) {
                if (!AbyssDebug.isEnabled()) return;

                if (index < end - start) {
                    giveItem(hero, Generator.random(classes.get(start + index)));
                } else {
                    int navigationIndex = index - (end - start);
                    if (page > 0 && navigationIndex == 0) {
                        showItemPage(hero, category, page - 1);
                    } else {
                        showItemPage(hero, category, page + 1);
                    }
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private static List<Class<? extends Item>> classesFor(Generator.Category category) {
        ArrayList<Class<? extends Item>> result = new ArrayList<>();

        if (category == Generator.Category.WEAPON) {
            addClasses(result, Generator.Category.WEP_T1);
            addClasses(result, Generator.Category.WEP_T2);
            addClasses(result, Generator.Category.WEP_T3);
            addClasses(result, Generator.Category.WEP_T4);
            addClasses(result, Generator.Category.WEP_T5);
        } else if (category == Generator.Category.MISSILE) {
            addClasses(result, Generator.Category.MIS_T1);
            addClasses(result, Generator.Category.MIS_T2);
            addClasses(result, Generator.Category.MIS_T3);
            addClasses(result, Generator.Category.MIS_T4);
            addClasses(result, Generator.Category.MIS_T5);
        } else {
            addClasses(result, category);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    private static void addClasses(List<Class<? extends Item>> target, Generator.Category category) {
        if (category.classes == null) return;
        for (Class<?> itemClass : category.classes) {
            if (Item.class.isAssignableFrom(itemClass)) {
                target.add((Class<? extends Item>) itemClass);
            }
        }
    }

    private static void giveItem(Hero hero, Item item) {
        if (!AbyssDebug.isEnabled() || item == null) return;

        item.identify();
        if (!item.collect()) {
            Dungeon.level.drop(item, hero.pos).sprite.drop(hero.pos);
        }
    }
}
