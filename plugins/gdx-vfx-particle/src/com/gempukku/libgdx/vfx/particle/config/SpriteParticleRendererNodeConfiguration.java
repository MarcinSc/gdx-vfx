package com.gempukku.libgdx.vfx.particle.config;

import com.gempukku.libgdx.graph.config.DefaultMenuNodeConfiguration;
import com.gempukku.libgdx.ui.graph.data.impl.DefaultGraphNodeInput;
import com.gempukku.libgdx.vfx.particle.VfxParticleFieldType;

public class SpriteParticleRendererNodeConfiguration extends DefaultMenuNodeConfiguration {
    public SpriteParticleRendererNodeConfiguration() {
        super("SpriteParticleRendering", "Sprite Particles Rendering", "Rendering");
        addNodeInput(
                new DefaultGraphNodeInput("input", "Sprite Particles", true, VfxParticleFieldType.ParticleOutput));
    }
}
