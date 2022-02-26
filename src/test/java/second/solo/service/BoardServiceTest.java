package second.solo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.dto.request.BoardCreateRequestDto;
import second.solo.repository.account.AccountRepository;
import second.solo.repository.board.BoardRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@Slf4j
class BoardServiceTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    EntityManager em;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        Account account = Account.builder()
                .email("ekdmd9092")
                .password("1234")
                .username("다응짱")
                .build();
        em.persist(account);

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

    @org.junit.jupiter.api.AfterEach
    public void downSet() {
        accountRepository.deleteAll();
        boardRepository.deleteAll();
    }

    @org.junit.jupiter.api.Test
    public void 전체_게시글_목록() throws Exception {
        //when
//        List<Board> boardList = boardRepository.findAllBoard();
//        List<BoardAllResponseDto> findBoard = boardList.stream()
//                .map(b -> BoardAllResponseDto.of(b, b.getLikeCount()))
//                .collect(Collectors.toList());
//        //then
//
//        assertThat(boardList.size()).isEqualTo(3);
//        assertThat(findBoard.get(0).getContent()).isEqualTo("테스트 테스트1");
//        assertThat(findBoard.get(1).getContent()).isEqualTo("테스트 테스트2");
//        assertThat(findBoard.get(2).getContent()).isEqualTo("테스트 테스트3");
    }
    @org.junit.jupiter.api.Test
    public void 게시글_수정() throws Exception {
        //given
        Account account = Account.builder()
                .email("ekdmd90921")
                .password("12341")
                .username("다응짱1")
                .build();
        Account savedAccount = accountRepository.save(account);
        Board board = Board.builder()
                .account(account)
                .content("테스트 테스트1")
                .build();
        Board savedBoard = boardRepository.save(board);
        em.flush();
        em.clear();

        Long findBoardId = savedBoard.getId();
        Long ownerId = savedAccount.getId();

        Optional<Board> findBoard = boardRepository.findById(findBoardId);
        String beforeContent = findBoard.get().getContent();
        Optional<Account> boardOwner = accountRepository.findById(ownerId);
        //when
        findBoard.get().updateContent("테스트 수정","asd","asd");
        Optional<Board> updatedBoard = boardRepository.findById(findBoardId);
        //then
        assertThat(updatedBoard.get().getAccount()).isEqualTo(boardOwner.get());
        assertThat(beforeContent).isNotEqualTo(updatedBoard.get().getContent());
        em.flush();
        em.clear();
    }

    @org.junit.jupiter.api.Test
    public void 게시글_등록() {
        //given
        Optional<Account> client = accountRepository.findByEmail("ekdmd9092");
        BoardCreateRequestDto createDto = BoardCreateRequestDto.builder()
                .content("테스트 컨텐트")
                .img_url("http어쩌고")
                .board_status("0")
                .build();
        //when
        Board savedBoard = boardRepository.save(BoardCreateRequestDto.toEntity(createDto, client.get()));
        Optional<Board> findBoard = boardRepository.findById(savedBoard.getId());
        //then
        assertThat(findBoard.get().getId()).isEqualTo(savedBoard.getId());
        assertThat(findBoard.get().getContent()).isEqualTo("테스트 컨텐트");
        em.flush();
        em.clear();
    }


    @org.junit.jupiter.api.Test
    public void 게시글_삭제() throws Exception {
        //given
        Optional<Account> client = accountRepository.findByEmail("ekdmd9092");
        BoardCreateRequestDto createDto = BoardCreateRequestDto.builder()
                .content("테스트 컨텐트")
                .img_url("http어쩌고")
                .board_status("0")
                .build();
        Board savedBoard = boardRepository.save(BoardCreateRequestDto.toEntity(createDto, client.get()));
        //when
        boardRepository.deleteById(savedBoard.getId());
        List<Board> allBoard = boardRepository.findAll();
        //then
        assertThat(allBoard).doesNotContain(savedBoard);
        em.flush();
        em.clear();
    }




}







