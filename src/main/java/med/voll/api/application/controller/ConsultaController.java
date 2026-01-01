package med.voll.api.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.application.usecase.CancelarConsultaUseCase;
import med.voll.api.application.dto.CancelarConsultaDto;
import med.voll.api.application.dto.DatalhesConsultaDto;
import med.voll.api.application.dto.AgendarConsultaDto;
import med.voll.api.application.usecase.AgendarConsultaUseCase;
import med.voll.api.domain.consulta.ConsultaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/consulta")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaRepository consultaRepository;

    private final AgendarConsultaUseCase agendarConsultaUseCase;
    private final CancelarConsultaUseCase cancelarConsultaUseCase;

    @GetMapping("/{idConsulta}")
    public ResponseEntity<DatalhesConsultaDto> detalhes(@PathVariable Long id) {
        var consulta = consultaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatalhesConsultaDto(consulta));
    }

    @PostMapping("/agendamento")
    public ResponseEntity<DatalhesConsultaDto> agendar(@RequestBody @Valid AgendarConsultaDto agendarConsultaDto, UriComponentsBuilder uriComponentsBuilder) {
        var consulta = agendarConsultaUseCase.agendar(agendarConsultaDto);
        var uri = uriComponentsBuilder.path("/consulta/{idConsulta}").buildAndExpand(consulta.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatalhesConsultaDto(consulta));
    }

    @PostMapping("/cancelamento")
    public ResponseEntity<DatalhesConsultaDto> cancelar(@RequestBody @Valid CancelarConsultaDto cancelarConsultaDto) {
        var consulta = cancelarConsultaUseCase.cancelar(cancelarConsultaDto);
        return ResponseEntity.ok(new DatalhesConsultaDto(consulta));
    }
}
