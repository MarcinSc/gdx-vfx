package com.gempukku.libgdx.vfx.design.common.config.trigger;

import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditor;
import com.gempukku.libgdx.ui.graph.editor.part.FloatEditorPart;
import com.gempukku.libgdx.vfx.common.config.trigger.TimeTriggerNodeConfiguration;
import com.gempukku.libgdx.vfx.design.ui.ProcessStartNodeEditorPart;
import com.gempukku.libgdx.vfx.design.ui.VfxGraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.VfxMenuNodeEditorProducer;
import com.kotcrab.vis.ui.util.Validators;

public class TimeTriggerNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public TimeTriggerNodeEditorProducer() {
        super(new TimeTriggerNodeConfiguration());
    }

    @Override
    public GraphNodeEditor createNodeEditor(String nodeId, JsonValue data) {
        VfxGraphNodeEditor graphNodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        graphNodeEditor.addGraphBoxPart(new ProcessStartNodeEditorPart("trigger", "Process"));
        graphNodeEditor.addGraphBoxPart(new FloatEditorPart("Time: ", "time", 1f,
                new Validators.GreaterThanValidator(0, false), "gdx-vfx-field-label", "gdx-vfx-field-property"));
        return graphNodeEditor;
    }
}
