package com.gempukku.libgdx.vfx.producer.common;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.common.config.LatchNodeConfiguration;
import com.gempukku.libgdx.vfx.node.AbstractVfxNode;
import com.gempukku.libgdx.vfx.node.VfxProcessingNode;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;
import com.gempukku.libgdx.vfx.node.VfxValueNode;
import com.gempukku.libgdx.vfx.process.VfxProcess;
import com.gempukku.libgdx.vfx.producer.AbstractVfxNodeProducer;

public class LatchNodeProducer extends AbstractVfxNodeProducer {
    public LatchNodeProducer() {
        super(new LatchNodeConfiguration());
    }

    @Override
    public VfxRuntimeNode createNode(JsonValue data, ObjectMap<String, Array<String>> inputTypes, ObjectMap<String, String> outputTypes, VfxEffectConfiguration configuration) {
        return new LatchNode();
    }

    private static class LatchNode extends AbstractVfxNode implements VfxProcessingNode, VfxValueNode {
        private boolean value;

        @Override
        public void process(String fieldId, VfxProcess process) {
            if (fieldId.equals("set")) {
                value = true;
                for (RelationshipConnection connection : outputNodes.get("setOutput")) {
                    ((VfxProcessingNode) connection.getNode()).process(connection.getFieldId(), process);
                }
            } else if (fieldId.equals("reset")) {
                value = false;
                for (RelationshipConnection connection : outputNodes.get("resetOutput")) {
                    ((VfxProcessingNode) connection.getNode()).process(connection.getFieldId(), process);
                }
            }
        }

        @Override
        public Object getValue(String fieldId) {
            if (fieldId.equals("value"))
                return value;
            return null;
        }
    }
}
