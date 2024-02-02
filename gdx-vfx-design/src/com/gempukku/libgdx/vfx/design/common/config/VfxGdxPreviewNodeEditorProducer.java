package com.gempukku.libgdx.vfx.design.common.config;

import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.common.Consumer;
import com.gempukku.libgdx.vfx.design.ui.VfxGdxPreviewEditorPart;
import com.gempukku.libgdx.vfx.design.ui.VfxGraphNodeEditor;
import com.gempukku.libgdx.vfx.design.ui.VfxMenuNodeEditorProducer;

public class VfxGdxPreviewNodeEditorProducer extends VfxMenuNodeEditorProducer {
    public VfxGdxPreviewNodeEditorProducer() {
        super(new VfxGdxPreviewNodeConfiguration());
    }

    @Override
    protected VfxGraphNodeEditor createNodeEditor(String nodeId) {
        VfxGraphNodeEditor nodeEditor = new VfxGraphNodeEditor(nodeConfiguration);
        nodeEditor.addJsonProducingConsumer(
                new Consumer<JsonValue>() {
                    @Override
                    public void consume(JsonValue jsonValue) {
                        jsonValue.addChild("ignoreMissing", new JsonValue(true));
                    }
                });

        nodeEditor.addGraphBoxPart(new VfxGdxPreviewEditorPart("preview", 300, 300));

        return nodeEditor;
    }
}
