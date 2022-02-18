package second.solo.repository.account;


import org.springframework.data.jpa.repository.JpaRepository;
import second.solo.domain.Account;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account,Long> , AccountRepositoryCustom{

    List<Account> findByEmail(String email);

    List<Account> findByUsername(String username);

}
