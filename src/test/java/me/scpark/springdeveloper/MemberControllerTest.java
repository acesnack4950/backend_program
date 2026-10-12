package me.scpark.springdeveloper;

import org.springframework.http.MediaType;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void cleanUp() {
        // 테스트마다 DB를 비워서 서로 영향을 주지 않게 함
        memberRepository.deleteAll();
    }

    @DisplayName("GET /api/members => 저장된 회원 목록을 200 OK로 반환한다.")
    @Test
    void getAllMembers() throws Exception {
        // 준비 given
        memberRepository.save(new Member("홍길동"));

        // 실행 when
        final ResultActions result = mockMvc.perform(get("/api/members"));

        // 검증 then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("홍길동"));
    }
    @DisplayName("GET /quiz?code=1 => 응답 코드는 201, 응답 본문은 Created!를 리턴한다.")
    @Test
    void getQuiz1() throws Exception {
        // 준비 given
        final String url = "/quiz";

        // 실행 when
        final ResultActions result = mockMvc.perform(get(url).param("code", "1"));

        // 검증 then
        result.andExpect(status().isCreated())
                .andExpect(content().string("Created!"));
    }

    @DisplayName("POST /quiz 요청, 요청 바디에 {'value': 2}이면 응답 코드는 200, 응답 본문은 OK~")
    @Test
    public void postQuiz2() throws Exception {
        // 준비 given
        final String url = "/quiz";
        final String requestBody = "{\"value\": 2}";

        // 실행 when
        final ResultActions result = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)   // "보내는 데이터가 JSON이다"라고 알려줌
                .content(requestBody));

        // 검증 then
        result.andExpect(status().isOk())
                .andExpect(content().string("OK!"));
    }
}