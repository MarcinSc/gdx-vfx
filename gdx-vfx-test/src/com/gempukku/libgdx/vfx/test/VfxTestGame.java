package com.gempukku.libgdx.vfx.test;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.profiling.GLProfiler;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.gempukku.libgdx.common.SimpleNumberFormatter;

public class VfxTestGame extends Game {
    private Array<Screen> screens;

    private int loadedIndex;
    private final FPSLogger fpsLogger = new FPSLogger();

    private boolean profile = false;
    private GLProfiler profiler;
    private Skin uiSkin;
    private Stage stage;
    private Label sceneLabel;
    private Label profilingLabel;

    @Override
    public void create() {
        screens = new Array<>();

        loadedIndex = screens.size - 1;

        setScreen(screens.get(loadedIndex));

        uiSkin = new Skin(Gdx.files.classpath("skin/default/uiskin.json"));
        stage = new Stage(new ScreenViewport());
        sceneLabel = new Label("", uiSkin);
        profilingLabel = new Label("", uiSkin);

        Table tbl1 = new Table(uiSkin);

        tbl1.setFillParent(true);
        tbl1.align(Align.bottomRight);

        tbl1.add(sceneLabel).width(250).pad(10f);
        tbl1.row();

        Table tbl2 = new Table(uiSkin);
        tbl2.setFillParent(true);
        tbl2.align(Align.topRight);
        tbl2.add(profilingLabel).width(200).pad(10f);
        tbl2.row();

        stage.addActor(tbl1);
        stage.addActor(tbl2);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

        super.resize(width, height);
    }

    @Override
    public void render() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.P) && loadedIndex > 0) {
            loadedIndex--;
            setScreen(screens.get(loadedIndex));
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.N) && loadedIndex < screens.size - 1) {
            loadedIndex++;
            setScreen(screens.get(loadedIndex));
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.O)) {
            if (profile)
                disableProfiler();
            else
                enableProfiler();
        }

        long start = 0;
        if (profile) {
            fpsLogger.log();
            profiler.reset();
            start = System.nanoTime();
        }

        super.render();

        StringBuilder sb = new StringBuilder();
        if (profile) {
            float ms = (System.nanoTime() - start) / 1000000f;

            sb.append("Time: " + SimpleNumberFormatter.format(ms) + "ms\n");
            sb.append("GL Calls: " + profiler.getCalls() + "\n");
            sb.append("Draw calls: " + profiler.getDrawCalls() + "\n");
            sb.append("Shader switches: " + profiler.getShaderSwitches() + "\n");
            sb.append("Texture bindings: " + profiler.getTextureBindings() + "\n");
            sb.append("Vertex calls: " + profiler.getVertexCount().total + "\n");
            long memory = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024);
            sb.append("Used memory: " + memory + "MB\n");
        }
        profilingLabel.setText(sb.toString());

        sb.setLength(0);
        sb.append("Scene:\n");
        sb.append("Screen " + (loadedIndex + 1) + "\n");
        sb.append("P - for previous scene\n");
        sb.append("N - for next scene\n");
        sb.append("O - to toggle profiling\n");
        sceneLabel.setText(sb.toString());

        stage.draw();
    }

    @Override
    public void dispose() {
        for (Screen screen : screens) {
            screen.dispose();
        }
        screens = null;

        super.dispose();
    }

    private void enableProfiler() {
        profiler = new GLProfiler(Gdx.graphics);
        profiler.enable();

        profile = true;
    }

    private void disableProfiler() {
        profiler.disable();

        profile = false;
    }
}
