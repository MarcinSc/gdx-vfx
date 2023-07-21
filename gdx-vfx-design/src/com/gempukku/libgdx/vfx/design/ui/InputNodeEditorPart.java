package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.common.Supplier;
import com.gempukku.libgdx.ui.graph.data.GraphNodeInputSide;
import com.gempukku.libgdx.ui.graph.editor.DefaultGraphNodeEditorInput;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditorInput;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditorOutput;
import com.gempukku.libgdx.ui.graph.editor.part.GraphNodeEditorPart;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class InputNodeEditorPart extends VisTable implements GraphNodeEditorPart {
    private final String fieldId;
    private final boolean required;

    public InputNodeEditorPart(String fieldId, String label) {
        this(fieldId, label, false);
    }

    public InputNodeEditorPart(String fieldId, String label, boolean required) {
        this.fieldId = fieldId;
        this.required = required;
        add(new VisLabel(label, "gdx-vfx-io-value")).growX();
    }

    @Override
    public GraphNodeEditorOutput getOutputConnector() {
        return null;
    }

    @Override
    public GraphNodeEditorInput getInputConnector() {
        String drawable = "vfx-value-left" + (required ? "-required" : "");

        return new DefaultGraphNodeEditorInput(
                GraphNodeInputSide.Left, new Supplier<Float>() {
            @Override
            public Float get() {
                return getHeight() / 2;
            }
        }, fieldId, VisUI.getSkin().getDrawable(drawable),
                VisUI.getSkin().getDrawable(drawable + "-invalid"));
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
