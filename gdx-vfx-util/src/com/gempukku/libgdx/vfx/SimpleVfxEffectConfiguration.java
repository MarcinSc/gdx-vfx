package com.gempukku.libgdx.vfx;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.graph.pipeline.time.TimeKeeper;
import com.gempukku.libgdx.graph.pipeline.time.TimeProvider;

public class SimpleVfxEffectConfiguration implements VfxEffectConfiguration, Disposable {
    private final TimeProvider renderTimeProvider;
    private final TimeKeeper effectTimeKeeper;

    private final ObjectMap<Class<?>, VfxConfiguration> configs = new ObjectMap<>();

    public SimpleVfxEffectConfiguration(TimeProvider renderTimeProvider, TimeKeeper effectTimeKeeper) {
        this.renderTimeProvider = renderTimeProvider;
        this.effectTimeKeeper = effectTimeKeeper;
    }

    public <Config extends VfxConfiguration> void setConfig(Class<Config> clazz, Config config) {
        configs.put(clazz, config);
    }

    @Override
    public TimeProvider getRenderTimeProvider() {
        return renderTimeProvider;
    }

    @Override
    public TimeKeeper getEffectTimeKeeper() {
        return effectTimeKeeper;
    }

    @Override
    public <Config extends VfxConfiguration> Config getConfig(Class<Config> clazz) {
        return (Config) configs.get(clazz);
    }

    public <Config extends VfxConfiguration> Config removeConfig(Class<Config> clazz) {
        return (Config) configs.remove(clazz);
    }

    @Override
    public void dispose() {
        for (VfxConfiguration config : configs.values()) {
            if (config instanceof Disposable) {
                ((Disposable) config).dispose();
            }
        }
        configs.clear();
    }
}
