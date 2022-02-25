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
        em.flush();
        em.clear();
    }
    @org.junit.jupiter.api.AfterEach
    public void downSet() {
        accountRepository.deleteAll();
        boardRepository.deleteAll();
        likesRepository.deleteAll();
    }

    @Test
    public void 게시글_좋아요() throws Exception {
        //given
        Account newAccount = Account.builder()
                .email("ekdmd9092")
                .password("1234")
                .username("다응짱")
                .build();
        Account savedAccount = accountRepository.save(newAccount);
        Optional<Account> account = accountRepository.findById(savedAccount.getId());
        Board board = Board.builder()
                .account(account.get())
                .content("테스트 테스트1")
                .build();
        Board saveBoard = boardRepository.save(board);
        em.flush();
        em.clear();
        //when
        Optional<Board> findBoard = boardRepository.findById(saveBoard.getId());
        Likes likes= Likes.builder()
                .account(account.get())
                .board(findBoard.get())
                .build();
        Likes like = likesRepository.save(likes);
        //then
        assertThat(like.getAccount().getId()).isEqualTo(findBoard.get().getAccount().getId());
        assertThat(like.getBoard().getId()).isEqualTo(findBoard.get().getId());
        assertThat(like.getBoard().getLikeCount()).isEqualTo(findBoard.get().getLikeCount());
        em.flush();
        em.clear();
    }

    @Test
    public void 게시글_좋아요_해지() throws Exception {
        //given
        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd9092");
        Board board = Board.builder()
                .account(findAccount.get())
                .content("테스트 테스트1")
                .build();
        Board savedBoard = boardRepository.save(board);
        Long requestBoardId = savedBoard.getId();
        Optional<Board> findBoard = boardRepository.findById(requestBoardId);
        Likes likes1 = Likes.builder()
                .account(findAccount.get())
                .board(findBoard.get())
                .build();
        em.persist(likes1);
        em.flush();
        em.clear();

        Optional<List<Likes>> likeByAccountWithBoard = likesRepository.findLikeByAccountWithBoard(findAccount.get().getId(), findBoard.get().getId());
        //when
        likeByAccountWithBoard.get().get(0).deleteLike();
        likesRepository.deleteById(likeByAccountWithBoard.get().get(0).getId());
        Optional<Likes> likes = likesRepository.findById(1L);
        //then
        assertThat(likes).isEqualTo(Optional.empty());
        em.flush();
        em.clear();
    }

}