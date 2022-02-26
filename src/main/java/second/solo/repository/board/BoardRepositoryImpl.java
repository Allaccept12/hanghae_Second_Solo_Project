package second.solo.repository.board;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import second.solo.domain.*;

import java.util.List;

import static second.solo.domain.QBoard.*;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<Board> findByBoardAtLimit(Long lastBoardId, Pageable paging) {
        List<Board> result = queryFactory
                .select(board)
                .from(board)
                .offset(paging.getOffset())
                .limit(paging.getPageSize())
                .where(isLastBoardId(lastBoardId))
                .orderBy(board.id.desc())
                .fetch();
        int count = result.size();
        return new PageImpl<>(result,paging,count);
    }

    public BooleanExpression isLastBoardId(Long lastBoardId) {
        return lastBoardId != 0 ? board.id.lt(lastBoardId) : null;
    }


}










