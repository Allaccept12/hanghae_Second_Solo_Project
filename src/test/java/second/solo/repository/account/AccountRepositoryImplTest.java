package second.solo.repository.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.dto.AccountRequestDto;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class AccountRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    AccountRepository accountRepository;

    @BeforeEach
    public void beforeEach() {
        Account account = Account.builder()
                .email("ekdmd90921")
                .password("12341")
                .username("다응짱2")
                .build();
        accountRepository.save(account);


        Board board = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .build();

        em.persist(board);
        em.flush();
        em.clear();
        List<Account> findAccount = accountRepository.findByEmail("ekdmd90921");
        Likes likes = Likes.builder()
                .account(findAccount.get(0))
                .board(board)
                .build();
        em.persist(likes);



    }

    @Test
    @Commit
    public void 로그인_테스트() throws Exception {
        //given
        Optional<List<Account>> logMember = accountRepository.login(new AccountRequestDto("ekdmd90921", "12341"));
        //then
        List<Likes> likesList = logMember.get().get(0).getLikesList();
        String findEmail = logMember.get().get(0).getEmail();
        String findName = logMember.get().get(0).getUsername();

        assertThat(likesList.size()).isEqualTo(1);
        assertThat(findEmail).isEqualTo("ekdmd90921");
        assertThat(findName).isEqualTo("다응짱2");
    }



}






