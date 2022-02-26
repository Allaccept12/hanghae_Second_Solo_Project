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
        assertThat(like.getBoard().getLikeList().size()).isEqualTo(findBoard.get().getLikeList().size());
        em.flush();
        em.clear();
    }

    @Test
    public void 게시글_좋아요_해지() throws Exception {
        //given
        Account newAccount = Account.builder()
                .email("ekdmd9092")
                .password("1234")
                .username("다응짱")
                .build();
        accountRepository.save(newAccount);
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

        Optional<Likes> likeByAccountWithBoard = likesRepository.findLikeByAccountWithBoard(findAccount.get().getId(), findBoard.get().getId());
        //when
        likeByAccountWithBoard.get().deleteLike();
        likesRepository.deleteById(likeByAccountWithBoard.get().getId());
        Optional<Likes> likes = likesRepository.findById(1L);
        //then
        assertThat(likes).isEqualTo(Optional.empty());
        em.flush();
        em.clear();
    }

}