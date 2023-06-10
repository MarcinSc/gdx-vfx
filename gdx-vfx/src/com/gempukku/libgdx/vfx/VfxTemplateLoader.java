package com.gempukku.libgdx.vfx;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.JsonReader;
import com.gempukku.libgdx.graph.GraphTypeRegistry;
import com.gempukku.libgdx.graph.data.GraphWithProperties;
import com.gempukku.libgdx.graph.loader.GraphLoader;

public class VfxTemplateLoader {
    public static VfxTemplate loadVfxTemplate(FileHandle fileHandle) {
        VfxGraphType graphType = GraphTypeRegistry.findGraphType(VfxGraphType.TYPE, VfxGraphType.class);
        GraphWithProperties graph = GraphLoader.loadGraph(graphType.getType(), new JsonReader().parse(fileHandle));

        if (graphType.validateGraph(graph).hasErrors())
            throw new GdxRuntimeException("Unable to load graph - not valid, open it in graph designer and fix it");

        return graphType.buildVfxTemplate(graph, false);
    }
}
