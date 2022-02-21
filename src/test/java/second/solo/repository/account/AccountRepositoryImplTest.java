package second.solo.repository.account;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;
import second.solo.repository.likes.LikesRepository;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class AccountRepositoryImplTest {

    @Autowired
    EntityManager em;

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LikesRepository likesRepository;

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
        Board board2 = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .build();
        Board board3 = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .build();

        em.persist(board);
        em.persist(board2);
        em.persist(board3);
        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd90921");
        Likes likes1 = Likes.builder()
                .account(findAccount.get())
                .board(board)
                .build();

        Likes likes2 = Likes.builder()
                .account(findAccount.get())
                .board(board2)
                .build();

        Likes likes3 = Likes.builder()
                .account(findAccount.get())
                .board(board3)
                .build();
        em.persist(likes1);
        em.persist(likes2);
        em.persist(likes3);
        em.flush();
        em.clear();

    }

    @Test
    @Commit
    public void 로그인_테스트() throws Exception {
//        //given
        AccountLoginResponseDto accountLoginResponseDto = accountRepository.
                login(AccountLoginRequestDto.builder().email("ekdmd90921").password("12341").build());

        for (Long aLong : accountLoginResponseDto.getBoard_id()) {
            System.out.println("aLong = " + aLong);
        }
        assertThat(accountLoginResponseDto.getAccount_id()).isEqualTo(1);
        assertThat(accountLoginResponseDto.getBoard_id().size()).isEqualTo(3);
        assertThat(accountLoginResponseDto.getAccount_email()).isEqualTo("ekdmd90921");


    }



}






