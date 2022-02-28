package second.solo.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;
import second.solo.service.AccountServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@SpringBootTest
class AccountControllerTest extends BaseIntegrationTest{

    @MockBean
    private AccountServiceImpl accountServiceImpl;

    @Test
    public void 회원가입() throws Exception {
        //given
        AccountRegisterRequestDto account = registerAccount();
        //when - then
        given(accountServiceImpl.registerAccount(new AccountRegisterRequestDto())).willReturn(anyLong());

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/register")
                        .content(mapper.writeValueAsString(account))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("result",is("success")))
                .andExpect(jsonPath("msg",is("회원가입 성공")));
    }

    @Test
    public void 로그인_성공() throws Exception {
        //given
        AccountLoginRequestDto account = AccountLoginRequestDto.builder().email("ekdmd").password("1234").build();
        //when - then
        given(accountServiceImpl.login(new AccountLoginRequestDto())).willReturn(ArgumentMatchers.any((AccountLoginResponseDto.class)));

        mvc.perform(MockMvcRequestBuilders
                        .post("/api/login")
                        .content(mapper.writeValueAsString(account))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("result",is("success")))
                .andExpect(jsonPath("msg",is("로그인 성공")));
    }

    private AccountRegisterRequestDto registerAccount() {
        return AccountRegisterRequestDto.builder()
                .email("ekdmd122")
                .password("1234122")
                .passwordCheck("1234122")
                .username("jay122")
                .build();
    }







}
























