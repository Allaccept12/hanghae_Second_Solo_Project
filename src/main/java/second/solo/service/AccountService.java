package second.solo.service;

import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;

public interface AccountService {

    Long registerAccount(AccountRegisterRequestDto dto);

    AccountLoginResponseDto login(AccountLoginRequestDto requestDto);


}
