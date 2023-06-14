package com.gempukku.libgdx.vfx.node;

public interface VfxOutputNode<OutputType, Identifier> extends VfxRuntimeNode {
    Identifier create(String fieldId, OutputType output);

    void update(String fieldId, Identifier identifier, OutputType output);

    void remove(String fieldId, Identifier identifier);
}
