package org.treeleaf.entity;

public enum Permission {
    READ_BLOG,
    WRITE_BLOG,
    DELETE_BLOG,
    WRITE_COMMENT;

    @Override
    public String toString() {
        return this.name();
    }
}
