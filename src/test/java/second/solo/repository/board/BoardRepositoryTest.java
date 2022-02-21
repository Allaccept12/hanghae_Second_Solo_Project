package second.solo.repository.board;


import com.querydsl.core.types.Projections;
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
import second.solo.dto.response.BoardAllResponseDto;
import second.solo.repository.account.AccountRepository;
import second.solo.repository.likes.LikesRepository;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

import static second.solo.domain.QAccount.account;
import static second.solo.domain.QBoard.board;
import static second.solo.domain.QLikes.likes;

@SpringBootTest
@Transactional
public class BoardRepositoryTest {
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
        Optional<Account> find = accountRepository.findByEmail("ekdmd90921");
        Likes likes1 = Likes.builder()
                .account(find.get())
                .board(board)
                .build();

        Likes likes2 = Likes.builder()
                .account(find.get())
                .board(board2)
                .build();

        Likes likes3 = Likes.builder()
                .account(find.get())
                .board(board3)
                .build();
        em.persist(likes1);
        em.persist(likes2);
        em.persist(likes3);

    }

    @Test
    @Commit
    public void 모든_게시물() throws Exception {
        //when
        List<BoardAllResponseDto> result = queryFactory
                .select(Projections.constructor(BoardAllResponseDto.class,
                        board.id.as("board_id"),
                        board.account.id.as("account_id"),
                        board.account.username.as("account_name"),
                        board.content,
                        board.created
                ))
                .from(board)
                .join(board.account, account)
                .leftJoin(likes).on(likes.board.id.eq(board.id))
                .fetch();

        Long count = queryFactory
                .selectFrom(likes)
                .leftJoin(likes.board,board)
                .where()
                .stream().count();

        //then
//        for (BoardAllResponseDto boardAllResponseDto : result) {
//            System.out.println("boardAllResponseDto = " + boardAllResponseDto.getBoard_id());
//        }
    }

}
