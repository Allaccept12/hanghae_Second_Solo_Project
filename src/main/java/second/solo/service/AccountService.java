package second.solo.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;
import second.solo.jwt.JwtTokenProvider;
import second.solo.repository.account.AccountRepository;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long registerAccount(AccountRegisterRequestDto dto) {
        registerValidation(dto);
        String password = passwordEncoder.encode(dto.getPassword());
        dto.encodedPassword(password);
        Account save = accountRepository.save(AccountRegisterRequestDto.toEntity(dto));
        return save.getId();

    }
    @Transactional
    public AccountLoginResponseDto login(AccountLoginRequestDto requestDto) {
        Account account = accountRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ApiRequestException("가입되지 않은 유저입니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), account.getPassword())) {
            throw new ApiRequestException("잘못된 비밀번호입니다.");
        }
        String token = jwtTokenProvider.createToken(Long.toString(account.getId()), account.getEmail(), account.getUsername());
        AccountLoginResponseDto dto = accountRepository.login(requestDto);
        dto.tokenSet(token);

        return dto;
    }
    private void registerValidation(AccountRegisterRequestDto dto) {
        Account account = accountRepository.findByEmail(dto.getAccount_email()).orElse(null);
        if (account != null) {
            throw new ApiRequestException("이메일 중복 오류"); //증복되는 이메일
        }
        if (!Pattern.matches("^[A-Za-z0-9]{3,}$", dto.getAccount_name())){
            throw new ApiRequestException("닉네임 조건에 맞지 않음"); //닉네임 조건에 맞지않음
        }

        if (!dto.getPassword().equals(dto.getPassword_check())
                || dto.getPassword().contains(dto.getAccount_name())){
            throw new ApiRequestException("비밀번호는 조건을 맞춰주세요"); // 비밀번호 맞지 않음
        }
    }

}
