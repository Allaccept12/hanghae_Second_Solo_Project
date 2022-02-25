package second.solo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import second.solo.dto.request.AccountRegisterRequestDto;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
class AccountControllerTest extends BaseIntegrationTest{


    @Test
    public void 회원가입() throws Exception {
        //given
        AccountRegisterRequestDto account = AccountRegisterRequestDto.builder()
                .email("ekdmd")
                .password("1234")
                .passwordCheck("1234")
                .username("ekdmd")
                .build();
        //when
        ResultActions resultActions = mvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(account))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print());
        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("data", is(notNullValue())));

    }







}
























