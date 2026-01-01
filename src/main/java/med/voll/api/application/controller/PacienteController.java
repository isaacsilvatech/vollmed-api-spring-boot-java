package med.voll.api.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.application.dto.DetalhesPacienteDto;
import med.voll.api.application.dto.PacienteDto;
import med.voll.api.application.dto.ItemListaPacienteDto;
import med.voll.api.application.dto.AtualizarPacienteDto;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/paciente")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesPacienteDto> create(@RequestBody @Valid PacienteDto pacienteDto, UriComponentsBuilder uriComponentsBuilder) {
        var paciente = pacienteRepository.save(Paciente.criar(pacienteDto));
        var uri = uriComponentsBuilder.path("/paciente/{idConsulta}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesPacienteDto(paciente));
    }

    @GetMapping
    public ResponseEntity<PagedModel<ItemListaPacienteDto>> getList(Pageable pageable) {
        var pagedModel = new PagedModel<>(pacienteRepository.findAllByAtivoTrue(pageable).map(ItemListaPacienteDto::new));
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{idConsulta}")
    public ResponseEntity<DetalhesPacienteDto> getById(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesPacienteDto(paciente));
    }

    @PutMapping
    public ResponseEntity<DetalhesPacienteDto> update(@RequestBody @Valid AtualizarPacienteDto pacienteUpdateDto) {
        var paciente = pacienteRepository.getReferenceById(pacienteUpdateDto.id());
        paciente.atualizar(pacienteUpdateDto);
        return ResponseEntity.ok(new DetalhesPacienteDto(paciente));
    }

    @DeleteMapping("/{idConsulta}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var paciente = pacienteRepository.getReferenceById(id);
        paciente.inativar();
        return ResponseEntity.noContent().build();
    }
}
