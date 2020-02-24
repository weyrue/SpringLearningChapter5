package com.zy.bean.propertyEditor;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DefaultPropertyEditor {

    private Character character;
    private Class cls;
    private Date date;
    private Long l;

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setCls(Class cls) {
        this.cls = cls;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setL(Long l) {
        this.l = l;
    }

    @Override
    public String toString() {
        return "DefaultPropertyEditor{" +
                "character=" + character +
                ", cls=" + cls +
                ", date=" + date +
                ", l=" + l +
                '}';
    }
}
