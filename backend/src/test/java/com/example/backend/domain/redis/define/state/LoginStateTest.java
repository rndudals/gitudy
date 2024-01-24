package com.example.backend.domain.redis.define.state;

import com.example.backend.domain.define.state.LoginState;
import com.example.backend.domain.define.state.LoginStateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class LoginStateTest {
    @Autowired
    private LoginStateRepository loginStateRepository;

    @AfterEach  // 테스트 후 데이터 삭제
    void tearDown() {
        loginStateRepository.deleteAll();
    }

    @Test
    @DisplayName("LoginState 저장")
    void redisLoginStateSave() {
        // given
        LoginState savedEntity = loginStateRepository.save(LoginState.builder()
                .build());

        // when
        Optional<LoginState> byId = loginStateRepository.findById(UUID.fromString(savedEntity.getState()));

        // then
        UUID stateAsUUID = UUID.fromString(byId.get().getState());
        assertThat(stateAsUUID).isInstanceOf(UUID.class);
    }

    @Test
    @DisplayName("LoginState 삭제")
    void redisLoginStateDelete() {
        // given
        LoginState savedEntity = loginStateRepository.save(LoginState.builder()
                .build());

        loginStateRepository.deleteById(UUID.fromString(savedEntity.getState()));

        // when
        Optional<LoginState> findLoginState = loginStateRepository.findById(UUID.fromString(savedEntity.getState()));

        // then
        assertThat(findLoginState.isEmpty()).isTrue();
    }
}
