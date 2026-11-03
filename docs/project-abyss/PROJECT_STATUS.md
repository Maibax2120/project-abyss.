# PROJECT ABYSS — Project Status

## Repository

- Upstream: https://github.com/00-Evan/shattered-pixel-dungeon
- Fork: https://github.com/Maibax2120/project-abyss.
- Working branch: `project-abyss-dev`
- Upstream baseline version observed in root build.gradle: 4.0.2
- Target platforms: Android and Windows/Desktop.

## Project decisions

| Area | Current decision |
|---|---|
| Source strategy | Fork and deeply modify Shattered Pixel Dungeon |
| Project relationship | Separate new game; not ASHEN WORLD |
| Platforms | Android + Windows Desktop |
| Working title | PROJECT ABYSS (temporary) |
| Initial implementation strategy | Preserve inherited turn-based combat and core loop |

## Current state

- [x] Fork and dedicated development branch created.
- [x] Initial architecture and sprint documentation committed.
- [x] Application identity and package name changed.
- [x] Gradle root project name set explicitly so the repository's trailing punctuation does not break Gradle 9.
- [x] Desktop release JAR built successfully.
- [x] Desktop Debug JAR built successfully.
- [x] Android Debug APK assembled successfully.
- [x] GitHub Actions workflow uploads all three build outputs.
- [ ] Desktop/Android runtime playtest on physical devices.
- [ ] Title-screen artwork, icons, credits and service branding migrated.

Latest verified CI run:
- Run: https://github.com/Maibax2120/project-abyss./actions/runs/41662384775
- Source commit: `08e1294fa5763dbeb07797ccbed275be2a09b127`
- Result: successful.

## Debug development track

- [x] Debug-only feature gate uses the platform's `INDEV` build marker.
- [x] Debug Character appears in hero selection only in Debug builds.
- [x] Debug Character uses Warrior's current baseline stats, equipment and subclass framework; no combat formulas were changed.
- [x] Debug Toolkit is placed in the starting inventory.
- [x] Debug Toolkit has a paged catalogue for concrete items registered in the inherited item generator.
- [x] Debug Character starts on isolated branch `2`.
- [x] Added `AbyssTestLevel`, with no ordinary enemy spawns or random floor loot.
- [x] Test-floor stair transitions loop back to the same isolated test floor.
- [x] Debug saves are hidden from Release continue lists; Release can reuse the hidden debug save slot.
- [x] Release and Debug desktop JARs are packaged separately.
- [ ] Playtest Debug Character creation, save/continue, item granting and test-floor transitions on Android and desktop.
- [ ] Replace the temporary sewer-style procedural shell with a dedicated hand-authored test layout.
- [ ] Add controlled enemy-spawn zones and dedicated item/effect/damage test areas.
- [ ] Expand the catalogue to include non-generator quest and special items.

**Current limitation:** the test floor currently reuses the inherited sewer room generator and visuals as a temporary shell. It is isolated from normal dungeon progression, but its bespoke layout and controlled test zones are not implemented yet.

## Build outputs

The successful CI artifact contains:

- `android-debug.apk` — Android Debug build.
- `desktop-0.1.0-dev-debug.jar` — Desktop Debug build; manifest version includes `INDEV`.
- `desktop-0.1.0-dev.jar` — Desktop Release build.

## Important technical note

The Debug Character is an internal development tool, not a new balanced player class. Its visuals currently reuse the Warrior sprite and splash art. This is intentional until the character's final appearance and gameplay identity are designed.

## Licensing

The upstream project is GPLv3. Preserve existing notices and review the full license obligations before distributing modified binaries.
