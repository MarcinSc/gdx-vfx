package com.gempukku.libgdx.vfx.particle.design.producer;

import com.gempukku.libgdx.ui.graph.editor.part.StringEditorPart;
import com.gempukku.libgdx.vfx.design.ui.InputNodeEditorPart;
import com.gempukku.libgdx.vfx.design.ui.VfxGraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.VfxMenuNodeEditorProducer;
import com.gempukku.libgdx.vfx.particle.config.SpriteParticleRendererNodeConfiguration;

public class SpriteRendererNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public SpriteRendererNodeEditorProducer() {
        super(new SpriteParticleRendererNodeConfiguration());
    }

    @Override
    protected VfxGraphNodeEditor createNodeEditor(String nodeId) {
        VfxGraphNodeEditor graphNodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        graphNodeEditor.addGraphBoxPart(new StringEditorPart("Tag", "graphShaderTag"));
        graphNodeEditor.addGraphBoxPart(new InputNodeEditorPart("input", "Particles", true));
        return graphNodeEditor;
    }
}
