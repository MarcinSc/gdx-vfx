package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.common.Producer;
import com.gempukku.libgdx.graph.data.GraphWithProperties;
import com.gempukku.libgdx.graph.data.MapWritablePropertyContainer;
import com.gempukku.libgdx.graph.pipeline.PipelineLoader;
import com.gempukku.libgdx.graph.pipeline.PipelineRenderer;
import com.gempukku.libgdx.graph.pipeline.RenderToTextureOutput;
import com.gempukku.libgdx.graph.pipeline.RendererConfiguration;
import com.gempukku.libgdx.graph.pipeline.impl.SimplePipelineRendererConfiguration;
import com.gempukku.libgdx.graph.ui.AssetResolver;
import com.gempukku.libgdx.graph.ui.pipeline.UIRenderPipelineConfiguration;
import com.gempukku.libgdx.graph.util.DefaultTimeKeeper;
import com.gempukku.libgdx.ui.DisposableTable;
import com.gempukku.libgdx.vfx.*;
import com.gempukku.libgdx.vfx.design.UIVfxGraphConfiguration;

public class VfxPreview extends DisposableTable {
    private final DefaultTimeKeeper timeKeeper;
    private final PerspectiveCamera camera;
    private FileHandle pipelineFile;
    private VfxTemplate vfxTemplate;
    private VfxInstance vfxInstance;
    private Texture texture;

    private SimplePipelineRendererConfiguration configuration;
    private SimpleVfxEffectConfiguration vfxEffectConfiguration;
    private PipelineRenderer pipelineRenderer;

    private boolean initialized = false;

    public VfxPreview() {
        timeKeeper = new DefaultTimeKeeper();

        camera = new PerspectiveCamera();
        camera.near = 0.1f;
        camera.far = 100f;
        camera.position.set(0f, 0f, 0.9f);
        camera.up.set(0f, 1f, 0f);
        camera.lookAt(0, 0f, 0f);
        camera.update();
    }

    public void setPipelineFile(FileHandle pipelineFile) {
        this.pipelineFile = pipelineFile;
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
            boolean finished = vfxInstance.update(delta);
            if (finished) {
                vfxInstance.dispose();
                vfxInstance = vfxTemplate.createEffect(vfxEffectConfiguration);
            }
        }

        int width = MathUtils.round(getWidth());
        int height = MathUtils.round(getHeight());
        if (initialized && width > 0 && height > 0) {
            if (hasToRecreateBuffer(width, height)) {
                if (texture != null) {
                    texture.dispose();
                }
                texture = new Texture(width, height, Pixmap.Format.RGBA8888);
            }

            camera.viewportWidth = width;
            camera.viewportHeight = height;
            camera.update();

            drawToOffscreen();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (initialized && texture != null && vfxInstance != null) {
            batch.draw(texture, getX(), getY() + getHeight(), getWidth(), -getHeight());
        }
        super.draw(batch, parentAlpha);
    }

    private void drawToOffscreen() {
        pipelineRenderer.render(new RenderToTextureOutput(texture));
    }

    private boolean hasToRecreateBuffer(int width, int height) {
        return texture == null || texture.getWidth() != width || texture.getHeight() != height;
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
        if (initialized && pipelineFile != null && vfxTemplate != null) {
            destroyEverything();

            // Create configuration
            configuration = new SimplePipelineRendererConfiguration(timeKeeper, AssetResolver.instance, new MapWritablePropertyContainer());
            for (ObjectMap.Entry<Class<? extends RendererConfiguration>, Producer<? extends RendererConfiguration>> configurationProducer : UIRenderPipelineConfiguration.getPreviewConfigurationBuilders()) {
                Class<RendererConfiguration> configurationClass = (Class<RendererConfiguration>) configurationProducer.key;
                RendererConfiguration rendererConfiguration = configurationProducer.value.create();
                configuration.setConfig(configurationClass, rendererConfiguration);
            }

            // Create vfxConfiguration
            vfxEffectConfiguration = new SimpleVfxEffectConfiguration(timeKeeper, new DefaultTimeKeeper());
            for (ObjectMap.Entry<Class<? extends VfxConfiguration>, Producer<? extends VfxConfiguration>> configurationProducer : UIVfxGraphConfiguration.getPreviewConfigurationBuilders()) {
                Class<VfxConfiguration> configurationClass = (Class<VfxConfiguration>) configurationProducer.key;
                VfxConfiguration vfxConfiguration = configurationProducer.value.create();
                vfxEffectConfiguration.setConfig(configurationClass, vfxConfiguration);
            }

            // Load pipeline
            pipelineRenderer = PipelineLoader.loadPipelineRenderer(pipelineFile, configuration);

            vfxInstance = vfxTemplate.createEffect(vfxEffectConfiguration);
        } else {
            // Destroy everything!
            destroyEverything();
        }
    }

    private void destroyEverything() {
        destroyConfiguration();
        destroyPipeline();
        destroyTexture();
        destroyVfxInstance();
    }

    private void destroyTexture() {
        if (texture != null) {
            texture.dispose();
            texture = null;
        }
    }

    private void destroyVfxInstance() {
        if (vfxInstance != null) {
            vfxInstance.dispose();
            vfxInstance = null;
        }
    }

    private void destroyPipeline() {
        if (pipelineRenderer != null) {
            pipelineRenderer.dispose();
            pipelineRenderer = null;
        }
    }

    private void destroyConfiguration() {
        if (vfxEffectConfiguration != null) {
            vfxEffectConfiguration.dispose();
            vfxEffectConfiguration = null;
        }
        if (configuration != null) {
            configuration.dispose();
            configuration = null;
        }
    }
}
