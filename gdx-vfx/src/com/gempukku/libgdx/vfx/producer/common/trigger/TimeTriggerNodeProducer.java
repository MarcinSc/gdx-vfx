package com.gempukku.libgdx.vfx.producer.common.trigger;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.graph.pipeline.time.TimeProvider;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.common.config.trigger.TimeTriggerNodeConfiguration;
import com.gempukku.libgdx.vfx.node.AbstractVfxNode;
import com.gempukku.libgdx.vfx.node.VfxProcessingNode;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;
import com.gempukku.libgdx.vfx.node.VfxTriggerNode;
import com.gempukku.libgdx.vfx.process.VfxProcess;
import com.gempukku.libgdx.vfx.producer.AbstractVfxNodeProducer;

public class TimeTriggerNodeProducer extends AbstractVfxNodeProducer {
    public TimeTriggerNodeProducer() {
        super(new TimeTriggerNodeConfiguration());
    }

    @Override
    public VfxRuntimeNode createNode(JsonValue data, ObjectMap<String, Array<String>> inputTypes, ObjectMap<String, String> outputTypes, final VfxEffectConfiguration configuration) {
        return new TimeTriggerNode(configuration, data.getFloat("time"));
    }

    private static class TimeTriggerNode extends AbstractVfxNode implements VfxTriggerNode {
        private final VfxEffectConfiguration configuration;
        private final float time;

        private boolean processed = false;

        public TimeTriggerNode(VfxEffectConfiguration configuration, float time) {
            this.configuration = configuration;
            this.time = time;
        }

        @Override
        public boolean isTriggered() {
            TimeProvider timeProvider = configuration.getEffectTimeProvider();
            return !processed && timeProvider.getTime()>=time && timeProvider.getTime()-timeProvider.getDelta()<time;
        }

        @Override
        public void processTrigger() {
            processed = true;

            VfxProcess vfxProcess = new VfxProcess(null);
            for (RelationshipConnection connection : outputNodes.get("trigger")) {
                ((VfxProcessingNode) connection.getNode()).process(connection.getFieldId(), vfxProcess);
            }
        }
    }
}
