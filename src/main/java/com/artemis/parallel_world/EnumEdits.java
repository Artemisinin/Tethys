package com.artemis.parallel_world;

import com.chocohead.mm.api.ClassTinkerers;

public class EnumEdits implements Runnable {

    @Override
    public void run() {
        // Add spawn group
        ClassTinkerers.enumBuilder("net.minecraft.entity.SpawnGroup", String.class, int.class, boolean.class, boolean.class, int.class).
                addEnum("TETHYS_CREATURES", "tethys_creatures", 30, true, true, 128).
                build();
        ClassTinkerers.enumBuilder("net.minecraft.entity.SpawnGroup", String.class, int.class, boolean.class, boolean.class, int.class).
                addEnum("TETHYS_MONSTERS", "tethys_monsters", 30, false, false, 128).
                build();
        }
}
