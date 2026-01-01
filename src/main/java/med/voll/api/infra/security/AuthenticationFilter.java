package med.voll.api.infra.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voll.api.infra.security.token.TokenService;
import med.voll.api.infra.security.user.UsuarioLogado;
import med.voll.api.infra.security.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getToken(request);
            if (Objects.nonNull(token)) {
                var login = tokenService.getSubject(token);
                var usuarioLogado = (UsuarioLogado) usuarioService.loadUserByUsername(login);
                var authentication = new UsernamePasswordAuthenticationToken(usuarioLogado, null, usuarioLogado.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException ex) {
            throw new BadCredentialsException(ex.getMessage(), ex);
        }
    }

    private String getToken(HttpServletRequest request) {
        var bearerToken = request.getHeader("Authorization");
        if (Objects.nonNull(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.replace("Bearer ", "").trim();
        }
        return null;
    }
}
