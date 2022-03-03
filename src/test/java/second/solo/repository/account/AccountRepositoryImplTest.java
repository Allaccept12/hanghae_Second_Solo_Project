//package second.solo.repository.account;
//
//import com.querydsl.core.Tuple;
//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.jpa.impl.JPAQueryFactory;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//import second.solo.domain.Account;
//import second.solo.domain.Board;
//import second.solo.domain.Likes;
//import second.solo.repository.likes.LikesRepository;
//
//import javax.persistence.EntityManager;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.springframework.util.StringUtils.hasText;
//import static second.solo.domain.QAccount.account;
//import static second.solo.domain.QLikes.likes;
//
//
//@SpringBootTest
//@Transactional
//class AccountRepositoryImplTest {
//
//    @Autowired
//    EntityManager em;
//
//    @Autowired
//    JPAQueryFactory queryFactory;
//
//    @Autowired
//    AccountRepository accountRepository;
//
//    @Autowired
//    LikesRepository likesRepository;
//
//    @AfterEach
//    public void downSet() {
//        accountRepository.deleteAll();
//        likesRepository.deleteAll();
//    }
//
//    @BeforeEach
//    public void beforeEach() {
//        Account account = Account.builder()
//                .email("ekdmd9092")
//                .password("1234")
//                .username("다응짱")
//                .build();
//        accountRepository.save(account);
//
//
//        Board board = Board.builder()
//                .account(account)
//                .content("테스트 테스트1")
//                .build();
//        Board board2 = Board.builder()
//                .account(account)
//                .content("테스트 테스트1")
//                .build();
//        Board board3 = Board.builder()
//                .account(account)
//                .content("테스트 테스트1")
//                .build();
//
//        em.persist(board);
//        em.persist(board2);
//        em.persist(board3);
//        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd9092");
//        Likes likes1 = Likes.builder()
//                .account(findAccount.get())
//                .board(board)
//                .build();
//
//        Likes likes2 = Likes.builder()
//                .account(findAccount.get())
//                .board(board2)
//                .build();
//
//        Likes likes3 = Likes.builder()
//                .account(findAccount.get())
//                .board(board3)
//                .build();
//        em.persist(likes1);
//        em.persist(likes2);
//        em.persist(likes3);
//        em.flush();
//        em.clear();
//
//    }
//
//    @Test
//    public void 이메일로_찾기() throws Exception {
//        //when
//        Optional<Account> findAccount = accountRepository.findByEmail("ekdmd9092");
//        //then
//        assertThat(findAccount.get().getEmail()).isEqualTo("ekdmd9092");
//        assertThat(findAccount.get().getPassword()).isEqualTo("1234");
//        assertThat(findAccount.get().getUsername()).isEqualTo("다응짱");
//    }
//
//
//    @Test
//    public void 로그인_쿼리_테스트() throws Exception {
//        //given
//        List<Tuple> result = queryFactory
//                .select(account, likes)
//                .from(account)
//                .leftJoin(likes).on(account.id.eq(likes.account.id)).fetchJoin()
//                .where(userEmailEq("ekdmd9092"))
//                .fetch();
//
//        List<Likes> likesList = result.stream()
//                .map(r -> r.get(likes))
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//
//        Account accountInfo = result.stream()
//                .map(r -> r.get(account))
//                .findFirst()
//                .get();
//
//        assertThat(likesList.size()).isEqualTo(3);
//        assertThat(accountInfo.getEmail()).isEqualTo("ekdmd9092");
//        assertThat(accountInfo.getPassword()).isEqualTo("1234");
//        assertThat(accountInfo.getUsername()).isEqualTo("다응짱");
//
//    }
//
//    private BooleanExpression userEmailEq(String userEmail) {
//        return hasText(userEmail) ? account.email.eq(userEmail) : null;
//    }
//
//
//
//}
//
//
//
//
//
//
