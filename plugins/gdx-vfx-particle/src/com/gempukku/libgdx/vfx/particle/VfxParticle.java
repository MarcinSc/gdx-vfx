package com.gempukku.libgdx.vfx.particle;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;
import com.gempukku.libgdx.graph.data.MapWritablePropertyContainer;

public class VfxParticle implements Pool.Poolable {
    private MapWritablePropertyContainer propertyContainer = new MapWritablePropertyContainer();
    private Vector3 position = new Vector3();
    private float creationTime;
    private float deathTime;

    public MapWritablePropertyContainer getPropertyContainer() {
        return propertyContainer;
    }

    public Vector3 getPosition() {
        return position;
    }

    public float getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(float creationTime) {
        this.creationTime = creationTime;
    }

    public float getDeathTime() {
        return deathTime;
    }

    public void setDeathTime(float deathTime) {
        this.deathTime = deathTime;
    }

    @Override
    public void reset() {
        propertyContainer.clear();
        position.setZero();
        creationTime = 0f;
        deathTime = 0f;
    }
}
