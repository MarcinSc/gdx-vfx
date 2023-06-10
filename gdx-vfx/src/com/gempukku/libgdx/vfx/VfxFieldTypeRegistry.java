package com.gempukku.libgdx.vfx;

import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.ObjectSet;

public class VfxFieldTypeRegistry {
    private static final ObjectMap<String, VfxFieldType> fieldTypes = new ObjectMap();
    private static final ObjectMap<String, ObjectSet<String>> extendsMap = new ObjectMap<>();

    public static boolean isOfType(VfxFieldType fieldType, VfxFieldType checkAgainst) {
        if (checkAgainst == fieldType)
            return true;
        for (String subClass : new ObjectSet.ObjectSetIterator<>(extendsMap.get(checkAgainst.getName()))) {
            if (isOfType(fieldType, findVFXFieldType(subClass)))
                return true;
        }
        return false;
    }

    public static void registerVFXFieldType(VfxFieldType fieldType) {
        fieldTypes.put(fieldType.getName(), fieldType);
        extendsMap.put(fieldType.getName(), new ObjectSet<String>());
    }

    public static void registerExtends(VfxFieldType child, VfxFieldType parent) {
        extendsMap.get(parent.getName()).add(child.getName());
    }

    public static VfxFieldType findVFXFieldType(String name) {
        return (VfxFieldType) fieldTypes.get(name);
    }
}
