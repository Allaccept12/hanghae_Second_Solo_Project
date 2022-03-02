package second.solo.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import second.solo.util.AccountValidator;
import second.solo.advice.ApiRequestException;
import second.solo.domain.Account;
import second.solo.dto.request.AccountRegisterRequestDto;
import second.solo.dto.request.AccountLoginRequestDto;
import second.solo.dto.response.AccountLoginResponseDto;
import second.solo.jwt.JwtTokenProvider;
import second.solo.repository.account.AccountRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long registerAccount(AccountRegisterRequestDto dto) {
        Account account = accountRepository.findByEmail(dto.getAccount_email()).orElse(null);
        AccountValidator.registerValidation(dto,account);
        String password = passwordEncoder.encode(dto.getPassword());
        dto.encodedPassword(password);
        Account save = accountRepository.save(AccountRegisterRequestDto.toEntity(dto));
        return save.getId();
    }

    @Transactional
    @Override
    public AccountLoginResponseDto login(AccountLoginRequestDto requestDto) {
        Account account = accountRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new ApiRequestException("가입되지 않은 유저입니다."));
        AccountValidator.loginValidation(passwordEncoder,requestDto.getPassword(),account.getPassword());
        String token = jwtTokenProvider.createToken(Long.toString(account.getId()), account.getEmail(), account.getUsername());
        AccountLoginResponseDto dto = accountRepository.login(requestDto);
        dto.tokenSet(token);

        return dto;
    }
}
