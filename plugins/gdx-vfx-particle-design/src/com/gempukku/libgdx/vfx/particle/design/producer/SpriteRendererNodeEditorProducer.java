package com.gempukku.libgdx.vfx.particle.design.producer;

import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.InputNodeEditorPart;
import com.gempukku.libgdx.vfx.design.ui.VfxGraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.VfxMenuNodeEditorProducer;
import com.gempukku.libgdx.vfx.particle.config.SpriteParticleRendererNodeConfiguration;

public class SpriteRendererNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public SpriteRendererNodeEditorProducer() {
        super(new SpriteParticleRendererNodeConfiguration());
    }

    @Override
    public GraphNodeEditor createNodeEditor(String nodeId, JsonValue data) {
        VfxGraphNodeEditor graphNodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        graphNodeEditor.addGraphBoxPart(new InputNodeEditorPart("input", "Sprites"));
        return graphNodeEditor;
    }
}
