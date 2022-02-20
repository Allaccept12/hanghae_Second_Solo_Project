package second.solo.repository.account;


import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import second.solo.domain.Account;
import second.solo.domain.Likes;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;
import static second.solo.domain.QAccount.*;
import static second.solo.domain.QLikes.*;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public AccountLoginResponseDto login(AccountLoginRequestDto dto) {
        List<Tuple> result = queryFactory
                .select(account, likes)
                .from(account)
                .leftJoin(likes).on(account.id.eq(likes.account.id))
                .where(Objects.requireNonNull(userPasswordEq(dto.getPassword())).and(userEmailEq(dto.getEmail())))
                .fetch();

        List<Likes> likesList = result.stream()
                .map(r -> r.get(likes))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        Account accountInfo = result.stream()
                .map(r -> r.get(account))
                .findFirst()
                .get();

        return AccountLoginResponseDto.of(likesList,accountInfo);

    }
    private BooleanExpression userPasswordEq(String password) {
        return hasText(password) ? account.password.eq(password) : null;
    }

    private BooleanExpression userEmailEq(String userEmail) {
        return hasText(userEmail) ? account.email.eq(userEmail) : null;
    }

}

















