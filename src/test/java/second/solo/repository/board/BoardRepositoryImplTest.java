package second.solo.repository.board;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static second.solo.domain.QBoard.board;
import static second.solo.domain.QLikes.likes;


@SpringBootTest
@Transactional
class BoardRepositoryImplTest {

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
                .email("ekdmd9092")
                .password("1234")
                .username("다응짱")
                .build();
        accountRepository.save(account);
        Board board = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .boardStatus("1")
                .imgUrl("1")
                .build();
        em.persist(board);
        em.flush();
        em.clear();
        Board board2 = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .boardStatus("1")
                .imgUrl("1")
                .build();
        em.persist(board2);
        em.flush();
        em.clear();
        Board board3 = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .boardStatus("1")
                .imgUrl("1")
                .build();
        em.persist(board3);
        em.flush();
        em.clear();
        Board board4 = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .boardStatus("1")
                .imgUrl("1")
                .build();
        em.persist(board4);
        em.flush();
        em.clear();
        Board board5 = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .boardStatus("1")
                .imgUrl("1")
                .build();
        em.persist(board5);
        em.flush();
        em.clear();
        Board board6 = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .boardStatus("1")
                .imgUrl("1")
                .build();
        em.persist(board6);
        em.flush();
        em.clear();

        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd9092");
        Likes likes1 = Likes.builder()
                .account(findAccount.get())
                .board(board)
                .build();
        Likes likes2 = Likes.builder()
                .account(findAccount.get())
                .board(board)
                .build();

        Likes likes3 = Likes.builder()
                .account(findAccount.get())
                .board(board3)
                .build();
        Likes likes4 = Likes.builder()
                .account(findAccount.get())
                .board(board3)
                .build();
        Likes likes5 = Likes.builder()
                .account(findAccount.get())
                .board(board3)
                .build();
        em.persist(likes1);
        em.persist(likes2);
        em.persist(likes3);
        em.persist(likes4);
        em.persist(likes5);

        em.flush();
        em.clear();

    }

//    @Test
//    @Commit
//    public void 전체_게시글() throws Exception {
//        Pageable paging = PageRequest.of(0,5);
//        Long lastBoardId = 4L;
//        //when
//        List<Tuple> result = queryFactory
//                .select(board,likes)
//                .from(board)
//                .offset(paging.getOffset())
//                .limit(paging.getPageSize())
//                .leftJoin(likes).on(board.id.eq(likes.board.id)).fetchJoin()
//                .where(isLastBoardId(lastBoardId))
//                .orderBy(board.id.desc())
//                .fetch();
////        List<BoardAllResponseDto> boards = result.stream()
////                .map(s -> {
////                    if (s.get(board.id) == s.get(likes.board.id))
////                        BoardAllResponseDto.of(board,)
////                })
////                .collect(Collectors.toList());
////
////        List<Likes> likesList = result.stream()
////                .map(r -> r.get(likes))
////                .filter(Objects::nonNull)
////                .collect(Collectors.toList());
////
////        Board boardInfo = result.stream()
////                .map(r -> r.get(board))
////                .findFirst()
////                .get();
//
//        //then
//        for (Tuple tuple : result) {
//            System.out.println("tuple = " + tuple);
//        }
//    }
//    public BooleanExpression isLastBoardId(Long lastBoardId) {
//        return lastBoardId != 0 ? board.id.lt(lastBoardId) : null;
//    }


}









