package com.gempukku.libgdx.vfx.common.config.trigger;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.graph.config.DefaultMenuNodeConfiguration;
import com.gempukku.libgdx.vfx.VfxFieldType;
import com.gempukku.libgdx.vfx.config.ValueFunction;
import com.gempukku.libgdx.vfx.config.VfxGraphNodeOutput;

public class TimeTriggerNodeConfiguration extends DefaultMenuNodeConfiguration {
    public TimeTriggerNodeConfiguration() {
        super("TimeTrigger", "Time Trigger", "Triggers");
        addNodeOutput(
                new VfxGraphNodeOutput("trigger", "Process", true, false,
                        new ValueFunction<ObjectMap<String, Array<String>>, String>(VfxFieldType.Process), VfxFieldType.Process));
    }
}
