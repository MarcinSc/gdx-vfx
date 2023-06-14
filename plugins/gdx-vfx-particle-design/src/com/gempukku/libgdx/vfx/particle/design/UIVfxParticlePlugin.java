package com.gempukku.libgdx.vfx.particle.design;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.gempukku.libgdx.graph.plugin.RuntimePluginRegistry;
import com.gempukku.libgdx.graph.ui.UIGdxGraphPlugin;
import com.gempukku.libgdx.vfx.design.UIVfxGraphConfiguration;
import com.gempukku.libgdx.vfx.particle.VfxParticlePlugin;
import com.gempukku.libgdx.vfx.particle.design.producer.SpriteRendererNodeEditorProducer;

public class UIVfxParticlePlugin implements UIGdxGraphPlugin {
    @Override
    public void initialize(FileHandleResolver assetResolver) {
        // Sprites
        UIVfxGraphConfiguration.register(new SpriteRendererNodeEditorProducer());

        // Register runtime plugin
        RuntimePluginRegistry.register(VfxParticlePlugin.class);
    }
}
