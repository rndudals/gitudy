package com.example.backend.auth;

/*
* static 모음 *
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

* Mocking *
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 */


import com.example.backend.auth.api.service.oauth.response.OAuthResponse;
import com.example.backend.auth.config.fixture.UserFixture;
import com.example.backend.domain.define.account.user.User;
import com.example.backend.domain.define.account.user.constant.UserPlatformType;
import com.example.backend.domain.define.account.user.constant.UserRole;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static com.example.backend.auth.config.fixture.UserFixture.expectedUserPlatformId;
import static com.example.backend.domain.define.account.user.constant.UserPlatformType.GITHUB;
import static com.example.backend.domain.define.account.user.constant.UserRole.USER;
import static java.util.Map.entry;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class TestConfig {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer";

    public static String createAuthorizationHeader(String accessToken, String refreshToken) {
        return BEARER + " " + accessToken + " " + refreshToken;
    }

}
