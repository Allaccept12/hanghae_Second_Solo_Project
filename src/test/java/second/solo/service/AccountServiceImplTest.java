package second.solo.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import second.solo.domain.Account;
import second.solo.domain.Board;
import second.solo.domain.Likes;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.jwt.JwtTokenProvider;
import second.solo.repository.account.AccountRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class AccountServiceImplTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EntityManager em;

    @AfterEach
    public void downSet() {
        accountRepository.deleteAll();
    }

    @BeforeEach
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
    public void 회원가입() throws Exception {
        //given
        AccountRegisterRequestDto dto = AccountRegisterRequestDto.builder()
                .email("ekdmd")
                .password("1234")
                .passwordCheck("1234")
                .username("ekdmd")
                .build();
        //when
        String password = passwordEncoder.encode(dto.getPassword());
        dto.encodedPassword(password);
        Long id = accountRepository.save(AccountRegisterRequestDto.toEntity(dto)).getId();
        Optional<Account> findAccount = accountRepository.findById(id);
        //then
        assertThat(findAccount.get().getEmail()).isEqualTo("ekdmd");
        assertThat(findAccount.get().getUsername()).isEqualTo("ekdmd");
        assertThat(findAccount.get().getPassword()).isEqualTo(password);
    }

    @Test
    public void 로그인_토큰발급() throws Exception {
        //given
        AccountLoginRequestDto requestDto = AccountLoginRequestDto.builder()
                .email("ekdmd9092")
                .password("1234")
                .build();
        //when
        Optional<Account> account = accountRepository.findByEmail(requestDto.getEmail());
        String token = jwtTokenProvider.createToken(Long.toString(account.get().getId()), account.get().getEmail(), account.get().getUsername());
        //then

        assertThat(token).isNotEmpty();

    }


}