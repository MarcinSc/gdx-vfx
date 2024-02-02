package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.graph.data.GraphWithProperties;
import com.gempukku.libgdx.graph.ui.graph.GraphChangedAware;
import com.gempukku.libgdx.ui.graph.GraphChangedEvent;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditorInput;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditorOutput;
import com.gempukku.libgdx.ui.graph.editor.part.GraphNodeEditorPart;
import com.kotcrab.vis.ui.widget.VisTable;

public class VfxGdxPreviewEditorPart extends VisTable implements GraphNodeEditorPart, GraphChangedAware {
    private final String propertyName;
    private VfxGdxPreview preview;

    public VfxGdxPreviewEditorPart(String propertyName, int width, int height) {
        this.propertyName = propertyName;

        preview = new VfxGdxPreview();
        add(preview).width(width).height(height).row();
    }

    @Override
    public GraphNodeEditorOutput getOutputConnector() {
        return null;
    }

    @Override
    public GraphNodeEditorInput getInputConnector() {
        return null;
    }

    @Override
    public Actor getActor() {
        return this;
    }

    @Override
    public void graphChanged(GraphChangedEvent event, GraphWithProperties graph) {
        if (event.isStructure() || event.isData()) {
            preview.graphChanged(graph);
        }
    }

    @Override
    public void initialize(JsonValue data) {
        if (data != null) {
            JsonValue value = (JsonValue) data.get(propertyName);
        }
    }

    @Override
    public void serializePart(JsonValue value) {
        JsonValue result = new JsonValue(JsonValue.ValueType.object);
        value.addChild(propertyName, result);
    }
}
