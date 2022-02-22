package second.solo.service;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.dto.response.BoardAllResponseDto;
import second.solo.repository.account.AccountRepository;
import second.solo.repository.board.BoardRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    AccountRepository accountRepository;

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
    public void 전체_게시글_목록() throws Exception {
        //when
        List<Board> boardList = boardRepository.findAllBoard();
        List<BoardAllResponseDto> findBoard = boardList.stream()
                .map(b -> BoardAllResponseDto.of(b, b.getLikeCount()))
                .collect(Collectors.toList());
        //then

        assertThat(boardList.size()).isEqualTo(3);
        assertThat(findBoard.get(1).getContent()).isEqualTo("테스트 테스트1");
        assertThat(findBoard.get(2).getContent()).isEqualTo("테스트 테스트2");
        assertThat(findBoard.get(3).getContent()).isEqualTo("테스트 테스트3");
    }


}