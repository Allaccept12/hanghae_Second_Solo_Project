package second.solo.repository.account;


import org.springframework.data.jpa.repository.JpaRepository;
import second.solo.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> , AccountRepositoryCustom{

    Optional<Account> findByEmail(String email);

    Optional<Account> findByEmailAndPassword(String email,String password);

    List<Account> findByUsername(String username);

}
