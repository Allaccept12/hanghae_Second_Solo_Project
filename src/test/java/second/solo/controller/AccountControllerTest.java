package second.solo.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import second.solo.advice.Success;
import second.solo.domain.Account;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.repository.account.AccountRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @AfterEach
    public void delete() {
        accountRepository.deleteAll();
    }

    @Test
    public void 로그인_컨트롤러_테스트() throws Exception {
        //given
        String email = "ekdmd9201@gmail.com";
        String password = "1234";
        String passwordCheck = "1234";
        String username = "jay";

        AccountRegisterRequestDto registerDto = AccountRegisterRequestDto.builder()
                .email(email)
                .password(password)
                .passwordCheck(passwordCheck)
                .username(username)
                .build();
        accountRepository.save(AccountRegisterRequestDto.toEntity(registerDto));

        //로그인
        AccountLoginRequestDto requestDto = AccountLoginRequestDto.builder()
                .email(email)
                .password(password)
                .build();
        String url = "http://localhost:" + port + "/api/login";
        //when
        ResponseEntity<Success> responseEntity = restTemplate.postForEntity(url,requestDto,Success.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(0L);

        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd9201@gmail.com");
        assertThat(findAccount.get().getEmail()).isEqualTo(email);
        assertThat(findAccount.get().getPassword()).isEqualTo(password);

    }














}