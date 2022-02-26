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

    @Test
    public void 좋아요_해지() throws Exception {
        //given
        Account newAccount = Account.builder()
                .email("ekdmd9092")
                .password("1234")
                .username("다응짱")
                .build();
        Account savedAccount = accountRepository.save(newAccount);
        Board newBoard = Board.builder()
                .account(newAccount)
                .content("테스트 테스트1")
                .build();
        Board savedBoard = boardRepository.save(newBoard);

        Likes likes1 = Likes.builder()
                .account(savedAccount)
                .board(savedBoard)
                .build();
        likesRepository.save(likes1);
        //when
        Likes findLike = queryFactory
                .selectFrom(likes)
                .join(likes.account, account).on(likes.account.id.eq(savedAccount.getId()))
                .join(likes.board, board).on(likes.board.id.eq(savedBoard.getId()))
                .fetchOne();
        findLike.deleteLike();
        likesRepository.deleteById(findLike.getId());
        //then
        assertThat(savedBoard.getLikeList().size()).isEqualTo(0);
    }


}

