package med.voll.api.application.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import med.voll.api.application.dto.DetalhesMedicoDto;
import med.voll.api.application.dto.CriarMedicoDto;
import med.voll.api.application.dto.ItemListaMedicoDto;
import med.voll.api.application.dto.AtualizarMedicoDto;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medico")
@SecurityRequirement(name = "bearer-key")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalhesMedicoDto> create(@RequestBody @Valid CriarMedicoDto criarMedicoDto, UriComponentsBuilder uriComponentsBuilder) {
        var medico = medicoRepository.save(Medico.criar(criarMedicoDto));
        var uri = uriComponentsBuilder.path("/medico/{idConsulta}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetalhesMedicoDto(medico));
    }

    @GetMapping
    public ResponseEntity<PagedModel<ItemListaMedicoDto>> getList(Pageable pageable) {
        var pagedModel = new PagedModel<>(medicoRepository.findAllByAtivoTrue(pageable).map(ItemListaMedicoDto::new));
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{idConsulta}")
    public ResponseEntity<DetalhesMedicoDto> getById(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetalhesMedicoDto(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetalhesMedicoDto> update(@RequestBody @Valid AtualizarMedicoDto medicoUpdateDto) {
        var medico = medicoRepository.getReferenceById(medicoUpdateDto.id());
        medico.atualizar(medicoUpdateDto);
        return ResponseEntity.ok(new DetalhesMedicoDto(medico));
    }

    @DeleteMapping("/{idConsulta}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();
        return ResponseEntity.noContent().build();
    }
}
