package com.gempukku.libgdx.vfx.particle.producer;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.graph.data.MapWritablePropertyContainer;
import com.gempukku.libgdx.graph.util.sprite.SpriteReference;
import com.gempukku.libgdx.graph.util.storage.MultiPartMesh;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.node.VfxOutputNode;
import com.gempukku.libgdx.vfx.node.VfxRuntimeNode;
import com.gempukku.libgdx.vfx.particle.SpriteParticleModel;
import com.gempukku.libgdx.vfx.particle.SpriteParticlesConfiguration;
import com.gempukku.libgdx.vfx.particle.VfxParticle;
import com.gempukku.libgdx.vfx.particle.config.SpriteParticleRendererNodeConfiguration;
import com.gempukku.libgdx.vfx.producer.AbstractVfxNodeProducer;

public class SpriteParticleRendererNodeProducer extends AbstractVfxNodeProducer {
    public SpriteParticleRendererNodeProducer() {
        super(new SpriteParticleRendererNodeConfiguration());
    }

    @Override
    public VfxRuntimeNode createNode(JsonValue data, ObjectMap<String, Array<String>> inputTypes, ObjectMap<String, String> outputTypes, VfxEffectConfiguration configuration) {
        return new SpriteParticleRendererNode(data, configuration);
    }

    private static class SpriteParticleRendererNode implements VfxOutputNode<VfxParticle, SpriteReference>, Disposable {
        private final VfxEffectConfiguration configuration;
        private final MultiPartMesh<VfxParticle, SpriteReference> spriteMesh;

        public SpriteParticleRendererNode(JsonValue data, VfxEffectConfiguration configuration) {
            this.configuration = configuration;
            String tag = data.getString("graphShaderTag");
            String spriteUVModel = data.getString("spriteUVModel");
            String previewSpriteUVModel = data.getString("previewSpriteUVModel");

            SpriteParticlesConfiguration spriteParticlesConfig = configuration.getConfig(SpriteParticlesConfiguration.class);
            SpriteParticleModel spriteModel = spriteParticlesConfig.getSpriteModel(spriteUVModel, previewSpriteUVModel);
            spriteMesh = spriteParticlesConfig.createSpriteMesh(tag, spriteModel, new MapWritablePropertyContainer());
        }

        @Override
        public void initializeRelationships(ObjectMap<String, Array<RelationshipConnection>> inputNodes, ObjectMap<String, Array<RelationshipConnection>> outputNodes) {

        }

        @Override
        public SpriteReference create(String fieldId, VfxParticle output) {
            return spriteMesh.addPart(output);
        }

        @Override
        public void update(String fieldId, SpriteReference spriteReference, VfxParticle output) {
            spriteMesh.updatePart(output, spriteReference);
        }

        @Override
        public void remove(String fieldId, SpriteReference spriteReference) {
            spriteMesh.removePart(spriteReference);
        }

        @Override
        public void dispose() {
            SpriteParticlesConfiguration spriteParticlesConfig = configuration.getConfig(SpriteParticlesConfiguration.class);
            spriteParticlesConfig.returnSpriteModel(spriteMesh);
        }
    }
}
