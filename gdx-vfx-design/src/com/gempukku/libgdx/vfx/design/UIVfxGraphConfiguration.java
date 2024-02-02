package com.gempukku.libgdx.vfx.design;

import com.badlogic.gdx.utils.ObjectMap;
import com.gempukku.libgdx.common.Producer;
import com.gempukku.libgdx.graph.ui.graph.MenuGraphNodeEditorProducer;
import com.gempukku.libgdx.graph.ui.graph.UIGraphConfiguration;
import com.gempukku.libgdx.graph.ui.graph.property.PropertyEditorDefinition;
import com.gempukku.libgdx.vfx.VfxConfiguration;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class UIVfxGraphConfiguration implements UIGraphConfiguration {
    private static Map<String, MenuGraphNodeEditorProducer> graphBoxProducers = new TreeMap<>();
    private static Map<String, PropertyEditorDefinition> propertyProducers = new LinkedHashMap<>();
    private static ObjectMap<Class<? extends VfxConfiguration>,
            Producer<? extends VfxConfiguration>> previewGdxConfigurationBuilders = new ObjectMap<>();

    public static void register(MenuGraphNodeEditorProducer producer) {
        String menuLocation = producer.getMenuLocation();
        if (menuLocation == null)
            menuLocation = "Dummy";
        graphBoxProducers.put(menuLocation + "/" + producer.getName(), producer);
    }

    public static void registerPropertyType(PropertyEditorDefinition propertyEditorDefinition) {
        String propertyType = propertyEditorDefinition.getType();
        propertyProducers.put(propertyType, propertyEditorDefinition);
    }

    @Override
    public Iterable<? extends MenuGraphNodeEditorProducer> getGraphNodeEditorProducers() {
        return graphBoxProducers.values();
    }

    @Override
    public Map<String, ? extends PropertyEditorDefinition> getPropertyEditorDefinitions() {
        return propertyProducers;
    }

    public static <T extends VfxConfiguration> void registerPreviewGdxConfigurationBuilder(Class<T> clazz, Producer<T> configurationProducer) {
        previewGdxConfigurationBuilders.put(clazz, configurationProducer);
    }

    public static ObjectMap<Class<? extends VfxConfiguration>, Producer<? extends VfxConfiguration>> getPreviewGdxConfigurationBuilders() {
        return previewGdxConfigurationBuilders;
    }
}
