package com.example.backend.study.api.controller.commit;

import com.example.backend.auth.api.service.auth.AuthService;
import com.example.backend.common.response.JsonResult;
import com.example.backend.domain.define.account.user.User;
import com.example.backend.study.api.controller.commit.request.CommitInfoPageRequest;
import com.example.backend.study.api.controller.commit.request.UserCommitInfoRequest;
import com.example.backend.study.api.service.StudyCommitService;
import com.example.backend.study.api.service.commit.response.CommitInfoResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/commits")
public class StudyCommitController {
    private final StudyCommitService studyCommitService;
    private final AuthService authService;

    @PostMapping("/user/{userId}")
    public JsonResult<?> userCommitList(@AuthenticationPrincipal User user,
                                        @PathVariable(name = "userId") Long userId,
                                        @Valid @RequestBody CommitInfoPageRequest request) {

        authService.authenticate(userId, user);

        PageRequest pageable = PageRequest.of(0, request.getPageSize());
        Page<CommitInfoResponse> commitInfoList = studyCommitService.selectUserCommitList(userId, pageable, request.getCursorIdx());
        return JsonResult.successOf(commitInfoList);
    }
}
