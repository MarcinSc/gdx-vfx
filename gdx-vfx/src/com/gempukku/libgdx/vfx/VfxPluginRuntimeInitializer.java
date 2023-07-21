package com.gempukku.libgdx.vfx;

import com.gempukku.libgdx.graph.GraphTypeRegistry;
import com.gempukku.libgdx.graph.plugin.PluginRuntimeInitializer;
import com.gempukku.libgdx.vfx.producer.common.LatchNodeProducer;
import com.gempukku.libgdx.vfx.producer.common.VfxPreviewNodeProducer;
import com.gempukku.libgdx.vfx.producer.common.trigger.StartTriggerNodeProducer;
import com.gempukku.libgdx.vfx.producer.common.trigger.TimeTriggerNodeProducer;

public class VfxPluginRuntimeInitializer implements PluginRuntimeInitializer {
    @Override
    public void initialize() {
        // Register graph type
        VfxGraphType graphType = new VfxGraphType();
        GraphTypeRegistry.registerType(graphType);

        // Register field types
        VfxFieldTypeRegistry.registerVFXFieldType(new BasicVfxFieldType(VfxFieldType.Float));
        VfxFieldTypeRegistry.registerVFXFieldType(new BasicVfxFieldType(VfxFieldType.Vector2));
        VfxFieldTypeRegistry.registerVFXFieldType(new BasicVfxFieldType(VfxFieldType.Vector3));
        VfxFieldTypeRegistry.registerVFXFieldType(new BasicVfxFieldType(VfxFieldType.Process));

        // Register runtime VFX nodes
        VfxGraphConfiguration.register(new StartTriggerNodeProducer());
        VfxGraphConfiguration.register(new TimeTriggerNodeProducer());
        VfxGraphConfiguration.register(new LatchNodeProducer());
        VfxGraphConfiguration.register(new VfxPreviewNodeProducer());

        // Register runtime VFX properties
    }

    @Override
    public void dispose() {

    }
}
