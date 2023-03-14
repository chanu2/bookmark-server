package uttug.bookmarkserver.global.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 헤더에서 JWT 를 받아옵니다.
        String accessToken = jwtUtil.resolveAccessToken(request);
        String refreshToken = jwtUtil.resolveRefreshToken(request);

        if (accessToken != null) {
            // 액세스 유효
            if (jwtUtil.validateToken(accessToken)) {
                this.setAuthentication(accessToken);
                //request.setAttribute("userUid", jwtUtil.getEmail(accessToken));
            }

            // 액세스 만료 && 리프레시 공백
            else if (!jwtUtil.validateToken(accessToken) && refreshToken == null){
                request.setAttribute("exception", "access token end" );
            }

            // 액세스 만료 && 리프레시 존재
            else if (!jwtUtil.validateToken(accessToken) && refreshToken != null) {
                boolean validateRefreshToken = jwtUtil.validateToken(refreshToken);
                boolean isRefreshToken = jwtUtil.existsRefreshToken(refreshToken);

                // 리프레시 유효
                if (validateRefreshToken && isRefreshToken) {

                    String email = jwtUtil.getEmail(refreshToken);
                    List roles = jwtUtil.getRoles(refreshToken);

                    String newAccessToken = jwtUtil.createAccessToken(email, roles);
                    jwtUtil.setHeaderAccessToken(response, newAccessToken);

                    this.setAuthentication(newAccessToken);

                }
                else {
                    request.setAttribute("exception", "refresh token not available" );
                }
            }
            else {
                request.setAttribute("exception", "refresh token not available" );
            }
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String token) {
        Authentication authentication = jwtUtil.getAuthentication(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}