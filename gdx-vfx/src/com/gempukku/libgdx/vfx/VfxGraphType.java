package com.gempukku.libgdx.vfx;

import com.gempukku.libgdx.graph.GraphType;
import com.gempukku.libgdx.graph.data.GraphWithProperties;
import com.gempukku.libgdx.ui.graph.data.Graph;
import com.gempukku.libgdx.ui.graph.validator.DefaultGraphValidationResult;
import com.gempukku.libgdx.ui.graph.validator.GraphValidationResult;
import com.gempukku.libgdx.vfx.template.DefaultVfxTemplate;

public class VfxGraphType implements GraphType {
    public static final String TYPE = "VFX";

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public GraphValidationResult validateGraph(Graph graph) {
        return new DefaultGraphValidationResult();
    }

    public VfxTemplate buildVfxTemplate(GraphWithProperties graph, boolean designTime) {
        return new DefaultVfxTemplate(graph, designTime);
    }
}
