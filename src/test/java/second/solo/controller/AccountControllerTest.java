package second.solo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import second.solo.domain.Account;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;
import second.solo.service.AccountService;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AccountControllerTest extends BaseIntegrationTest{

    @Mock
    private AccountService accountService;



    @Test
    public void 회원가입() throws Exception {
        //given
        AccountRegisterRequestDto account = registerAccount();
        //when
        ResultActions resultActions = mvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(account))
                        .accept(MediaType.APPLICATION_JSON));
//        //then
        resultActions
                .andExpect(status().isOk())
                .andExpect(jsonPath("data", is(notNullValue())));
    }

    @Test
    public void 로그인_실패_비밀번호오류() throws Exception {
        //given
//        AccountLoginRequestDto loginAccount = AccountLoginRequestDto.builder()
//                .email("ekdmd")
//                .password("1234")
//                .build();
//        doReturn(new AccountLoginResponseDto()).when(accountService).login(loginAccount);
//        //when
//        ResultActions resultActions = mvc.perform(post("/api/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(loginAccount))
//                .accept(MediaType.APPLICATION_JSON));
//        //then
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("result",is("fail")))
//                .andExpect(jsonPath("msg",is("가입되지 않은 유저입니다.")));
    }

    @Test
    public void 로그인_실패_미가입유저() throws Exception {
        //given
//        AccountLoginRequestDto loginAccount = AccountLoginRequestDto.builder()
//                .email("ekdmd")
//                .password("1234")
//                .build();
//        //when
//        ResultActions resultActions = mvc.perform(post("/api/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(mapper.writeValueAsString(loginAccount))
//                .accept(MediaType.APPLICATION_JSON));
//        //then
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("result",is("fail")))
//                .andExpect(jsonPath("msg",is("가입되지 않은 유저입니다.")));
    }

    private AccountRegisterRequestDto registerAccount() {
        return AccountRegisterRequestDto.builder()
                .email("ekdmd")
                .password("1234")
                .passwordCheck("1234")
                .username("jay")
                .build();
    }







}
























