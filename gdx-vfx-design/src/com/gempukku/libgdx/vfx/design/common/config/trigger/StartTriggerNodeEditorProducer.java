package com.gempukku.libgdx.vfx.design.common.config.trigger;

import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditor;
import com.gempukku.libgdx.vfx.common.config.trigger.StartTriggerNodeConfiguration;
import com.gempukku.libgdx.vfx.design.ui.ProcessStartNodeEditorPart;
import com.gempukku.libgdx.vfx.design.ui.VfxGraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.VfxMenuNodeEditorProducer;

public class StartTriggerNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public StartTriggerNodeEditorProducer() {
        super(new StartTriggerNodeConfiguration());
    }

    @Override
    public GraphNodeEditor createNodeEditor(String nodeId, JsonValue data) {
        VfxGraphNodeEditor graphNodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        graphNodeEditor.addGraphBoxPart(new ProcessStartNodeEditorPart("trigger", "Process"));
        return graphNodeEditor;
    }
}
