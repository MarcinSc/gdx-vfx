package com.gempukku.libgdx.vfx.template;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.gempukku.libgdx.graph.pipeline.time.TimeProvider;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.VfxInstance;
import com.gempukku.libgdx.vfx.node.VfxFinishingNode;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;
import com.gempukku.libgdx.vfx.node.VfxTriggerNode;
import com.gempukku.libgdx.vfx.node.VfxUpdatingNode;

public class DefaultVfxInstance implements VfxInstance {
    private Array<VfxRuntimeNode> nodes;
    private VfxEffectConfiguration configuration;
    private boolean stopped = false;

    private Array<VfxTriggerNode> triggerNodes = new Array<>();
    private Array<VfxUpdatingNode> updatingNodes = new Array<>();
    private Array<VfxFinishingNode> finishingNodes = new Array<>();

    public DefaultVfxInstance(Array<VfxRuntimeNode> nodes, VfxEffectConfiguration configuration) {
        this.nodes = new Array<>(nodes);
        this.configuration = configuration;

        for (VfxRuntimeNode node : nodes) {
            if (node instanceof VfxTriggerNode)
                triggerNodes.add((VfxTriggerNode) node);
            if (node instanceof VfxUpdatingNode)
                updatingNodes.add((VfxUpdatingNode) node);
            if (node instanceof VfxFinishingNode)
                finishingNodes.add((VfxFinishingNode) node);
        }
    }

    @Override
    public boolean process() {
        TimeProvider renderTimeProvider = configuration.getRenderTimeProvider();
        configuration.getEffectTimeKeeper().updateTime(renderTimeProvider.getDelta());

        for (VfxTriggerNode triggerNode : triggerNodes) {
            if (triggerNode.isTriggered())
                triggerNode.processTrigger();
        }

        for (VfxUpdatingNode updatingNode : updatingNodes) {
            updatingNode.update();
        }

        boolean finished = stopped;
        for (VfxFinishingNode finishingNode : finishingNodes) {
            finished |= finishingNode.isFinished();
        }
        return finished;
    }

    public void stop() {
        stopped = true;
        for (VfxRuntimeNode node : nodes) {
            if (node instanceof Disposable)
                ((Disposable) node).dispose();
        }
        nodes.clear();
    }

    @Override
    public void dispose() {
        stop();
    }
}
