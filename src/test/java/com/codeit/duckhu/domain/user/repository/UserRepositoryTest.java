package com.codeit.duckhu.domain.user.repository;

import com.codeit.duckhu.domain.user.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@Sql("/data.sql")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("저장 성공")
    void save_success() {
        //given
        User user=new User("testA@example.com", "testA", "testa1234!", false);

        //when
        User result = userRepository.save(user);

        //then
        assertThat(result.getEmail()).isEqualTo("testA@example.com");
        assertThat(result.getNickname()).isEqualTo("testA");
        assertThat(result.getPassword()).isEqualTo("testa1234!");
    }

    @Test
    @DisplayName("저장 실패")
    void save_fail() {
        //given
        User user=new User(null, null, "testa1234!", false);
        //when
        //then
        assertThatThrownBy(() -> {
            userRepository.save(user);
            userRepository.flush();
        }).isInstanceOf(DataIntegrityViolationException.class);
    }

    @Test
    @DisplayName("이메일 존재 여부 성공")
    void existsByEmail_success() {
        //given
        //when
        //then
        assertThat(userRepository.existsByEmail("test@example.com")).isTrue();
    }

    @Test
    @DisplayName("이메일 존재 여부 실패")
    void existsByEmail_fail() {
        //given
        //when
        //then
        assertThat(userRepository.existsByEmail("testB@example.com")).isFalse();
    }

    @Test
    @DisplayName("email로 사용자 조회 성공")
    void findByNickname_success() {
        //given
        //when
        Optional<User> findUser = userRepository.findByEmail("test@example.com");

        //then
        assertThat(findUser).isPresent();
        assertThat(findUser.get().getNickname()).isEqualTo("테스트유저");
    }

    @Test
    @DisplayName("email로 사용자 조회 실패")
    void findByNickname_fail() {
        //given
        String nonExistEmail="te@example.com";

        //when
        Optional<User> findUser = userRepository.findByEmail(nonExistEmail);

        //then
        assertThat(findUser).isEmpty();
    }



}
