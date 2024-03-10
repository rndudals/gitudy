package com.example.backend.study.api.controller.info.response;

import com.example.backend.domain.define.study.info.StudyInfo;
import com.example.backend.domain.define.study.info.constant.StudyPeriodType;
import com.example.backend.domain.define.study.info.constant.StudyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudyInfoDetailResponse {
    private Long id;                                // 아이디

    private Long userId;                            // 스터디장 ID

    private String topic;                           // 스터디 이름

    private int score;                              // 스터디 활동점수

    private String info;                            // 스터디 소개

    private int maximumMember;                      // 스터디 제한 인원

    private int currentMember;                      // 스터디 현재 인원

    private LocalDate lastCommitDay;                // 스터디 마지막 활동 시간

    private String profileImageUrl;                 // 스터디 프로필 사진

    private StudyPeriodType periodType;             // 스터디 커밋 규칙(주기)

    private StudyStatus status;                // 스터디 상태

    private LocalDateTime createdDateTime;          // 스터디 개설 시간

    private LocalDateTime modifiedDateTime;         // 스터디 변경 시간

    private List<Long> categoriesId;              // 카테고리 ID 리스트

    public static StudyInfoDetailResponse of(StudyInfo studyInfo, List<Long> categoriesId){
        return StudyInfoDetailResponse.builder()
                .userId(studyInfo.getUserId())
                .topic(studyInfo.getTopic())
                .score(studyInfo.getScore())
                .info(studyInfo.getInfo())
                .maximumMember(studyInfo.getMaximumMember())
                .currentMember(studyInfo.getCurrentMember())
                .lastCommitDay(studyInfo.getLastCommitDay())
                .profileImageUrl(studyInfo.getProfileImageUrl())
                .periodType(studyInfo.getPeriodType())
                .status(studyInfo.getStatus())
                .createdDateTime(studyInfo.getCreatedDateTime())
                .modifiedDateTime(studyInfo.getModifiedDateTime())
                .categoriesId(categoriesId)
                .build();
    }

    public static StudyInfoDetailResponse of(StudyInfo studyInfo){
        return StudyInfoDetailResponse.builder()
                .userId(studyInfo.getId())
                .topic(studyInfo.getTopic())
                .score(studyInfo.getScore())
                .info(studyInfo.getInfo())
                .maximumMember(studyInfo.getMaximumMember())
                .currentMember(studyInfo.getCurrentMember())
                .lastCommitDay(studyInfo.getLastCommitDay())
                .profileImageUrl(studyInfo.getProfileImageUrl())
                .periodType(studyInfo.getPeriodType())
                .status(studyInfo.getStatus())
                .createdDateTime(studyInfo.getCreatedDateTime())
                .modifiedDateTime(studyInfo.getModifiedDateTime())
                .build();
    }
}
