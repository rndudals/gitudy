package com.example.backend.domain.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Value("${firebase.key-path}")
    private String fcmKeyPath;


    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) {
            // FirebaseApp 초기화 로직
            InputStream credentials = new ClassPathResource(fcmKeyPath).getInputStream();

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(credentials))
                    .build();
            FirebaseApp.initializeApp(options);

        }

        log.info("FCM Setting Completed");
        // FirebaseApp이 정상적으로 초기화된 후, FirebaseMessaging 인스턴스 반환
        return FirebaseMessaging.getInstance();
    }

}