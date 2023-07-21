package com.gempukku.libgdx.vfx.particle;

import com.gempukku.libgdx.graph.data.PropertyContainer;
import com.gempukku.libgdx.graph.util.sprite.SpriteReference;
import com.gempukku.libgdx.graph.util.storage.MultiPartMesh;
import com.gempukku.libgdx.vfx.VfxConfiguration;

public interface SpriteParticlesConfiguration extends VfxConfiguration {
    SpriteParticleModel getSpriteModel(String spriteModel, String previewSpriteModel);

    MultiPartMesh<VfxParticle, SpriteReference> createSpriteMesh(
            String graphShaderTag, SpriteParticleModel spriteUVModel, PropertyContainer previewUniforms);

    void returnSpriteModel(MultiPartMesh<VfxParticle, SpriteReference> spriteMesh);
}
