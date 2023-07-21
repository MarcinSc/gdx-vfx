package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.common.Supplier;
import com.gempukku.libgdx.ui.graph.data.GraphNodeOutputSide;
import com.gempukku.libgdx.ui.graph.editor.DefaultGraphNodeEditorOutput;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditorInput;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditorOutput;
import com.gempukku.libgdx.ui.graph.editor.part.GraphNodeEditorPart;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class OutputNodeEditorPart extends VisTable implements GraphNodeEditorPart {
    private final String fieldId;
    private final boolean required;

    public OutputNodeEditorPart(String fieldId, String label) {
        this(fieldId, label, false);
    }

    public OutputNodeEditorPart(String fieldId, String label, boolean required) {
        this.fieldId = fieldId;
        this.required = required;
        VisLabel outputLabel = new VisLabel(label, "gdx-vfx-io-value");
        outputLabel.setAlignment(Align.right);
        add(outputLabel).growX();
    }

    @Override
    public GraphNodeEditorOutput getOutputConnector() {
        String drawable = "vfx-value-right" + (required ? "-required" : "");

        return new DefaultGraphNodeEditorOutput(
                GraphNodeOutputSide.Right, new Supplier<Float>() {
            @Override
            public Float get() {
                return getHeight() / 2;
            }
        }, fieldId, VisUI.getSkin().getDrawable(drawable),
                VisUI.getSkin().getDrawable(drawable + "-invalid"));
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
    public void initialize(JsonValue data) {

    }

    @Override
    public void serializePart(JsonValue value) {

    }
}
