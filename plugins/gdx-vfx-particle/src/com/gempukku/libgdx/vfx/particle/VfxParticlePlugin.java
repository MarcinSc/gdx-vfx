package com.gempukku.libgdx.vfx.particle;

import com.gempukku.libgdx.graph.plugin.PluginRuntimeInitializer;
import com.gempukku.libgdx.vfx.BasicVfxFieldType;
import com.gempukku.libgdx.vfx.VfxFieldType;
import com.gempukku.libgdx.vfx.VfxFieldTypeRegistry;
import com.gempukku.libgdx.vfx.VfxGraphConfiguration;
import com.gempukku.libgdx.vfx.particle.producer.ParticleEmitterNodeProducer;
import com.gempukku.libgdx.vfx.particle.producer.SpriteParticleRendererNodeProducer;

public class VfxParticlePlugin implements PluginRuntimeInitializer {
    @Override
    public void initialize() {
        // Register field types
        VfxFieldTypeRegistry.registerVFXFieldType(new BasicVfxFieldType(VfxParticleFieldType.ParticleOutput));
        VfxFieldTypeRegistry.registerVFXFieldType(new BasicVfxFieldType(VfxParticleFieldType.ParticleProcess));
        VfxFieldTypeRegistry.registerExtends(
                VfxFieldTypeRegistry.findVFXFieldType(VfxParticleFieldType.ParticleProcess),
                VfxFieldTypeRegistry.findVFXFieldType(VfxFieldType.Process));

        // Register runtime VFX nodes
        VfxGraphConfiguration.register(new SpriteParticleRendererNodeProducer());
        VfxGraphConfiguration.register(new ParticleEmitterNodeProducer());

        // Register runtime VFX properties
    }

    @Override
    public void dispose() {

    }
}
