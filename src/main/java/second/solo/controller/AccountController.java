package second.solo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import second.solo.advice.ApiRequestException;
import second.solo.advice.Success;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.jwt.UserDetailsImpl;
import second.solo.service.AccountService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/login")
    public ResponseEntity<Success> login(@RequestBody AccountLoginRequestDto requestDto,
                                         @AuthenticationPrincipal UserDetailsImpl accountDetails) {
        if (accountDetails != null) {
            throw new ApiRequestException("이미 로그인 되어있습니다.");
        }
        return new ResponseEntity<>(new Success<>(
                "로그인 성공", accountService.login(requestDto)), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Success> register(@RequestBody AccountRegisterRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl accountDetails) {
        if (accountDetails != null) {
            throw new ApiRequestException("이미 로그인 되어있습니다.");
        }
        return new ResponseEntity<>(new Success<>(
                "회원가입 성공", accountService.registerAccount(requestDto)), HttpStatus.OK);
    }




}
