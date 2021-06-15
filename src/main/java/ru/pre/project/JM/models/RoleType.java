package ru.pre.project.JM.models;

public enum RoleType {
    ADMIN ("ROLE_ADMIN"),
    USER ("USER_USER");

    RoleType(String type) {
        this.type = type;
    }

    private String type;

    public String getType() {
        return type;
    }
}
