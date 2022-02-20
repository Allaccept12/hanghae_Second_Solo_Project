package second.solo.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;
import second.solo.repository.account.AccountRepository;

import java.util.Objects;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public void registerAccount(AccountRegisterRequestDto dto) {
        registerValidation(dto);
        accountRepository.save(AccountRegisterRequestDto.toEntity(dto));
    }

    public AccountLoginResponseDto doLogin(AccountLoginRequestDto accountRequestDto) {
        accountRepository.findByEmailAndPassword(accountRequestDto.getEmail(),accountRequestDto.getPassword())
                .orElseThrow(() -> new ApiRequestException("비밀번호와 이메일을 다시 확인 해주세요."));
        return accountRepository.login(accountRequestDto);
    }



    private void registerValidation(AccountRegisterRequestDto dto) {
        Account account = accountRepository.findByEmail(dto.getEmail()).orElse(null);
        if (account != null) {
            throw new ApiRequestException("이메일 중복 오류"); //증복되는 이메일
        }
        if (!Pattern.matches("^[A-Za-z0-9]{3,}$", dto.getUsername())){
            throw new ApiRequestException("닉네임 조건에 맞지 않음"); //닉네임 조건에 맞지않음
        }
        if (!Objects.equals(dto.getPassword(), dto.getPasswordCheck())
                || dto.getPassword().contains(dto.getUsername())
                || dto.getUsername().length() < 4 ){
            throw new ApiRequestException("비밀번호 맞지 않음"); // 비밀번호 맞지 않음
        }
    }

}
