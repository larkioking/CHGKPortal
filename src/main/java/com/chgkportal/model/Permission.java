package com.chgkportal.model;

public enum Permission {
    ADMIN_PANEL_CRUD("adminpanel:crud"),
    DEFAULT_READ("default:read"),
    DEFAULT_WRITE("default:write");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
