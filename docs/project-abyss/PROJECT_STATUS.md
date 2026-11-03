# PROJECT ABYSS — Project Status

## Repository

- Upstream: https://github.com/00-Evan/shattered-pixel-dungeon
- Fork: https://github.com/Maibax2120/project-abyss.
- Working branch: `project-abyss-dev`
- Upstream baseline version observed in root build.gradle: 4.0.2
- Upstream build stack observed: Java compatibility 11, LibGDX 1.14.0, Gradle multi-project build.

## Project decisions

| Area | Current decision |
|---|---|
| Source strategy | Fork and deeply modify Shattered Pixel Dungeon |
| Project relationship | Separate new game; not ASHEN WORLD |
| Platforms | Android + Windows Desktop |
| Working title | PROJECT ABYSS (temporary) |
| First implementation strategy | Preserve the inherited turn-based foundation initially |

## Current state

- [x] Fork detected and readable through the connected GitHub account.
- [x] Dedicated branch `project-abyss-dev` created from `master`.
- [x] Initial architecture and sprint documentation committed.
- [ ] Desktop build executed and verified.\n- [x] GitHub Actions workflow added for desktop release/debug JARs + Android debug APK (latest run pending verification).
- [ ] Android build executed and verified.
- [x] Initial unique application/package identifier selected (`com.maibax2120.projectabyss`; can be revised later).
- [x] Base application metadata changed in root `build.gradle` (name, application ID, development version).\n- [ ] Title-screen artwork, icons, credits and service branding migrated.

## Debug development track

- [x] Debug-only feature gate added at `systems/abyss/AbyssDebug.java` using the platform debug-build check.
- [x] Added transient, one-shot test-session request state; it is deliberately not written into normal save data.
- [ ] Debug Character integration into the character selection and save-slot flow.
- [ ] Isolated test floor with dedicated transitions and save/load behavior.
- [ ] Debug Toolkit item catalogue and grant actions.
- [ ] Verify that all debug entry points are absent/inactive in release builds.

The first code change is infrastructure only. It does not yet expose a playable Debug Character or test floor. The existing `HeroClass` enum participates in initialization, unlocks, sprites, armor abilities, mastery badges and save-slot sizing, so the debug character will not be inserted into it until those dependencies are audited. This avoids changing ordinary character selection or risking compatibility with existing saves.

## Debug development track

- [x] Debug-only feature gate uses the platform's `INDEV` build marker.
- [x] Debug Character added to the hero selection list only in Debug builds; it reuses the Warrior baseline for now and does not alter the combat engine.
- [x] Debug Character receives the Debug Toolkit at run start.
- [x] Debug Toolkit opens a paged catalogue of concrete items registered in the inherited generator categories.
- [x] Debug Character starts on isolated branch `2`, separate from the normal dungeon branch `0` and side branch `1`.
- [x] Added `AbyssTestLevel`, which uses the inherited room generator but starts without ordinary mobs or floor loot.
- [x] Debug saves are hidden from the Release build's continue list.
- [ ] Verify Android Debug APK and desktop Debug JAR builds in CI.
- [ ] Playtest character creation, save/continue, item granting and test-floor transitions on Android and desktop.
- [ ] Add a dedicated, hand-authored test-room layout and controlled enemy spawn zones.

**Current limitation:** the test floor currently uses the inherited sewer room-generation and visual style as a temporary shell. The bespoke layout, enemy-spawn controls and a complete catalogue of non-generator quest/special items are not implemented yet.

## Important technical note

The upstream repository's desktop guide documents `./gradlew desktop:debug` for running a debug build. This is a documented command, not a build result from this development environment.

The upstream recommended-changes guide specifically calls out `appName`, `appPackageName`, version fields, icons/title assets, credits and update notification services as areas to review when creating a derivative.

## Licensing

The upstream desktop guide identifies GPLv3 obligations for distributed modifications. Preserve existing notices and review the complete license text and distribution obligations before publishing binaries.
