package com.gempukku.libgdx.vfx.assistant;

import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.gdx.assistant.plugin.*;

public class GdxVfxProject implements AssistantPluginProject {
    private final ObjectMap<String, JsonValue> mainGraphs = new ObjectMap<>();

    private final AssistantApplication application;
    private final MenuManager menuManager;
    private final TabManager tabManager;
    private final AssistantProject assistantProject;

    private boolean dirty;

    public GdxVfxProject(AssistantApplication application, AssistantProject assistantProject) {
        this(application, assistantProject, null);
    }

    public GdxVfxProject(AssistantApplication application, AssistantProject assistantProject, JsonValue data) {
        this.application = application;
        this.assistantProject = assistantProject;
        this.menuManager = application.getMenuManager();
        this.tabManager = application.getTabManager();
    }

    @Override
    public boolean isProjectDirty() {
        return dirty;
    }

    @Override
    public void processUpdate(float deltaTime) {

    }

    @Override
    public JsonValue saveProject() {
        return new JsonValue(JsonValue.ValueType.object);
    }

    @Override
    public void markProjectClean() {
        dirty = false;
    }

    @Override
    public void closeProject() {

    }
}
