package com.gempukku.libgdx.vfx;

public class BasicVfxFieldType implements VfxFieldType{
    private final String name;

    public BasicVfxFieldType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
