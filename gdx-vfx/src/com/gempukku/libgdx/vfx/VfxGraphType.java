package com.gempukku.libgdx.vfx;

import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.common.BiFunction;
import com.gempukku.libgdx.graph.GraphType;
import com.gempukku.libgdx.graph.data.GraphWithProperties;
import com.gempukku.libgdx.ui.graph.data.Graph;
import com.gempukku.libgdx.ui.graph.data.NodeConfiguration;
import com.gempukku.libgdx.ui.graph.validator.*;
import com.gempukku.libgdx.vfx.template.DefaultVfxTemplate;

public class VfxGraphType implements GraphType {
    public static final String TYPE = "VFX";

    private GraphValidator graphValidator;

    public VfxGraphType() {
        BiFunction<String, JsonValue, NodeConfiguration> nodeConfigurationResolver =
                new BiFunction<String, JsonValue, NodeConfiguration>() {
                    @Override
                    public NodeConfiguration evaluate(String nodeType, JsonValue data) {
                        VfxRuntimeNodeProducer producer = VfxGraphConfiguration.findProducer(nodeType);
                        if (producer == null)
                            return null;
                        return producer.getConfiguration(data);
                    }
                };

        DAGValidator dagValidator = new DAGValidator();
        SumGraphValidator sumValidator = new SumGraphValidator(
                new RequiredValidator(nodeConfigurationResolver),
                new MultipleConnectionsValidator(nodeConfigurationResolver),
                new FieldTypeValidator(nodeConfigurationResolver));
        graphValidator = new SerialGraphValidator(dagValidator, sumValidator);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public GraphValidationResult validateGraph(Graph graph) {
        return graphValidator.validateGraph(graph);
    }

    public VfxTemplate buildVfxTemplate(GraphWithProperties graph, boolean designTime) {
        return new DefaultVfxTemplate(graph, designTime);
    }
}
