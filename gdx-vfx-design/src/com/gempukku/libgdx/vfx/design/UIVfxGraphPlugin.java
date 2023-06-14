package com.gempukku.libgdx.vfx.design;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.gempukku.libgdx.graph.GraphTypeRegistry;
import com.gempukku.libgdx.graph.plugin.RuntimePluginRegistry;
import com.gempukku.libgdx.graph.ui.UIGdxGraphPlugin;
import com.gempukku.libgdx.graph.ui.graph.FileGraphTemplate;
import com.gempukku.libgdx.vfx.VfxPluginRuntimeInitializer;
import com.gempukku.libgdx.vfx.design.common.config.LatchNodeEditorProducer;
import com.gempukku.libgdx.vfx.design.common.config.VfxPreviewNodeEditorProducer;
import com.gempukku.libgdx.vfx.design.common.config.trigger.StartTriggerNodeEditorProducer;
import com.gempukku.libgdx.vfx.design.common.config.trigger.TimeTriggerNodeEditorProducer;
import com.kotcrab.vis.ui.VisUI;

public class UIVfxGraphPlugin implements UIGdxGraphPlugin {
    @Override
    public void initialize(FileHandleResolver assetResolver) {
        // Register graph type
        UIVfxGraphType graphType = new UIVfxGraphType(VisUI.getSkin().getDrawable("graph-model-shader-icon"));
        GraphTypeRegistry.registerType(graphType);

        // Register VFX graph nodes
        UIVfxGraphConfiguration.register(new VfxPreviewNodeEditorProducer());
        UIVfxGraphConfiguration.register(new StartTriggerNodeEditorProducer());
        UIVfxGraphConfiguration.register(new TimeTriggerNodeEditorProducer());
        UIVfxGraphConfiguration.register(new LatchNodeEditorProducer());

        // Register VFX graph properties

        // Register runtime plugin
        RuntimePluginRegistry.register(VfxPluginRuntimeInitializer.class);

        // Register VFX graph templates
        VfxTemplateRegistry.register(new FileGraphTemplate(graphType, "Empty visual effect", assetResolver.resolve("template/vfx/empty-visual-effect.vfg")));
    }
}
