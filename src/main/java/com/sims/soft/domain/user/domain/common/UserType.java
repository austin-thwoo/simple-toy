package com.sims.soft.domain.user.domain.common;

public enum UserType {
    CEED("관리자"),
    SPROUT("제조사"),
    TREE("중개인"),
    FOREST("숲");

    private final String value;

    UserType(String value) {
        this.value = value;
    }
}
