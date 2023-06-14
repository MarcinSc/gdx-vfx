package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.common.Supplier;
import com.gempukku.libgdx.ui.graph.data.NodeConfiguration;
import com.gempukku.libgdx.ui.graph.editor.*;
import com.gempukku.libgdx.ui.graph.editor.part.GraphNodeEditorPart;
import com.kotcrab.vis.ui.widget.VisTable;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class VfxGraphNodeEditor implements GraphNodeEditor {
    private NodeConfiguration configuration;
    private VisTable table;
    private List<GraphNodeEditorPart> editorParts = new LinkedList<>();
    private Map<String, GraphNodeEditorInput> inputConnectors = new HashMap<>();
    private Map<String, GraphNodeEditorOutput> outputConnectors = new HashMap<>();

    public VfxGraphNodeEditor(NodeConfiguration configuration) {
        this.configuration = configuration;
        table = new VisTable();
    }

    @Override
    public NodeConfiguration getConfiguration() {
        return configuration;
    }

    public void addGraphBoxPart(GraphNodeEditorPart graphBoxPart) {
        editorParts.add(graphBoxPart);
        final Actor actor = graphBoxPart.getActor();
        table.add(actor).growX().row();
        final GraphNodeEditorInput inputConnector = graphBoxPart.getInputConnector();
        if (inputConnector != null) {
            inputConnectors.put(inputConnector.getFieldId(),
                    new DefaultGraphNodeEditorInput(inputConnector.getSide(),
                            new Supplier<Float>() {
                                @Override
                                public Float get() {
                                    return actor.getY() + actor.getHeight() / 2f;
                                }
                            },
                            inputConnector.getFieldId(), inputConnector.getConnectorDrawable(true), inputConnector.getConnectorDrawable(false)));
        }
        final GraphNodeEditorOutput outputConnector = graphBoxPart.getOutputConnector();
        if (outputConnector != null) {
            outputConnectors.put(outputConnector.getFieldId(),
                    new DefaultGraphNodeEditorOutput(outputConnector.getSide(),
                            new Supplier<Float>() {
                                @Override
                                public Float get() {
                                    return actor.getY() + actor.getHeight() / 2f;
                                }
                            },
                            outputConnector.getFieldId(), outputConnector.getConnectorDrawable(true), outputConnector.getConnectorDrawable(false)));
        }
    }

    @Override
    public Map<String, GraphNodeEditorInput> getInputs() {
        return inputConnectors;
    }

    @Override
    public Map<String, GraphNodeEditorOutput> getOutputs() {
        return outputConnectors;
    }

    @Override
    public Actor getActor() {
        return table;
    }

    public void initialize(JsonValue data) {
        for (GraphNodeEditorPart editorPart : editorParts) {
            editorPart.initialize(data);
        }
    }

    @Override
    public JsonValue getData() {
        JsonValue result = new JsonValue(JsonValue.ValueType.object);

        for (GraphNodeEditorPart graphBoxPart : editorParts)
            graphBoxPart.serializePart(result);

        if (result.isEmpty())
            return null;
        return result;
    }
}
