package com.mysite.sbb.user;

import lombok.Getter;

//상수들의 집합으로 꺼내서 쓸 수 있는 저장고 

@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}