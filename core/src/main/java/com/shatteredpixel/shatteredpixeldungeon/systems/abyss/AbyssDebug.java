/*
 * PROJECT ABYSS
 * Debug-only development helpers.
 *
 * This file is part of a derivative of Shattered Pixel Dungeon and remains
 * subject to the repository's GPL-3.0-or-later license.
 */
package com.shatteredpixel.shatteredpixeldungeon.systems.abyss;

import com.watabou.utils.DeviceCompat;

/**
 * Central gate for features that must never be available in release builds.
 *
 * Keep debug-only state out of the normal dungeon progression and save format.
 * Persistent test-session state will be introduced together with the isolated
 * test dungeon, rather than being inferred from the current floor/depth.
 */
public final class AbyssDebug {

    private static boolean testSessionRequested;

    private AbyssDebug() {
    }

    /** True only when the running application is a debug build. */
    public static boolean isEnabled() {
        return DeviceCompat.isDebug();
    }

    /** Requests entry into the isolated test session. */
    public static void requestTestSession() {
        if (isEnabled()) {
            testSessionRequested = true;
        }
    }

    /**
     * Consumes the pending request exactly once.
     * Release builds always return false.
     */
    public static boolean consumeTestSessionRequest() {
        if (!isEnabled() || !testSessionRequested) {
            testSessionRequested = false;
            return false;
        }
        testSessionRequested = false;
        return true;
    }

    /** Clears transient debug navigation state. */
    public static void clearTestSessionRequest() {
        testSessionRequested = false;
    }
}
