package second.solo.advice;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Fail> handle(ApiRequestException ex) {
        Fail apiException = Fail.builder()
                .msg(ex.getMessage())
                .build();
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Fail> defaultException(Exception ex) {
        log.info(ex.getMessage());
        Fail apiException = Fail.builder()
                .msg("알 수 없는 오류 입니다. 관리자에게 문의 바랍니다.")
                .build();
        return new  ResponseEntity<>(apiException,HttpStatus.BAD_REQUEST);
    }
}
