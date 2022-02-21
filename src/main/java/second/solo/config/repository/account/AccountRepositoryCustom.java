package second.solo.config.repository.account;

import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;

import java.util.Optional;

public interface AccountRepositoryCustom {

    AccountLoginResponseDto login(AccountLoginRequestDto accountRequestDto);
}
