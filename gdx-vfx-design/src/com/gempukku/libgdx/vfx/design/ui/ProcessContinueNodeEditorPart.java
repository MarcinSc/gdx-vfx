package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.common.Supplier;
import com.gempukku.libgdx.ui.graph.data.GraphNodeInputSide;
import com.gempukku.libgdx.ui.graph.data.GraphNodeOutputSide;
import com.gempukku.libgdx.ui.graph.editor.DefaultGraphNodeEditorInput;
import com.gempukku.libgdx.ui.graph.editor.DefaultGraphNodeEditorOutput;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditorInput;
import com.gempukku.libgdx.ui.graph.editor.GraphNodeEditorOutput;
import com.gempukku.libgdx.ui.graph.editor.part.GraphNodeEditorPart;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;

public class ProcessContinueNodeEditorPart extends VisTable implements GraphNodeEditorPart {
    private final String inputFieldId;
    private final String outputFieldId;

    public ProcessContinueNodeEditorPart(String inputFieldId, String outputFieldId, String label) {
        this.inputFieldId = inputFieldId;
        this.outputFieldId = outputFieldId;

        VisLabel processLabel = new VisLabel(label, "gdx-vfx-io-process");
        processLabel.setAlignment(Align.center);
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
                }, outputFieldId, VisUI.getSkin().getDrawable("vfx-process-right"),
                VisUI.getSkin().getDrawable("vfx-process-right-invalid"));
    }

    @Override
    public GraphNodeEditorInput getInputConnector() {
        return new DefaultGraphNodeEditorInput(GraphNodeInputSide.Left,
                new Supplier<Float>() {
                    @Override
                    public Float get() {
                        return getHeight() / 2;
                    }
                }, inputFieldId, VisUI.getSkin().getDrawable("vfx-process-left-required"),
                VisUI.getSkin().getDrawable("vfx-process-left-required-invalid"));
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
