package second.solo.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.dto.response.BoardLikeCountResponseDto;
import second.solo.repository.account.AccountRepository;
import second.solo.repository.board.BoardRepository;
import second.solo.repository.likes.LikesRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@Transactional
class LikesServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LikesRepository likesRepository;

    @Autowired
    EntityManager em;

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
                .content("테스트 테스트2")
                .build();
        Board board3 = Board.builder()
                .account(account)
                .content("테스트 테스트3")
                .build();

        em.persist(board);
        em.persist(board2);
        em.persist(board3);

        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd9092");
        Likes likes1 = Likes.builder()
                .account(findAccount.get())
                .board(board)
                .build();
        em.persist(likes1);
        em.flush();
        em.clear();

    }

    @Test
    public void 게시글_좋아요() throws Exception {
        //given
        Optional<Account> account = accountRepository.findById(1L);
        Optional<Board> findBoard = boardRepository.findById(1L);
        //when
        Likes likes= Likes.builder()
                .account(account.get())
                .board(findBoard.get())
                .build();
        Likes like = likesRepository.save(likes);
        //then
        assertThat(like.getAccount().getId()).isEqualTo(1L);
        assertThat(like.getBoard().getId()).isEqualTo(1L);
        assertThat(like.getBoard().getLikeCount()).isEqualTo(1);
    }

    @Test
    public void 게시글_좋아요_해지() throws Exception {
        //given
        Optional<Board> findBoard = boardRepository.findById(1L);
        Optional<List<Likes>> likeByAccountWithBoard = likesRepository.findLikeByAccountWithBoard(1L, findBoard.get().getId());
        //when
        likeByAccountWithBoard.get().get(0).deleteLike();
        likesRepository.deleteById(likeByAccountWithBoard.get().get(0).getId());
        Optional<Likes> likes = likesRepository.findById(1L);
        //then
        assertThat(likes).isEqualTo(Optional.empty());

    }

}