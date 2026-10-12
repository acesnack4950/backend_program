package me.scpark.springdeveloper;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreateRequest {

    private String name;
    private String email;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }
}