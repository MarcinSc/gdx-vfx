package com.gempukku.libgdx.vfx.particle.producer;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.*;
import com.gempukku.libgdx.vfx.VfxEffectConfiguration;
import com.gempukku.libgdx.vfx.node.*;
import com.gempukku.libgdx.vfx.particle.VfxParticle;
import com.gempukku.libgdx.vfx.particle.config.ParticleEmitterNodeConfiguration;
import com.gempukku.libgdx.vfx.particle.node.VfxOutputNode;
import com.gempukku.libgdx.vfx.process.DefaultVfxProcess;
import com.gempukku.libgdx.vfx.producer.AbstractVfxNodeProducer;

public class ParticleEmitterNodeProducer extends AbstractVfxNodeProducer {
    public ParticleEmitterNodeProducer() {
        super(new ParticleEmitterNodeConfiguration());
    }

    @Override
    public VfxRuntimeNode createNode(JsonValue data, ObjectMap<String, Array<String>> inputTypes, ObjectMap<String, String> outputTypes, VfxEffectConfiguration configuration) {
        return new ParticleEmitterNode(data, configuration);
    }

    private static class ParticleEmitterNode extends AbstractVfxNode implements VfxUpdatingNode {
        private final ObjectSet<VfxParticle> particles = new ObjectSet<>();
        private final JsonValue data;
        private final VfxEffectConfiguration configuration;
        private boolean emitting = false;
        private float lastEffectTime = 0f;
        private float lastParticleGenerated = 0f;

        private RelationshipConnection runConnection;
        private RelationshipConnection outputConnection;
        private RelationshipConnection initConnection;
        private RelationshipConnection updateConnection;
        private RelationshipConnection deathConnection;
        private RelationshipConnection initCountConnection;
        private RelationshipConnection perSecondCountConnection;
        private RelationshipConnection lifetimeConnection;

        public ParticleEmitterNode(JsonValue data, VfxEffectConfiguration configuration) {
            this.data = data;
            this.configuration = configuration;
        }

        @Override
        public void initializeRelationships(ObjectMap<String, Array<RelationshipConnection>> inputNodes, ObjectMap<String, Array<RelationshipConnection>> outputNodes) {
            super.initializeRelationships(inputNodes, outputNodes);

            runConnection = getFirstInputConnectionOrNull("run");
            outputConnection = getFirstOutputConnectionOrNull("particleOutput");
            initConnection = getFirstOutputConnectionOrNull("particleInit");
            updateConnection = getFirstOutputConnectionOrNull("particleUpdate");
            deathConnection = getFirstOutputConnectionOrNull("particleDeath");
            initCountConnection = getFirstInputConnectionOrNull("initCount");
            perSecondCountConnection = getFirstInputConnectionOrNull("perSecondCount");
            lifetimeConnection = getFirstInputConnectionOrNull("lifetime");
        }

        @Override
        public void update() {
            float renderTime = configuration.getRenderTimeProvider().getTime();
            float effectTime = configuration.getEffectTimeKeeper().getTime();
            boolean emitValue = (Boolean) ((VfxValueNode) runConnection.getNode()).getValue(runConnection.getFieldId());

            // Init burst
            if (emitValue && !emitting) {
                int initCount = MathUtils.floor(getFloatValue(initCountConnection, "initCount"));
                if (initCount > 0) {
                    for (int i = 0; i < initCount; i++) {
                        createParticle(renderTime);
                    }
                }
                lastParticleGenerated = renderTime;
            }
            // Create per second
            if (emitValue) {
                float particlePerSecond = getFloatValue(perSecondCountConnection, "perSecondCount");
                if (particlePerSecond > 0) {
                    float timeElapsed = effectTime - lastEffectTime;
                    float particleDelay = 1 / particlePerSecond;
                    int particleCount = MathUtils.floor(timeElapsed / particleDelay);
                    for (int i = 0; i < particleCount; i++) {
                        createParticle(lastParticleGenerated + particleDelay * (i + 1));
                    }
                    lastParticleGenerated += particleDelay * particleCount;
                }
            }
            // Update live particles
            if (updateConnection != null) {
                for (VfxParticle particle : particles) {
                    if (particle.getCreationTime() < renderTime && renderTime < particle.getDeathTime()) {
                        processParticle(updateConnection, particle);

                        ((VfxOutputNode<VfxParticle, Object>) outputConnection.getNode()).update(outputConnection.getFieldId(), particle.getRenderedIdentifier(), particle);
                    }
                }
            }
            // Process particle death
            ObjectSet.ObjectSetIterator<VfxParticle> particleIterator = particles.iterator();
            while (particleIterator.hasNext()) {
                VfxParticle particle = particleIterator.next();
                if (particle.getDeathTime() <= renderTime) {
                    processParticle(deathConnection, particle);

                    ((VfxOutputNode<VfxParticle, Object>) outputConnection.getNode()).remove(outputConnection.getFieldId(), particle.getRenderedIdentifier());

                    particleIterator.remove();

                    Pools.free(particle);
                }
            }

            emitting = emitValue;
            lastEffectTime = effectTime;
        }

        private void createParticle(float particleBirthTime) {
            VfxParticle particle = Pools.obtain(VfxParticle.class);
            particle.setCreationTime(particleBirthTime);
            float lifetime = getFloatValue(lifetimeConnection, "lifetime");
            particle.setDeathTime(particleBirthTime + lifetime);

            processParticle(initConnection, particle);

            Object identifier = ((VfxOutputNode<VfxParticle, Object>) outputConnection.getNode()).create(outputConnection.getFieldId(), particle);
            particle.setRenderedIdentifier(identifier);

            particles.add(particle);
        }

        private void processParticle(RelationshipConnection processConnection, VfxParticle particle) {
            if (processConnection != null) {
                DefaultVfxProcess particleProcess = Pools.obtain(DefaultVfxProcess.class);
                particleProcess.getPayload().put("Particle", particle);
                ((VfxProcessingNode) processConnection.getNode()).process(processConnection.getFieldId(), particleProcess);
                Pools.free(particleProcess);
            }
        }

        private RelationshipConnection getFirstInputConnectionOrNull(String fieldId) {
            Array<RelationshipConnection> relationshipConnections = inputNodes.get(fieldId);
            if (relationshipConnections.isEmpty())
                return null;
            else
                return relationshipConnections.get(0);
        }

        private RelationshipConnection getFirstOutputConnectionOrNull(String fieldId) {
            Array<RelationshipConnection> relationshipConnections = outputNodes.get(fieldId);
            if (relationshipConnections.isEmpty())
                return null;
            else
                return relationshipConnections.get(0);
        }

        private float getFloatValue(RelationshipConnection connection, String propertyName) {
            if (connection != null)
                return (Float) ((VfxValueNode) connection.getNode()).getValue(connection.getFieldId());
            return data.getFloat(propertyName, 0f);
        }
    }
}
