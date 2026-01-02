package med.voll.api.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.application.usecase.CancelarConsultaUseCase;
import med.voll.api.application.dto.CancelarConsultaDto;
import med.voll.api.application.dto.DetalhesConsultaDto;
import med.voll.api.application.dto.AgendarConsultaDto;
import med.voll.api.application.usecase.AgendarConsultaUseCase;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaRepository consultaRepository;

    private final AgendarConsultaUseCase agendarConsultaUseCase;
    private final CancelarConsultaUseCase cancelarConsultaUseCase;

    @GetMapping("/{idConsulta}")
    public ResponseEntity<DetalhesConsultaDto> detalhes(@PathVariable Long id) {
        var consulta = consultaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesConsultaDto(consulta));
    }

    @PostMapping("/agendamento")
    public ResponseEntity<DetalhesConsultaDto> agendar(@RequestBody @Valid AgendarConsultaDto agendarConsultaDto, UriComponentsBuilder uriComponentsBuilder) {
        var detalhes = agendarConsultaUseCase.agendar(agendarConsultaDto);
        var uri = uriComponentsBuilder.path("/consulta/{idConsulta}").buildAndExpand(detalhes.id()).toUri();
        return ResponseEntity.created(uri).body(detalhes);
    }

    @PostMapping("/cancelamento")
    public ResponseEntity<DetalhesConsultaDto> cancelar(@RequestBody @Valid CancelarConsultaDto cancelarConsultaDto) {
        var detalhes = cancelarConsultaUseCase.cancelar(cancelarConsultaDto);
        return ResponseEntity.ok(detalhes);
    }
}
