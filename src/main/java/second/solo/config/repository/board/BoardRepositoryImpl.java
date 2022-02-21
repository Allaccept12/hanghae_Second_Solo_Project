package second.solo.config.repository.board;


import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import second.solo.domain.*;

import java.util.List;

import static second.solo.domain.QBoard.*;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public List<Board> findAllBoard() {
        return queryFactory
                .select(board)
                .from(board)
                .fetch();

    }
}










