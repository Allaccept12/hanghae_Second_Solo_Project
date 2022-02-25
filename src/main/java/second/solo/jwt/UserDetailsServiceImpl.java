package second.solo.jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.repository.account.AccountRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        log.info(userPk);
        Account user = accountRepository.findById(Long.parseLong(userPk))
                .orElseThrow(() -> new ApiRequestException("존재하지 않거나 로그인 하지 않았습니다"));
        return new UserDetailsImpl(user);
    }
}