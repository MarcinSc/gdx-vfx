package com.gempukku.libgdx.vfx.design.common.config;

import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditor;
import com.gempukku.libgdx.vfx.common.config.LatchNodeConfiguration;
import com.gempukku.libgdx.vfx.design.ui.OutputNodeEditorPart;
import com.gempukku.libgdx.vfx.design.ui.ProcessContinueNodeEditorPart;
import com.gempukku.libgdx.vfx.design.ui.VfxGraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.VfxMenuNodeEditorProducer;

public class LatchNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public LatchNodeEditorProducer() {
        super(new LatchNodeConfiguration());
    }

    @Override
    public GraphNodeEditor createNodeEditor(String nodeId, JsonValue data) {
        VfxGraphNodeEditor graphNodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        graphNodeEditor.addGraphBoxPart(new ProcessContinueNodeEditorPart("set", "setOutput", "Set"));
        graphNodeEditor.addGraphBoxPart(new ProcessContinueNodeEditorPart("reset", "resetOutput", "Reset"));
        graphNodeEditor.addGraphBoxPart(new OutputNodeEditorPart("value", "Value"));
        return graphNodeEditor;
    }
}
