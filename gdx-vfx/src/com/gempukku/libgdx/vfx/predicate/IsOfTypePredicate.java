package com.gempukku.libgdx.vfx.predicate;

import com.gempukku.libgdx.common.Predicate;
import com.gempukku.libgdx.vfx.VfxFieldTypeRegistry;

public class IsOfTypePredicate implements Predicate<String> {
    private final String type;

    public IsOfTypePredicate(String type) {
        this.type = type;
    }

    @Override
    public boolean test(String s) {
        return VfxFieldTypeRegistry.isOfType(VfxFieldTypeRegistry.findVFXFieldType(s), VfxFieldTypeRegistry.findVFXFieldType(type));
    }
}
