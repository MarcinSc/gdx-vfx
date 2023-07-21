package com.gempukku.libgdx.vfx.design.common.config;

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
    protected VfxGraphNodeEditor createNodeEditor(String nodeId) {
        VfxGraphNodeEditor graphNodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        graphNodeEditor.addGraphBoxPart(new ProcessContinueNodeEditorPart("set", "setOutput", "Set"));
        graphNodeEditor.addGraphBoxPart(new ProcessContinueNodeEditorPart("reset", "resetOutput", "Reset"));
        graphNodeEditor.addGraphBoxPart(new OutputNodeEditorPart("value", "Value", true));
        return graphNodeEditor;
    }
}
