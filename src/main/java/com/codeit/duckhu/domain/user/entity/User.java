package com.codeit.duckhu.domain.user.entity;

import com.codeit.duckhu.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_deleted", nullable = false)
    private boolean deleted;

    public User(String email, String nickname, String password, boolean isDeleted) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.deleted = isDeleted;
    }

}
