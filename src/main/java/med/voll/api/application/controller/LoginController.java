package med.voll.api.application.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.infra.security.token.TokenDto;
import med.voll.api.infra.security.login.LoginDto;
import med.voll.api.infra.security.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<TokenDto> checkLogin(@RequestBody @Valid LoginDto login) {
        return ResponseEntity.ok(new TokenDto(loginService.checkLogin(login.login(), login.senha())));
    }
}