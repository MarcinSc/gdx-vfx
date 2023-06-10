package com.gempukku.libgdx.vfx.assistant;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Collections;
import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.gdx.assistant.plugin.*;
import com.gempukku.gdx.plugins.PluginEnvironment;
import com.gempukku.gdx.plugins.PluginVersion;
import com.gempukku.libgdx.graph.ui.UIGdxGraphPluginRegistry;
import com.gempukku.libgdx.vfx.design.UIVfxGraphPlugin;
import com.kotcrab.vis.ui.VisUI;

public class GdxVfxAssistantPlugin implements AssistantPlugin {
    private AssistantApplication assistantApplication;
    private TextureAtlas gdxVfxTextureAtlas;

    @Override
    public String getId() {
        return "gdx-vfx";
    }

    @Override
    public PluginVersion getVersion() {
        return new PluginVersion(0, 0, 1);
    }

    @Override
    public boolean shouldBeRegistered(PluginEnvironment pluginEnvironment) {
        return pluginEnvironment.hasPlugin("gdx-graph");
    }

    @Override
    public void registerPlugin() {
        Collections.allocateIterators = true;
        registerVfxPlugins();
    }

    private static void registerVfxPlugins() {
        UIGdxGraphPluginRegistry.register(UIVfxGraphPlugin.class);
    }

    @Override
    public void initializePlugin(AssistantApplication assistantApplication) {
        this.assistantApplication = assistantApplication;

        Skin skin = VisUI.getSkin();

        gdxVfxTextureAtlas = new TextureAtlas(assistantApplication.getAssetResolver().resolve("skin/gdx-vfx/uiskin.atlas"));
        skin.addRegions(gdxVfxTextureAtlas);
        skin.load(assistantApplication.getAssetResolver().resolve("skin/gdx-vfx/uiskin.json"));

        // initialize plugins

        MenuManager menuManager = assistantApplication.getMenuManager();
        menuManager.addMainMenu("VFX");
        // Setup menu bar
    }

    @Override
    public void deregisterPlugin() {
        gdxVfxTextureAtlas.dispose();
    }

    @Override
    public AssistantPluginProject newProjectCreated(AssistantProject assistantProject) {
        return new GdxVfxProject(assistantApplication, assistantProject);
    }

    @Override
    public AssistantPluginProject projectOpened(AssistantProject assistantProject, JsonValue projectData) {
        return new GdxVfxProject(assistantApplication, assistantProject, projectData);
    }
}
