package com.gempukku.libgdx.vfx.design.common.config;

import com.gempukku.libgdx.graph.ui.graph.GdxGraphNodeEditorProducer;
import com.gempukku.libgdx.vfx.common.config.VfxPreviewNodeConfiguration;

public class VfxPreviewNodeEditorProducer extends GdxGraphNodeEditorProducer {
    public VfxPreviewNodeEditorProducer() {
        super(new VfxPreviewNodeConfiguration());
        setCloseable(false);
    }
}
