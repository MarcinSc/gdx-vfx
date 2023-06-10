package com.gempukku.libgdx.vfx.config;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.common.Function;
import com.gempukku.libgdx.ui.graph.data.GraphNodeOutput;
import com.gempukku.libgdx.ui.graph.data.GraphNodeOutputSide;

public class VfxGraphNodeOutput implements GraphNodeOutput {
    private final String id;
    private final String name;
    private final boolean required;
    private final boolean acceptsMultiple;
    private final Array<String> outputTypes;
    private final Function<ObjectMap<String, Array<String>>, String> outputTypeFunction;

    public VfxGraphNodeOutput(String id, String name, boolean required, boolean acceptsMultiple,
                              Function<ObjectMap<String, Array<String>>, String> outputTypeFunction, String... outputType) {

        this.id = id;
        this.name = name;
        this.required = required;
        this.acceptsMultiple = acceptsMultiple;
        this.outputTypes = new Array<>(outputType);
        this.outputTypeFunction = outputTypeFunction;
    }

    @Override
    public GraphNodeOutputSide getSide() {
        return GraphNodeOutputSide.Right;
    }

    @Override
    public String determineFieldType(ObjectMap<String, Array<String>> inputs) {
        return outputTypeFunction.evaluate(inputs);
    }

    @Override
    public Array<String> getConnectableFieldTypes() {
        return outputTypes;
    }

    @Override
    public boolean isRequired() {
        return required;
    }

    @Override
    public boolean acceptsMultipleConnections() {
        return acceptsMultiple;
    }

    @Override
    public String getFieldName() {
        return name;
    }

    @Override
    public String getFieldId() {
        return id;
    }
}
