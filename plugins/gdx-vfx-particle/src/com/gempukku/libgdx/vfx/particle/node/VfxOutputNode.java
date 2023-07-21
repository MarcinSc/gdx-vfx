package com.gempukku.libgdx.vfx.particle.node;

import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;

public interface VfxOutputNode<OutputType, Identifier> extends VfxRuntimeNode {
    Identifier create(String fieldId, OutputType output);

    void update(String fieldId, Identifier identifier, OutputType output);

    void remove(String fieldId, Identifier identifier);
}
