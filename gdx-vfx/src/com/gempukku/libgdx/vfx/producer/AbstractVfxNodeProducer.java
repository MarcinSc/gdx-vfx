package com.gempukku.libgdx.vfx.producer;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.ui.graph.data.GraphNodeOutput;
import com.gempukku.libgdx.ui.graph.data.NodeConfiguration;
import com.gempukku.libgdx.vfx.VfxRuntimeNodeProducer;

public abstract class AbstractVfxNodeProducer implements VfxRuntimeNodeProducer {
    protected NodeConfiguration nodeConfiguration;

    public AbstractVfxNodeProducer(NodeConfiguration nodeConfiguration) {
        this.nodeConfiguration = nodeConfiguration;
    }

    @Override
    public String getType() {
        return nodeConfiguration.getType();
    }

    @Override
    public NodeConfiguration getConfiguration(JsonValue data) {
        return nodeConfiguration;
    }

    @Override
    public ObjectMap<String, String> getOutputTypes(JsonValue data, ObjectMap<String, Array<String>> inputTypes) {
        ObjectMap<String, String> result = new ObjectMap<>();
        for (ObjectMap.Entry<String, GraphNodeOutput> nodeOutput : nodeConfiguration.getNodeOutputs()) {
            String outputType = nodeOutput.value.determineFieldType(inputTypes);
            result.put(nodeOutput.key, outputType);
        }

        return result;
    }
}
