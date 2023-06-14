package com.gempukku.libgdx.vfx.node;

public interface VfxTriggerNode extends VfxRuntimeNode {
    boolean isTriggered();
    void processTrigger();
}
