package com.gempukku.libgdx.vfx.template;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.gempukku.libgdx.graph.pipeline.time.TimeProvider;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.VfxInstance;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;
import com.gempukku.libgdx.vfx.node.VfxTriggerNode;
import com.gempukku.libgdx.vfx.node.VfxUpdatingNode;

public class DefaultVfxInstance implements VfxInstance {
    private Array<VfxRuntimeNode> nodes;
    private VfxEffectConfiguration configuration;

    private Array<VfxTriggerNode> triggerNodes = new Array<>();
    private Array<VfxUpdatingNode> updatingNodes = new Array<>();

    public DefaultVfxInstance(Array<VfxRuntimeNode> nodes, VfxEffectConfiguration configuration) {
        this.nodes = nodes;
        this.configuration = configuration;

        for (VfxRuntimeNode node : nodes) {
            if (node instanceof VfxTriggerNode)
                triggerNodes.add((VfxTriggerNode) node);
            if (node instanceof VfxUpdatingNode)
                updatingNodes.add((VfxUpdatingNode) node);
        }
    }

    public void process() {
        TimeProvider renderTimeProvider = configuration.getRenderTimeProvider();
        configuration.getEffectTimeKeeper().updateTime(renderTimeProvider.getDelta());

        for (VfxTriggerNode triggerNode : triggerNodes) {
            if (triggerNode.isTriggered())
                triggerNode.processTrigger();
        }

        for (VfxUpdatingNode updatingNode : updatingNodes) {
            updatingNode.update();
        }
    }

    public void stop() {
        for (VfxRuntimeNode node : nodes) {
            if (node instanceof Disposable)
                ((Disposable) node).dispose();
        }
        nodes.clear();
    }
}
