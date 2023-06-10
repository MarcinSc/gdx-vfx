package com.gempukku.libgdx.vfx;

import com.gempukku.libgdx.graph.GraphTypeRegistry;
import com.gempukku.libgdx.graph.plugin.PluginRuntimeInitializer;

public class VfxPluginRuntimeInitializer implements PluginRuntimeInitializer {
    @Override
    public void initialize() {
        // Register graph type
        VfxGraphType graphType = new VfxGraphType();
        GraphTypeRegistry.registerType(graphType);

        // Register field types

        // Register runtime VFX nodes

        // Register runtime VFX properties
    }

    @Override
    public void dispose() {

    }
}
