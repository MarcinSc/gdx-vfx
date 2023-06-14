package com.gempukku.libgdx.vfx.particle;

import com.gempukku.libgdx.graph.plugin.PluginRuntimeInitializer;
import com.gempukku.libgdx.vfx.VfxGraphConfiguration;
import com.gempukku.libgdx.vfx.particle.producer.SpriteParticleRendererNodeProducer;

public class VfxParticlePlugin implements PluginRuntimeInitializer {
    @Override
    public void initialize() {
        // Register runtime VFX nodes
        VfxGraphConfiguration.register(new SpriteParticleRendererNodeProducer());

        // Register runtime VFX properties
    }

    @Override
    public void dispose() {

    }
}
