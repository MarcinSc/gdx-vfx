package com.gempukku.libgdx.vfx.design.common.config.trigger;

import com.gempukku.libgdx.vfx.common.config.trigger.StartTriggerNodeConfiguration;
import com.gempukku.libgdx.vfx.design.ui.ProcessStartNodeEditorPart;
import com.gempukku.libgdx.vfx.design.ui.VfxGraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.VfxMenuNodeEditorProducer;

public class StartTriggerNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public StartTriggerNodeEditorProducer() {
        super(new StartTriggerNodeConfiguration());
    }

    @Override
    protected VfxGraphNodeEditor createNodeEditor(String nodeId) {
        VfxGraphNodeEditor graphNodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        graphNodeEditor.addGraphBoxPart(new ProcessStartNodeEditorPart("trigger", "Process"));
        return graphNodeEditor;
    }
}
