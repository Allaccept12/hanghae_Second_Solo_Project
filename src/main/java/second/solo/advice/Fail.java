package second.solo.advice;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class Fail {

    private String result = "fail";
    private String msg;

    @Builder
    public Fail(String msg) {
        this.msg = msg;
    }
}
