package com.gempukku.libgdx.vfx.design;

import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.gempukku.libgdx.graph.ui.graph.GraphTemplate;
import com.gempukku.libgdx.graph.ui.graph.UIGraphConfiguration;
import com.gempukku.libgdx.graph.ui.graph.UIGraphType;
import com.gempukku.libgdx.vfx.VfxGraphType;

public class UIVfxGraphType extends VfxGraphType implements UIGraphType {
    private UIGraphConfiguration[] configurations;
    private Drawable icon;

    public UIVfxGraphType(Drawable icon) {
        this.icon = icon;
        this.configurations = new UIGraphConfiguration[] {
                new UIVfxGraphConfiguration()
        };
    }

    @Override
    public String getFileExtension() {
        return "fxg";
    }

    @Override
    public String getPresentableName() {
        return "Visual Effect";
    }

    @Override
    public Drawable getIcon() {
        return icon;
    }

    @Override
    public Iterable<? extends GraphTemplate> getGraphTemplates() {
        return VfxTemplateRegistry.getTemplates();
    }

    @Override
    public UIGraphConfiguration[] getUIConfigurations() {
        return configurations;
    }
}
