package com.example.backend.domain.define.study.commit.repository;

import com.example.backend.auth.TestConfig;
import com.example.backend.domain.define.study.commit.StudyCommit;
import com.example.backend.domain.define.study.commit.StudyCommitFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Random;

import static com.example.backend.domain.define.study.commit.StudyCommitFixture.createDefaultStudyCommitList;
import static com.example.backend.domain.define.study.commit.StudyCommitFixture.expectedUserId;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SuppressWarnings("NonAsciiCharacters")
class StudyCommitRepositoryTest extends TestConfig {

    @Autowired
    StudyCommitRepository studyCommitRepository;

    @AfterEach
    void tearDown() {
        studyCommitRepository.deleteAllInBatch();
    }

    @Test
    void 마이_커밋_오프셋_기반_페이지_조회_테스트() {
        // given
        int pageNumber = 1;
        int pageSize = 10;
        int dataSize = 20;

        PageRequest pageable = PageRequest.of(pageNumber, pageSize);

        List<StudyCommit> commitList = createDefaultStudyCommitList(dataSize);
        studyCommitRepository.saveAll(commitList);

        // when
        Page<StudyCommit> resultPage = studyCommitRepository.findStudyCommitListByUserId_OffsetPaging(pageable, expectedUserId);
        List<StudyCommit> content = resultPage.getContent();
//        for (StudyCommit sc : content) {
//            System.out.println("sc.getCommitSHA() = " + sc.getCommitSHA());
//        }

        // then
        assertEquals(dataSize, resultPage.getTotalElements());
        assertEquals(pageNumber>=dataSize/pageSize ? 0 : pageSize, resultPage.getContent().size());
        assertEquals(pageNumber, resultPage.getNumber());
    }

    @Test
    void 마이_커밋_커서_기반_페이지_조회_테스트() {
        // given
        int pageSize = 10;
        int dataSize = 20;
        Random random = new Random();
        Long cursorIdx = (long) random.nextInt(dataSize) + 1L;

        PageRequest pageable = PageRequest.of(any(Integer.class), pageSize);

        List<StudyCommit> commitList = createDefaultStudyCommitList(dataSize);
        studyCommitRepository.saveAll(commitList);

        // when
        Page<StudyCommit> resultPage = studyCommitRepository.findStudyCommitListByUserId_CursorPaging(pageable, expectedUserId, cursorIdx);
        List<StudyCommit> content = resultPage.getContent();
//        for (StudyCommit sc : content) {
//            System.out.println("sc.getId() = " + sc.getId());
//        }

        // then
        assertEquals(dataSize, resultPage.getTotalElements());
        assertEquals(cursorIdx <= pageSize ? cursorIdx - 1 : pageSize, resultPage.getContent().size());

        for (StudyCommit commit : resultPage.getContent()) {
            assertTrue(commit.getId() < cursorIdx);
            assertEquals(expectedUserId, commit.getUserId());
        }
    }

}