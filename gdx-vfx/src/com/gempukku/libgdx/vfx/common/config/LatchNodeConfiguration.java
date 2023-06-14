package com.gempukku.libgdx.vfx.common.config;

import com.gempukku.libgdx.graph.config.DefaultMenuNodeConfiguration;
import com.gempukku.libgdx.ui.graph.data.GraphNodeOutputSide;
import com.gempukku.libgdx.ui.graph.data.impl.DefaultGraphNodeInput;
import com.gempukku.libgdx.ui.graph.data.impl.DefaultGraphNodeOutput;
import com.gempukku.libgdx.vfx.VfxFieldType;
import com.gempukku.libgdx.vfx.VfxFieldTypeRegistry;
import com.gempukku.libgdx.vfx.config.SameAsInputFunction;
import com.gempukku.libgdx.vfx.predicate.IsOfTypePredicate;

public class LatchNodeConfiguration extends DefaultMenuNodeConfiguration {
    public LatchNodeConfiguration() {
        super("Latch", "Latch", "Logic");
        addNodeInput(
                new DefaultGraphNodeInput("set", "Set", true, new IsOfTypePredicate(VfxFieldType.Process)));
        addNodeInput(
                new DefaultGraphNodeInput("reset", "Reset", true, new IsOfTypePredicate(VfxFieldType.Process)));
        addNodeOutput(
                new DefaultGraphNodeOutput("setOutput", "Set", false, new SameAsInputFunction("set"), VfxFieldTypeRegistry.ext(VfxFieldType.Process)));
        addNodeOutput(
                new DefaultGraphNodeOutput("resetOutput", "Set", false, new SameAsInputFunction("reset"), VfxFieldTypeRegistry.ext(VfxFieldType.Process)));
        addNodeOutput(
                new DefaultGraphNodeOutput("value", "Value", true, GraphNodeOutputSide.Right, VfxFieldType.Boolean));
    }
}
