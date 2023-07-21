package com.gempukku.libgdx.vfx.particle.design.producer;

import com.gempukku.libgdx.ui.graph.editor.part.SectionEditorPart;
import com.gempukku.libgdx.vfx.design.ui.*;
import com.gempukku.libgdx.vfx.particle.config.ParticleEmitterNodeConfiguration;
import com.kotcrab.vis.ui.util.Validators;

public class ParticleEmitterNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public ParticleEmitterNodeEditorProducer() {
        super(new ParticleEmitterNodeConfiguration());
    }

    @Override
    protected VfxGraphNodeEditor createNodeEditor(String nodeId) {
        VfxGraphNodeEditor graphNodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        graphNodeEditor.addGraphBoxPart(
                new InputOutputNodeEditorPart("run", "Run", true, "particleOutput", "Particle Output", true));

        graphNodeEditor.addGraphBoxPart(
                new SectionEditorPart("Particle Initialization", "gdx-graph-section-label", "default"));
        graphNodeEditor.addGraphBoxPart(
                new InputFloatNodeEditorPart("initCount", "Init #", "initCount", 0f, new Validators.GreaterThanValidator(0, true)));
        graphNodeEditor.addGraphBoxPart(
                new OutputNodeEditorPart("particleInit", "Particle Init", false));

        graphNodeEditor.addGraphBoxPart(
                new SectionEditorPart("Particle Update", "gdx-graph-section-label", "default"));
        graphNodeEditor.addGraphBoxPart(
                new InputFloatNodeEditorPart("perSecondCount", "Per Second #", "perSecondCount", 10f, new Validators.GreaterThanValidator(0, true)));
        graphNodeEditor.addGraphBoxPart(
                new OutputNodeEditorPart("particleUpdate", "Particle Update", false));

        graphNodeEditor.addGraphBoxPart(
                new SectionEditorPart("Particle Life", "gdx-graph-section-label", "default"));
        graphNodeEditor.addGraphBoxPart(
                new InputFloatNodeEditorPart("lifetime", "Lifetime", "lifetime", 3f, new Validators.GreaterThanValidator(0, true)));
        graphNodeEditor.addGraphBoxPart(
                new OutputNodeEditorPart("particleDeath", "Particle Death", false));

        return graphNodeEditor;
    }
}
