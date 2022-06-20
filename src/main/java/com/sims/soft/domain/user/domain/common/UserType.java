package com.sims.soft.domain.user.domain.common;

public enum UserType {
    SEED("씨앗"),
    seedling("묘목"),
    TREE("나무"),
    FOREST("숲");

    private final String value;

    UserType(String value) {
        this.value = value;
    }
}
