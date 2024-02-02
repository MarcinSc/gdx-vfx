package com.gempukku.libgdx.vfx.config;

import com.gempukku.libgdx.common.Predicate;
import com.gempukku.libgdx.ui.graph.data.GraphNodeInputSide;
import com.gempukku.libgdx.ui.graph.data.impl.NamedGraphNodeInput;

public class VfxGraphNodeInput implements NamedGraphNodeInput {
    private final String id;
    private final String name;
    private final boolean required;
    private final boolean acceptsMultiple;
    private final Predicate<String> acceptableTypePredicate;

    public VfxGraphNodeInput(String id, String name, boolean required, boolean acceptsMultiple,
                             Predicate<String> acceptableTypePredicate) {

        this.id = id;
        this.name = name;
        this.required = required;
        this.acceptsMultiple = acceptsMultiple;
        this.acceptableTypePredicate = acceptableTypePredicate;
    }

    @Override
    public GraphNodeInputSide getSide() {
        return GraphNodeInputSide.Left;
    }

    @Override
    public boolean acceptsFieldType(String fieldType) {
        return acceptableTypePredicate.test(fieldType);
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
