package com.gempukku.libgdx.vfx.test;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

public class VfxTestLauncher {
    public static void main(String[] arg) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Vfx Test");
        config.setWindowedMode(1440, 810);

        new Lwjgl3Application(new VfxTestGame());
    }
}
