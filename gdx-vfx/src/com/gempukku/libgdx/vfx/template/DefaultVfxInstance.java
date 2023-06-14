package com.gempukku.libgdx.vfx.template;

import com.badlogic.gdx.utils.Array;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.VfxInstance;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;

public class DefaultVfxInstance implements VfxInstance {
    private Array<VfxRuntimeNode> nodes;
    private VfxEffectConfiguration configuration;

    public DefaultVfxInstance(Array<VfxRuntimeNode> nodes, VfxEffectConfiguration configuration) {
        this.nodes = nodes;
        this.configuration = configuration;
    }
}
