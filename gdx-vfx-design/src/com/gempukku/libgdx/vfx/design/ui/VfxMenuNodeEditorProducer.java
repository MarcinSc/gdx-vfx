package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.graph.config.MenuNodeConfiguration;
import com.gempukku.libgdx.graph.ui.graph.MenuGraphNodeEditorProducer;
import com.gempukku.libgdx.ui.graph.data.NodeConfiguration;

public abstract class VfxMenuNodeEditorProducer implements MenuGraphNodeEditorProducer {
    protected final MenuNodeConfiguration nodeConfiguration;
    private boolean closeable = true;

    public VfxMenuNodeEditorProducer(MenuNodeConfiguration nodeConfiguration) {
        this.nodeConfiguration = nodeConfiguration;
    }

    public void setCloseable(boolean closeable) {
        this.closeable = closeable;
    }

    @Override
    public String getMenuLocation() {
        return nodeConfiguration.getMenuLocation();
    }

    @Override
    public String getType() {
        return nodeConfiguration.getType();
    }

    @Override
    public NodeConfiguration getConfiguration(JsonValue data) {
        return nodeConfiguration;
    }

    @Override
    public String getName() {
        return nodeConfiguration.getName();
    }

    @Override
    public boolean isCloseable() {
        return closeable;
    }
}
