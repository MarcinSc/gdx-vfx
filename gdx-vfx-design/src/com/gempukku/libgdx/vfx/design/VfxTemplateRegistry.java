package com.gempukku.libgdx.vfx.design;

import com.badlogic.gdx.utils.Array;
import com.gempukku.libgdx.graph.ui.graph.GraphTemplate;

public class VfxTemplateRegistry {
    private static Array<GraphTemplate> templates = new Array();

    public VfxTemplateRegistry() {
    }

    public static void register(GraphTemplate template) {
        templates.add(template);
    }

    public static Iterable<GraphTemplate> getTemplates() {
        return templates;
    }
}
