package com.gempukku.libgdx.vfx.producer.common;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.common.config.VfxPreviewNodeConfiguration;
import com.gempukku.libgdx.vfx.node.AbstractVfxNode;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;
import com.gempukku.libgdx.vfx.producer.AbstractVfxNodeProducer;

public class VfxPreviewNodeProducer extends AbstractVfxNodeProducer {
    public VfxPreviewNodeProducer() {
        super(new VfxPreviewNodeConfiguration());
    }

    @Override
    public VfxRuntimeNode createNode(JsonValue data, ObjectMap<String, Array<String>> inputTypes, ObjectMap<String, String> outputTypes, VfxEffectConfiguration configuration) {
        return new VfxPreviewNode();
    }

    private static class VfxPreviewNode extends AbstractVfxNode {
    }
}
