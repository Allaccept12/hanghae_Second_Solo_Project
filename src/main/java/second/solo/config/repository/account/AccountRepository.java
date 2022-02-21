package second.solo.config.repository.account;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import second.solo.domain.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> , AccountRepositoryCustom{

    Optional<Account> findByEmail(String email);

    @Query("select a from Account a where a.username = :name")
    Optional<Account> findByName(@Param("name") String name);

    Optional<Account> findByEmailAndPassword(String email,String password);

    List<Account> findByUsername(String username);

}
