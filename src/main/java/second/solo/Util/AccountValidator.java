package second.solo.Util;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.request.AccountRegisterRequestDto;

import java.util.regex.Pattern;


public class AccountValidator {

    public static void registerValidation(AccountRegisterRequestDto dto, Account account) {
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

    public static void loginValidation(PasswordEncoder passwordEncoder,String requestDto, String account) {
        if (!passwordEncoder.matches(requestDto, account)) {
            throw new ApiRequestException("잘못된 비밀번호입니다.");
        }
    }

}
