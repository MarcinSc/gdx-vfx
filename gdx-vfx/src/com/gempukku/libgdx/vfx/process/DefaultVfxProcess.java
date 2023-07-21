package com.gempukku.libgdx.vfx.process;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;

public class DefaultVfxProcess implements VfxProcess, Pool.Poolable {
    private ObjectMap<String, Object> payload = new ObjectMap<>();

    public ObjectMap<String, Object> getPayload() {
        return payload;
    }

    @Override
    public void reset() {
        payload.clear();
    }
}
