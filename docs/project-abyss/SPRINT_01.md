# PROJECT ABYSS — Sprint 01

**Status:** planned / implementation baseline  
**Base:** Shattered Pixel Dungeon 4.0.2  
**Target platforms:** Android and Windows (Desktop)  
**Working branch:** `project-abyss-dev`

## Sprint goal

Establish a safe, reproducible development baseline for the new game without destabilizing the inherited roguelike loop.

## Confirmed project decisions

- This is a separate game project, not ASHEN WORLD.
- The codebase is a fork/derivative of Shattered Pixel Dungeon.
- Android and Windows Desktop are the initial target platforms.
- The initial camera/combat/world-loop foundation is inherited; major redesigns happen incrementally.
- The working title is PROJECT ABYSS and remains changeable.

## Tasks

### 1. Repository and provenance
- Keep the upstream history and license notices.
- Work on the dedicated development branch.
- Record the upstream repository and the exact base version/commit before substantial changes.

### 2. Build baseline
- Verify the desktop debug task using the upstream documented command: `./gradlew desktop:debug`.
- Verify Android debug assembly using the Android guide and an installed Android SDK.
- Record JDK, Gradle, Android SDK and build results. Do not mark a build as verified until it has actually run.

### 3. Identity migration (after package ID decision)
- Change visible application name.
- Choose a unique application/package identifier before changing it.
- Update app icons, title banner, credits and store-facing metadata.
- Review update/news service modules so the derivative does not accidentally use upstream Shattered Pixel Dungeon services.

### 4. Codebase reconnaissance
- Map the game entry point and scene/menu flow.
- Map turn scheduling, actors and enemy AI.
- Map level generation, transitions and persistence.
- Map item/equipment/buff systems.
- Identify asset and localization conventions.

### 5. First playable milestone
- Launch a branded desktop build.
- Launch an Android debug build.
- Keep the inherited gameplay loop functional.
- Add a minimal project-specific title/menu identity before introducing new mechanics.

## Out of scope for Sprint 01

- Rewriting the turn-based engine.
- Replacing the rendering framework.
- Large-scale content replacement.
- Implementing the full final game design before a stable baseline exists.

## Acceptance criteria

- [ ] Development branch exists.
- [ ] Desktop debug build launches.
- [ ] Android debug build assembles and launches on a device/emulator.
- [ ] Entry points and core gameplay subsystems are documented.
- [ ] No upstream license or attribution notices are removed.
- [ ] Project-specific package identifier is selected before identity migration.
