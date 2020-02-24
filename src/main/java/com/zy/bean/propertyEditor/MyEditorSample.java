package com.zy.bean.propertyEditor;

public class MyEditorSample {
    private FullName fullName;

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "MyEditorSample{" +
                "fullName=" + fullName +
                '}';
    }
}
