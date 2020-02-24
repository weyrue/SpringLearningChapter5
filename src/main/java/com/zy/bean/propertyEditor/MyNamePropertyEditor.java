package com.zy.bean.propertyEditor;

import java.beans.PropertyEditorSupport;

public class MyNamePropertyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] names = text.split("\\s");
        setValue(new FullName(names[1], names[0]));
    }
}
