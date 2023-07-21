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

public class ProcessStartNodeEditorPart extends VisTable implements GraphNodeEditorPart {
    private String fieldId;

    public ProcessStartNodeEditorPart(String fieldId, String label) {
        this.fieldId = fieldId;

        VisLabel processLabel = new VisLabel(label, "gdx-vfx-io-process");
        processLabel.setAlignment(Align.right);
        add(processLabel).growX();
    }

    @Override
    public GraphNodeEditorOutput getOutputConnector() {
        return new DefaultGraphNodeEditorOutput(GraphNodeOutputSide.Right,
                new Supplier<Float>() {
                    @Override
                    public Float get() {
                        return getHeight() / 2;
                    }
                }, fieldId, VisUI.getSkin().getDrawable("vfx-process-right-required"),
                VisUI.getSkin().getDrawable("vfx-process-right-required-invalid"));
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
