package second.solo.repository.account;

import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;

public interface AccountRepositoryCustom {

    AccountLoginResponseDto login(AccountLoginRequestDto accountRequestDto);
}
