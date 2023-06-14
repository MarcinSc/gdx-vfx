package com.gempukku.libgdx.vfx.particle;

import com.gempukku.libgdx.graph.pipeline.util.ValuePerVertex;
import com.gempukku.libgdx.graph.util.sprite.model.SpriteModel;

public interface SpriteParticleModel extends SpriteModel {
    ValuePerVertex getUVs();
    ValuePerVertex getPositions();
}
