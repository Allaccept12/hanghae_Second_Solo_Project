package second.solo.repository.account;


import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import second.solo.domain.Account;
import second.solo.domain.QAccount;
import second.solo.domain.QLikes;
import second.solo.dto.AccountRequestDto;
import second.solo.dto.AccountResponseDto;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.StringUtils.hasText;
import static second.solo.domain.QAccount.*;
import static second.solo.domain.QLikes.*;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<Account>> login(AccountRequestDto dto) {
        if (validator(dto))
            return Optional.empty();

        return Optional.ofNullable(queryFactory
                .select(account)
                .from(account)
                .leftJoin(account.likesList, likes).fetchJoin()
                .where(userEmailEq(dto.getEmail()),userPasswordEq(dto.getPassword()))
                .fetch());
    }

    private boolean validator(AccountRequestDto dto) {
        return dto.getEmail().isEmpty() || dto.getPassword().isEmpty();
    }

        private BooleanExpression userPasswordEq(String password) {
        return hasText(password) ? account.password.eq(password) : null;
    }

    private BooleanExpression userEmailEq(String userEmail) {
        return hasText(userEmail) ? account.email.eq(userEmail) : null;
    }

}

















