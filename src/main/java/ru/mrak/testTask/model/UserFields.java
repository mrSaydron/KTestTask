package ru.mrak.testTask.model;

public enum UserFields {
    ID_UP,
    ID_DOWN,
    NAME_UP,
    NAME_DOWN,
    AGE_UP,
    AGE_DOWN,
    ISADMIN_UP,
    ISADMIN_DOWN,
    CREATEDDATE_UP,
    CREATEDDATE_DOWN;

    public String getField() {
        return this.name().substring(0, this.name().lastIndexOf('_'));
    }

    public String getDirection() {
        return this.name().substring(this.name().lastIndexOf('_') + 1);
    }
}
