package com.gempukku.libgdx.vfx.process;

import com.badlogic.gdx.utils.ObjectMap;

public class VfxProcess {
    private ObjectMap<String, Object> payload;

    public VfxProcess(ObjectMap<String, Object> payload) {
        this.payload = payload;
    }

    public ObjectMap<String, Object> getPayload() {
        return payload;
    }
}
