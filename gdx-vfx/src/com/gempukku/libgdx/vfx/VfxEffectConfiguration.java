package com.gempukku.libgdx.vfx;

import com.gempukku.libgdx.graph.pipeline.time.TimeProvider;

public interface VfxEffectConfiguration {
    TimeProvider getRenderTimeProvider();
    TimeProvider getEffectTimeProvider();
    <Config> Config getConfig(Class<Config> clazz);
}
