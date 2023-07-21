package com.gempukku.libgdx.vfx.test;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.gempukku.libgdx.graph.pipeline.PipelineLoader;
import com.gempukku.libgdx.graph.pipeline.PipelineRenderer;
import com.gempukku.libgdx.graph.pipeline.RenderOutputs;
import com.gempukku.libgdx.graph.pipeline.impl.SimplePipelineRendererConfiguration;
import com.gempukku.libgdx.graph.util.DefaultTimeKeeper;

public class SimpleEmitterScreen extends ScreenAdapter {
    private DefaultTimeKeeper renderPipelineTimeKeeper;
    private SimplePipelineRendererConfiguration renderPipelineConfiguration;
    private PipelineRenderer pipelineRenderer;

    @Override
    public void show() {
        renderPipelineTimeKeeper = new DefaultTimeKeeper();
        renderPipelineConfiguration = new SimplePipelineRendererConfiguration(renderPipelineTimeKeeper);

        pipelineRenderer = PipelineLoader.loadPipelineRenderer(Gdx.files.internal("pipeline/billboard-pipeline.rnp"), renderPipelineConfiguration);
    }

    @Override
    public void render(float delta) {
        renderPipelineTimeKeeper.updateTime(delta);
        pipelineRenderer.render(RenderOutputs.drawToScreen);
    }

    @Override
    public void hide() {
        pipelineRenderer.dispose();
        pipelineRenderer = null;

        renderPipelineConfiguration.dispose();
        renderPipelineConfiguration = null;

        renderPipelineTimeKeeper = null;
    }
}
