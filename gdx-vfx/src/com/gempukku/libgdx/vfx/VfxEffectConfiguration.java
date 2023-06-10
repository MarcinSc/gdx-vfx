package com.gempukku.libgdx.vfx;

public interface VfxEffectConfiguration {
    <Config extends VfxConfiguration> Config getConfig(Class<Config> clazz);
}
