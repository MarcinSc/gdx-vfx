package com.gempukku.libgdx.vfx;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.ui.graph.data.NodeConfiguration;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;

public interface VfxRuntimeNodeProducer {
    String getType();

    NodeConfiguration getConfiguration(JsonValue data);

    ObjectMap<String, String> getOutputTypes(JsonValue data, ObjectMap<String, Array<String>> inputTypes);

    VfxRuntimeNode createNode(JsonValue data,
                              ObjectMap<String, Array<String>> inputTypes, ObjectMap<String, String> outputTypes,
                              VfxEffectConfiguration configuration);
}
