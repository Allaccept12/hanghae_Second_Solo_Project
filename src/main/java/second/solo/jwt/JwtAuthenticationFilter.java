package second.solo.jwt;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import second.solo.advice.ApiRequestException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends GenericFilterBean {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT 를 받아옵니다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        if (token != null){
            token = token.substring(7);
            if (jwtTokenProvider.validateToken(token)) {
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                // SecurityContext 에 Authentication 객체를 저장합니다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            chain.doFilter(request, response);
        } else if (((HttpServletRequest) request).getMethod().equals("GET") ||
                (((HttpServletRequest) request).getMethod().equals("POST") &&
                        (((HttpServletRequest) request).getRequestURI().equals("/api/login")) ||
                        (((HttpServletRequest) request).getRequestURI().equals("/api/register")))) {
            chain.doFilter(request, response);
        } else {
            JSONObject jsonObject = new JSONObject(); // 중괄호에 들어갈 속성 정의 { "a" : "1", "b" : "2" }
            try {
                jsonObject.put("result", "fail");
                jsonObject.put("msg", "you are not logged in.");
                PrintWriter out = response.getWriter();
                out.print(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}