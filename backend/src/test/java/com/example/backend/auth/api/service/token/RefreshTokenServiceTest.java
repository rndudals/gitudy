package com.example.backend.auth.api.service.token;

import com.example.backend.domain.define.refreshToken.RefreshToken;
import com.example.backend.domain.define.refreshToken.repository.RefreshTokenRepository;
import com.example.backend.domain.define.user.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RefreshTokenServiceTest {
    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;
    @AfterEach
    void tearDown() {
        userRepository.deleteAllInBatch();
    }
    @Test
    @DisplayName("Refresh Token 저장 & 조회 테스트")
    void redisRefreshTokenGenerate() {
        // given
        RefreshToken saveToken = RefreshToken.builder()
                .refreshToken("TestToken")
                .email("TestEmail@test.com")
                .build();

        refreshTokenService.saveRefreshToken(saveToken);

        // when
        Optional<RefreshToken> testToken = refreshTokenRepository.findById("TestToken");

        // then
        assertThat(testToken.get().getRefreshToken()).isEqualTo("TestToken");
        assertThat(testToken.get().getEmail()).isEqualTo("TestEmail@test.com");
    }
}