package second.solo.repository.likes;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import second.solo.domain.Likes;

import java.util.List;
import java.util.Optional;

import static second.solo.domain.QAccount.account;
import static second.solo.domain.QBoard.board;
import static second.solo.domain.QLikes.*;


@RequiredArgsConstructor
public class LikesRepositoryImpl implements LikesRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public Optional<Likes> findLikeByAccountWithBoard(Long accountId, Long boardId) {
        return Optional.ofNullable(queryFactory
                .selectFrom(likes)
                .join(likes.account, account).on(likes.account.id.eq(accountId)).fetchJoin()
                .join(likes.board, board).on(likes.board.id.eq(boardId)).fetchJoin()
                .fetchOne());
    }
}
