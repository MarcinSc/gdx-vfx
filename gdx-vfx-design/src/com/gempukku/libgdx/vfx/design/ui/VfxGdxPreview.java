package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.common.Producer;
import com.gempukku.libgdx.graph.data.GraphWithProperties;
import com.gempukku.libgdx.graph.util.DefaultTimeKeeper;
import com.gempukku.libgdx.ui.DisposableTable;
import com.gempukku.libgdx.vfx.*;
import com.gempukku.libgdx.vfx.design.UIVfxGraphConfiguration;

public class VfxGdxPreview extends DisposableTable {
    private final DefaultTimeKeeper timeKeeper;
    private final PerspectiveCamera camera;
    private VfxTemplate vfxTemplate;
    private VfxInstance vfxInstance;
    private FrameBuffer frameBuffer;

    private SimpleVfxEffectConfiguration vfxEffectConfiguration;

    private boolean initialized = false;

    public VfxGdxPreview() {
        timeKeeper = new DefaultTimeKeeper();

        camera = new PerspectiveCamera();
        camera.near = 0.1f;
        camera.far = 100f;
        camera.position.set(0f, 0f, 0.9f);
        camera.up.set(0f, 1f, 0f);
        camera.lookAt(0, 0f, 0f);
        camera.update();
    }

    @Override
    protected void initializeWidget() {
        initialized = true;
        updateRenderingWidgetIfNeeded();
    }

    @Override
    protected void disposeWidget() {
        initialized = false;
        updateRenderingWidgetIfNeeded();
    }

    @Override
    public void act(float delta) {
        validate();
        super.act(delta);

        timeKeeper.updateTime(delta);
        if (vfxInstance != null) {
            boolean finished = vfxInstance.process();
            if (finished) {
                vfxInstance.dispose();
                vfxInstance = vfxTemplate.createEffect(vfxEffectConfiguration);
            }
        }

        int width = MathUtils.round(getWidth());
        int height = MathUtils.round(getHeight());
        if (initialized && width > 0 && height > 0) {
            if (hasToRecreateBuffer(width, height)) {
                if (frameBuffer != null) {
                    frameBuffer.dispose();
                }
                frameBuffer = new FrameBuffer(Pixmap.Format.RGBA8888, width, height, true);
            }

            camera.viewportWidth = width;
            camera.viewportHeight = height;
            camera.update();

            drawToOffscreen();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (initialized && frameBuffer != null && vfxInstance != null) {
            batch.draw(frameBuffer.getColorBufferTexture(), getX(), getY() + getHeight(), getWidth(), -getHeight());
        }
        super.draw(batch, parentAlpha);
    }

    private void drawToOffscreen() {
        if (frameBuffer != null) {
            System.out.println("Drawing to offscreen");
            frameBuffer.begin();
            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            frameBuffer.end();
        }
    }

    private boolean hasToRecreateBuffer(int width, int height) {
        return frameBuffer == null || frameBuffer.getWidth() != width || frameBuffer.getHeight() != height;
    }

    public void graphChanged(GraphWithProperties graph) {
        try {
            this.vfxTemplate = VfxTemplateLoader.loadVfxTemplate(graph);
        } catch (Exception exp) {
            this.vfxTemplate = null;
        }
        updateRenderingWidgetIfNeeded();
    }

    private void updateRenderingWidgetIfNeeded() {
        if (initialized && vfxTemplate != null) {
            destroyEverything();

            // Create vfxConfiguration
            vfxEffectConfiguration = new SimpleVfxEffectConfiguration(timeKeeper, new DefaultTimeKeeper());
            for (ObjectMap.Entry<Class<? extends VfxConfiguration>, Producer<? extends VfxConfiguration>> configurationProducer : UIVfxGraphConfiguration.getPreviewGdxConfigurationBuilders()) {
                Class<VfxConfiguration> configurationClass = (Class<VfxConfiguration>) configurationProducer.key;
                VfxConfiguration vfxConfiguration = configurationProducer.value.create();
                vfxEffectConfiguration.setConfig(configurationClass, vfxConfiguration);
            }

            vfxInstance = vfxTemplate.createEffect(vfxEffectConfiguration);
        } else {
            // Destroy everything!
            destroyEverything();
        }
    }

    private void destroyEverything() {
        destroyConfiguration();
        destroyFrameBuffer();
        destroyVfxInstance();
    }

    private void destroyFrameBuffer() {
        if (frameBuffer != null) {
            frameBuffer.dispose();
            frameBuffer = null;
        }
    }

    private void destroyVfxInstance() {
        if (vfxInstance != null) {
            vfxInstance.dispose();
            vfxInstance = null;
        }
    }

    private void destroyConfiguration() {
        if (vfxEffectConfiguration != null) {
            vfxEffectConfiguration.dispose();
            vfxEffectConfiguration = null;
        }
    }
}
