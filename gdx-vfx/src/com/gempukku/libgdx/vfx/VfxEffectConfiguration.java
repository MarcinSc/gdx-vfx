package com.gempukku.libgdx.vfx;

import com.gempukku.libgdx.graph.pipeline.time.TimeKeeper;
import com.gempukku.libgdx.graph.pipeline.time.TimeProvider;

public interface VfxEffectConfiguration {
    TimeProvider getRenderTimeProvider();
    TimeKeeper getEffectTimeKeeper();
    <Config extends VfxConfiguration> Config getConfig(Class<Config> clazz);
}
