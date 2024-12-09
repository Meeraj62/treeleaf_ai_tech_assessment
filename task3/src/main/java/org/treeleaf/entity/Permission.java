package org.treeleaf.entity;

public enum Permission {
    READ_BLOG,
    WRITE_BLOG,
    DELETE_BLOG,
    MANAGE_USERS;

    @Override
    public String toString() {
        return this.name();
    }
}
