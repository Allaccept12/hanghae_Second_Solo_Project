package second.solo.repository.board;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import second.solo.domain.*;
import second.solo.dto.response.BoardAllResponseDto;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

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










