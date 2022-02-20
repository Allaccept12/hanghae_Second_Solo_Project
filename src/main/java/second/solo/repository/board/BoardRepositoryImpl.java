package second.solo.repository.board;


import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import second.solo.domain.Board;
import second.solo.domain.QAccount;
import second.solo.domain.QBoard;
import second.solo.domain.QLikes;
import second.solo.dto.response.BoardAllResponseDto;

import java.util.List;

import static second.solo.domain.QAccount.*;
import static second.solo.domain.QBoard.*;
import static second.solo.domain.QLikes.*;

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










