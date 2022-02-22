package second.solo.jwt;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("result","fail");
            jsonObject.put("msg","It is not a normal token");
            PrintWriter out = response.getWriter();
            out.print(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // 유효한 자격증명을 제공하지 않고 접근하려 할때 401
        //response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"로그인을 하지 않았습니다");
    }
}