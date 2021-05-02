package com.marcelo.testes.cadastro.domain.enums;

public enum ProfileGender {
    F("FEMALE"),
    M("MALE");

    private final String gender;

    ProfileGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
