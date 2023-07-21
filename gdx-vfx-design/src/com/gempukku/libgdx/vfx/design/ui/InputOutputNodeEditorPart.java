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

public class InputOutputNodeEditorPart extends VisTable implements GraphNodeEditorPart {
    private final String inputFieldId;
    private final boolean inputRequired;
    private final String outputFieldId;
    private final boolean outputRequired;

    public InputOutputNodeEditorPart(
            String inputFieldId, String inputLabel, boolean inputRequired,
            String outputFieldId, String outputLabel, boolean outputRequired) {
        this.inputFieldId = inputFieldId;
        this.inputRequired = inputRequired;
        this.outputFieldId = outputFieldId;
        this.outputRequired = outputRequired;

        add(new VisLabel(inputLabel, "gdx-vfx-io-value")).left().growX();
        VisLabel labelActor = new VisLabel(outputLabel, "gdx-vfx-io-value");
        labelActor.setAlignment(Align.right);
        add(labelActor).right().growX();
    }

    @Override
    public GraphNodeEditorInput getInputConnector() {
        String drawable = "vfx-value-left" + (inputRequired ? "-required" : "");

        return new DefaultGraphNodeEditorInput(
                GraphNodeInputSide.Left, new Supplier<Float>() {
            @Override
            public Float get() {
                return getHeight() / 2;
            }
        }, inputFieldId, VisUI.getSkin().getDrawable(drawable),
                VisUI.getSkin().getDrawable(drawable + "-invalid"));
    }

    @Override
    public GraphNodeEditorOutput getOutputConnector() {
        String drawable = "vfx-value-right" + (outputRequired ? "-required" : "");

        return new DefaultGraphNodeEditorOutput(
                GraphNodeOutputSide.Right, new Supplier<Float>() {
            @Override
            public Float get() {
                return getHeight() / 2;
            }
        }, outputFieldId, VisUI.getSkin().getDrawable(drawable),
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
