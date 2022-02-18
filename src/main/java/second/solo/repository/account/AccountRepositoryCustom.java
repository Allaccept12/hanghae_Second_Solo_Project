package second.solo.repository.account;

import second.solo.domain.Account;
import second.solo.dto.AccountRequestDto;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryCustom {

    Optional<List<Account>> login(AccountRequestDto accountRequestDto);
}
