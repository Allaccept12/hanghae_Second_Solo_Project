package second.solo.repository.likes;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import second.solo.domain.Board;

import java.util.List;

import static second.solo.domain.QAccount.account;
import static second.solo.domain.QLikes.*;


@RequiredArgsConstructor
public class LikesRepositoryImpl implements LikesRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Long> findAccountLikeBoard(Long account_id) {
        return queryFactory
                .select(likes.board.id)
                .from(likes)
                .join(likes.account,account)
                .where(likes.account.id.eq(account_id))
                .fetch();
    }

    @Override
    public List<Long> getBoardLikeCount(List<Board> boardList) {
        return queryFactory
                .select(likes.id)
                .from(likes)
                .where(likes.board.in(boardList))
                .fetch();
    }

}
