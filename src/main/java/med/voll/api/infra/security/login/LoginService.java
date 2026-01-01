package med.voll.api.infra.security.login;

import lombok.RequiredArgsConstructor;
import med.voll.api.infra.security.token.TokenService;
import med.voll.api.infra.security.user.UsuarioLogado;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public String checkLogin(String login, String password) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(login, password);
        var authentication = authenticationManager.authenticate(authenticationToken);
        return tokenService.genarateAccessToken(((UsuarioLogado) authentication.getPrincipal()).getUsuario());
    }
}
