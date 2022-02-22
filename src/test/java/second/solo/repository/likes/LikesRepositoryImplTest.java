package second.solo.repository.likes;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.repository.account.AccountRepository;
import second.solo.repository.board.BoardRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static second.solo.domain.QAccount.account;
import static second.solo.domain.QBoard.board;
import static second.solo.domain.QLikes.likes;


@SpringBootTest
@Transactional
class LikesRepositoryImplTest {


    @Autowired
    EntityManager em;

    @Autowired
    JPAQueryFactory queryFactory;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    LikesRepository likesRepository;

    @BeforeEach
    public void beforeEach() {
        Account account = Account.builder()
                .email("ekdmd9092")
                .password("1234")
                .username("다응짱")
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
        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd9092");
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
    public void 좋아요_해지() throws Exception {
        //when
        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd9092");
        Optional<Board> findBoard = boardRepository.findById(2L);

        Likes findLike = queryFactory
                .selectFrom(likes)
                .join(likes.account, account).on(likes.account.id.eq(findAccount.get().getId()))
                .join(likes.board, board).on(likes.board.id.eq(findBoard.get().getId()))
                .fetchOne();
        //then
        assertThat(findLike.getAccount().getId()).isEqualTo(findAccount.get().getId());
        assertThat(findLike.getBoard().getId()).isEqualTo(findBoard.get().getId());
    }


}