package com.gempukku.libgdx.vfx.predicate;

import com.gempukku.libgdx.common.Predicate;
import com.gempukku.libgdx.vfx.VfxFieldType;
import com.gempukku.libgdx.vfx.VfxFieldTypeRegistry;

public class IsOfTypePredicate implements Predicate<String> {
    private final VfxFieldType type;

    public IsOfTypePredicate(String type) {
        this.type = VfxFieldTypeRegistry.findVFXFieldType(type);
    }

    @Override
    public boolean test(String s) {
        return VfxFieldTypeRegistry.isOfType(VfxFieldTypeRegistry.findVFXFieldType(s), type);
    }
}
