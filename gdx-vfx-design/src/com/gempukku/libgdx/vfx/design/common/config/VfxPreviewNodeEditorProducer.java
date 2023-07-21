package com.gempukku.libgdx.vfx.design.common.config;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.gempukku.libgdx.graph.pipeline.RenderPipelineGraphType;
import com.gempukku.libgdx.graph.ui.GraphResolver;
import com.gempukku.libgdx.graph.ui.GraphResolverHolder;
import com.gempukku.libgdx.ui.graph.editor.part.SelectEditorPart;
import com.gempukku.libgdx.vfx.common.config.VfxPreviewNodeConfiguration;
import com.gempukku.libgdx.vfx.design.ui.VfxGraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.VfxMenuNodeEditorProducer;

public class VfxPreviewNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public VfxPreviewNodeEditorProducer() {
        super(new VfxPreviewNodeConfiguration());
        setCloseable(false);
    }

    @Override
    protected VfxGraphNodeEditor createNodeEditor(String nodeId) {
        VfxGraphNodeEditor nodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        GraphResolver graphResolver = GraphResolverHolder.graphResolver;

        Iterable<? extends GraphResolver.GraphInformation> renderPipelines = graphResolver.getGraphsByType(RenderPipelineGraphType.TYPE);
        Array<String> names = new Array<>();
        for (GraphResolver.GraphInformation renderPipeline : renderPipelines) {
            names.add(renderPipeline.getName());
        }

        SelectEditorPart pipelineSelect = new SelectEditorPart("Pipeline: ", "pipeline", names.toArray());
        pipelineSelect.addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ChangeEvent event, Actor actor) {

                    }
                });
        nodeEditor.addGraphBoxPart(pipelineSelect);

        return nodeEditor;
    }
}
