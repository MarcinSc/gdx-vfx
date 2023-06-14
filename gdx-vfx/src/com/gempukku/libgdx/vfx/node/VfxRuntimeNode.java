package com.gempukku.libgdx.vfx.node;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public interface VfxRuntimeNode {
    void initializeRelationships(ObjectMap<String, Array<RelationshipConnection>> inputNodes, ObjectMap<String, Array<RelationshipConnection>> outputNodes);

    class RelationshipConnection {
        private String fieldId;
        private VfxRuntimeNode node;

        public RelationshipConnection(String fieldId, VfxRuntimeNode node) {
            this.fieldId = fieldId;
            this.node = node;
        }

        public String getFieldId() {
            return fieldId;
        }

        public VfxRuntimeNode getNode() {
            return node;
        }
    }
}
