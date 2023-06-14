package com.gempukku.libgdx.vfx;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.graph.pipeline.field.PipelineFieldType;
import com.gempukku.libgdx.graph.pipeline.property.PipelinePropertyProducer;

public class VfxGraphConfiguration {
    private static final ObjectMap<String, VfxRuntimeNodeProducer> vfxNodeProducers = new ObjectMap<>();
    private static final Array<PipelinePropertyProducer> pipelinePropertyProducers = new Array<>();


    public static void register(VfxRuntimeNodeProducer vfxRuntimeNodeProducer) {
        vfxNodeProducers.put(vfxRuntimeNodeProducer.getType(), vfxRuntimeNodeProducer);
    }

    public static void registerPropertyProducer(PipelinePropertyProducer pipelinePropertyProducer, PipelineFieldType pipelineFieldType) {
        pipelinePropertyProducers.add(pipelinePropertyProducer);
    }

    public static VfxRuntimeNodeProducer findProducer(String type) {
        return vfxNodeProducers.get(type);
    }
}
