package com.example.backend.domain.define.fcm.listener;

import com.example.backend.TestConfig;
import com.example.backend.domain.define.event.FcmFixture;
import com.example.backend.domain.define.fcm.FcmToken;
import com.example.backend.domain.define.study.todo.TodoEventFixture;
import com.example.backend.domain.define.study.todo.event.TodoUpdateMemberEvent;
import com.example.backend.study.api.event.FcmMultiTokenRequest;
import com.example.backend.study.api.event.service.FcmService;
import com.example.backend.study.api.event.service.NoticeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class TodoUpdateMemberListenerTest extends TestConfig {
    @InjectMocks
    private TodoUpdateListener todoUpdateMemberListener;

    @Mock
    private FcmService fcmService;

    @Mock
    private NoticeService noticeService;

    @Test
    @DisplayName("Todo 수정 시 알림 리스너 테스트")
    void Todo_update_notify_listener_test() throws Exception {
        // given
        List<Long> userIds = Arrays.asList(1L, 2L, 3L);

        TodoUpdateMemberEvent todoUpdateMemberEvent = TodoEventFixture.generateTodoUpdateEvent(userIds);

        List<FcmToken> fcmTokens = FcmFixture.generateFcmTokens(userIds);

        when(fcmService.findFcmTokensByIdsOrThrowException(userIds)).thenReturn(fcmTokens);

        doNothing().when(noticeService).TodoUpdateMemberNotice(any(TodoUpdateMemberEvent.class));

        // when
        todoUpdateMemberListener.todoUpdateListener(todoUpdateMemberEvent);

        // then
        verify(fcmService).sendMessageMultiDevice(any(FcmMultiTokenRequest.class)); // sendMessageMultiDevice 호출 검증
    }
}