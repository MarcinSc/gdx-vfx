package com.gempukku.libgdx.vfx.node;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public abstract class AbstractVfxNode implements VfxRuntimeNode {
    protected ObjectMap<String, Array<RelationshipConnection>> inputNodes;
    protected ObjectMap<String, Array<RelationshipConnection>> outputNodes;

    @Override
    public void initializeRelationships(ObjectMap<String, Array<RelationshipConnection>> inputNodes, ObjectMap<String, Array<RelationshipConnection>> outputNodes) {
        this.inputNodes = inputNodes;
        this.outputNodes = outputNodes;
    }
}
