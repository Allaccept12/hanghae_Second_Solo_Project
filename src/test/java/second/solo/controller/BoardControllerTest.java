package second.solo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import second.solo.service.BoardServiceImpl;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class BoardControllerTest extends BaseIntegrationTest{

    @MockBean
    BoardServiceImpl boardServiceImpl;

    @Test
    public void 전체_게시글_조회() throws Exception {
        //given
        given(boardServiceImpl.pagedBoardSearch(anyLong())).willReturn(any());
        //when - then
        mvc.perform(MockMvcRequestBuilders
                        .get("/api/board")
                        .contentType(APPLICATION_JSON)
                        .param("lastBoardId","0")
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("result",is("success")))
                .andExpect(jsonPath("msg",is("전체 게시글 조회")));
    }

}