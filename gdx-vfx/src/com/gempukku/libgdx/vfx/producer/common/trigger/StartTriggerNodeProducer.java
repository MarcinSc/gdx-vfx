package com.gempukku.libgdx.vfx.producer.common.trigger;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.common.config.trigger.StartTriggerNodeConfiguration;
import com.gempukku.libgdx.vfx.node.AbstractVfxNode;
import com.gempukku.libgdx.vfx.node.VfxProcessingNode;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;
import com.gempukku.libgdx.vfx.node.VfxTriggerNode;
import com.gempukku.libgdx.vfx.process.VfxProcess;
import com.gempukku.libgdx.vfx.producer.AbstractVfxNodeProducer;

public class StartTriggerNodeProducer extends AbstractVfxNodeProducer {
    public StartTriggerNodeProducer() {
        super(new StartTriggerNodeConfiguration());
    }

    @Override
    public VfxRuntimeNode createNode(JsonValue data, ObjectMap<String, Array<String>> inputTypes, ObjectMap<String, String> outputTypes, VfxEffectConfiguration configuration) {
        return new StartTriggerNode();
    }

    private static class StartTriggerNode extends AbstractVfxNode implements VfxTriggerNode{
        private boolean processed = false;

        @Override
        public boolean isTriggered() {
            return !processed;
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
