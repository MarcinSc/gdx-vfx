package com.gempukku.libgdx.vfx.node;

import com.gempukku.libgdx.vfx.process.VfxProcess;

public interface VfxProcessingNode extends VfxRuntimeNode {
    void process(String fieldId, VfxProcess process);
}
