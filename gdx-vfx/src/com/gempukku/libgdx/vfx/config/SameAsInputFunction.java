package com.gempukku.libgdx.vfx.config;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.common.Function;

public class SameAsInputFunction implements Function<ObjectMap<String, Array<String>>, String> {
    private final String inputFieldId;

    public SameAsInputFunction(String inputFieldId) {
        this.inputFieldId = inputFieldId;
    }

    @Override
    public String evaluate(ObjectMap<String, Array<String>> entries) {
        return entries.get(inputFieldId).get(0);
    }
}
