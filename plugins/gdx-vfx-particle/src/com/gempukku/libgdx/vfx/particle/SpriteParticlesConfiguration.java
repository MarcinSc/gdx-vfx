package com.gempukku.libgdx.vfx.particle;

import com.gempukku.libgdx.graph.data.PropertyContainer;
import com.gempukku.libgdx.graph.util.sprite.SpriteReference;
import com.gempukku.libgdx.graph.util.storage.MultiPartMesh;

public interface SpriteParticlesConfiguration {
    SpriteParticleModel getSpriteModel(String spriteModel, String previewSpriteModel);

    MultiPartMesh<VfxParticle, SpriteReference> createSpriteMesh(
            String graphShaderTag, SpriteParticleModel spriteUVModel, PropertyContainer previewUniforms);

    void returnSpriteModel(MultiPartMesh<VfxParticle, SpriteReference> spriteMesh);
}
