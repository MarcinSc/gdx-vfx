package com.gempukku.libgdx.vfx.design.ui;

import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.JsonValue;
import com.gempukku.libgdx.common.SimpleNumberFormatter;
import com.gempukku.libgdx.ui.undo.UndoableValidatableTextField;
import com.kotcrab.vis.ui.util.InputValidator;
import com.kotcrab.vis.ui.util.Validators;

public class InputFloatNodeEditorPart extends InputNodeEditorPart {
    private final String propertyName;
    private final UndoableValidatableTextField floatTextField;

    public InputFloatNodeEditorPart(String fieldId, String label,
                                    String propertyName, float defaultValue, InputValidator... inputValidator) {
        super(fieldId, label, false);
        this.propertyName = propertyName;
        floatTextField = new UndoableValidatableTextField(SimpleNumberFormatter.format(defaultValue));
        floatTextField.setAlignment(Align.right);
        floatTextField.addValidator(new Validators.FloatValidator());
        for (InputValidator validator : inputValidator) {
            floatTextField.addValidator(validator);
        }
        add(floatTextField).width(60);
    }

    @Override
    public void initialize(JsonValue data) {
        if (data != null) {
            float value = data.getFloat(propertyName, 0f);
            floatTextField.setText(SimpleNumberFormatter.format(value));
        }
    }

    @Override
    public void serializePart(JsonValue value) {
        value.addChild(propertyName, new JsonValue(Float.parseFloat(floatTextField.getText())));
    }
}
