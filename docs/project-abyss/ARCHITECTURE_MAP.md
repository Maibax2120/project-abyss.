# PROJECT ABYSS — Architecture Map

**Inspected branch:** `project-abyss-dev`  
**Base code:** Shattered Pixel Dungeon  
**Purpose:** record the existing extension points before gameplay refactoring.

## 1. Runtime and turn flow

The game uses an actor scheduler rather than a simple loop that updates every enemy once per player input.

- `actors/Actor.java` owns the global actor registry, actor IDs, game time, priorities, scheduling and actor serialization.
- `actors/Char.java` is the shared character layer: position, movement, health, buffs and combat resolution.
- `actors/hero/Hero.java` specializes the player character and owns hero progression, equipment interactions, talents and hero-specific persistence.
- `actors/mobs/Mob.java` specializes non-player combatants and contains AI state handling, target selection and enemy persistence.
- `actors/buffs/Buff.java` models timed effects as actors; buffs participate in the scheduler and are attached to characters.

**Refactoring rule:** do not replace the scheduler or rewrite the shared combat path as an early change. New mechanics should first be implemented as isolated systems and connected through narrow extension points.

## 2. World and level lifecycle

- `Dungeon.java` owns the active run state: hero, current level, depth/branch, seed, run statistics and generated-level tracking.
- `levels/Level.java` owns tile arrays and world objects: mobs, item heaps, blobs, plants, traps, transitions and custom tilemaps.
- `Dungeon.newLevel()` selects the level class based on depth/branch. This is a major extension point for replacing the inherited dungeon progression with a new world structure.
- Level and run state are serialized. Changes to fields that affect a run must define save/restore behavior.

**Refactoring rule:** separate world generation from run persistence before attempting a large world-generation redesign.

## 3. Presentation and input

- `scenes/GameScene.java` builds the active gameplay presentation and connects world state to visible UI elements.
- `scenes/TitleScene.java` builds the title/menu screen and wires its buttons to other scenes.
- `items/Item.java` is the base for inventory objects and item interactions.
- `actors/buffs/Buff.java` also has UI-facing behavior through buff indicators and localized names/descriptions.

**Refactoring rule:** keep gameplay rules out of scene classes where possible. Scenes should present state and route player input into game systems.

## 4. Existing persistence constraints

- `Actor`, `Char`, `Hero`, `Mob` and `Level` each serialize their own state.
- Actor IDs are used to restore references such as mob targets.
- The level stores tile and object collections, while the dungeon stores run-level progression and generation data.

Any new persistent mechanic must have a stable bundle key, a default for older saves, and a restore path. Save compatibility must be tested before distributing builds.

## 5. Recommended incremental migration sequence

1. Keep the inherited scheduler and movement stable.
2. Add new mechanics in a separate `systems/abyss` package.
3. Add save/load support for each new persistent mechanic.
4. Connect one mechanic at a time to `Hero`, `Char`, `Mob` or `Level`.
5. Add the corresponding UI in a scene/UI component, not inside core rules.
6. Add regression tests or manual test scenarios for movement, combat, death, level transitions and save/load.
7. Only after these seams are stable, replace larger inherited systems.

## 6. Known risk areas

- Combat changes in `Char.attack()` affect both the hero and mobs.
- Hero changes can interact with talents, subclasses, armor abilities, equipment and buffs.
- Mob AI is stateful and saves target references.
- Level generation and save/load share world data structures.
- TitleScene currently wires upstream news, updates, support and other services; these need a separate product-identity review.

## 7. Scope of this inspection

This map is based on reading the source files listed above. It is an architectural reconnaissance document, not a claim that every subsystem has been fully audited or that the project has been built and tested.
