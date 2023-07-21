package com.gempukku.libgdx.vfx.particle.config;

import com.gempukku.libgdx.graph.config.DefaultMenuNodeConfiguration;
import com.gempukku.libgdx.ui.graph.data.GraphNodeOutputSide;
import com.gempukku.libgdx.ui.graph.data.impl.DefaultGraphNodeInput;
import com.gempukku.libgdx.ui.graph.data.impl.DefaultGraphNodeOutput;
import com.gempukku.libgdx.vfx.VfxFieldType;
import com.gempukku.libgdx.vfx.particle.VfxParticleFieldType;

public class ParticleEmitterNodeConfiguration extends DefaultMenuNodeConfiguration {
    public ParticleEmitterNodeConfiguration() {
        super("ParticleEmitter", "Particle Emitter", "Particle/Emitters");
        addNodeInput(
                new DefaultGraphNodeInput("run", "Run", true, VfxFieldType.Boolean));
        addNodeOutput(
                new DefaultGraphNodeOutput("particleOutput", "Particle Output", true, GraphNodeOutputSide.Right, VfxParticleFieldType.ParticleOutput));

        addNodeInput(
                new DefaultGraphNodeInput("initCount", "Init cnt", false, VfxFieldType.Float));
        addNodeOutput(
                new DefaultGraphNodeOutput("particleInit", "Particle init", false, GraphNodeOutputSide.Right, VfxParticleFieldType.ParticleProcess));

        addNodeInput(
                new DefaultGraphNodeInput("perSecondCount", "Per second", false, VfxFieldType.Float));
        addNodeOutput(
                new DefaultGraphNodeOutput("particleUpdate", "Particle update", false, GraphNodeOutputSide.Right, VfxParticleFieldType.ParticleProcess));

        addNodeInput(
                new DefaultGraphNodeInput("lifetime", "Lifetime", false, VfxFieldType.Float));
        addNodeOutput(
                new DefaultGraphNodeOutput("particleDeath", "Particle death", false, GraphNodeOutputSide.Right, VfxParticleFieldType.ParticleProcess));
    }
}
