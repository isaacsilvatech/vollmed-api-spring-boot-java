package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.dto.MedicoDetailsDto;
import med.voll.api.dto.MedicoDto;
import med.voll.api.dto.MedicoListDto;
import med.voll.api.dto.MedicoUpdateDto;
import med.voll.api.model.Medico;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medico")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoDetailsDto> create(@RequestBody @Valid MedicoDto medicoDto, UriComponentsBuilder uriComponentsBuilder) {
        var medico = medicoRepository.save(new Medico(medicoDto));
        var uri = uriComponentsBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new MedicoDetailsDto(medico));
    }

    @GetMapping
    public ResponseEntity<PagedModel<MedicoListDto>> getList(Pageable pageable) {
        var pagedModel = new PagedModel<>(medicoRepository.findAllByAtivoTrue(pageable).map(MedicoListDto::new));
        return ResponseEntity.ok(pagedModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDetailsDto> getById(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new MedicoDetailsDto(medico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicoDetailsDto> update(@RequestBody @Valid MedicoUpdateDto medicoUpdateDto) {
        var medico = medicoRepository.getReferenceById(medicoUpdateDto.id());
        medico.update(medicoUpdateDto);
        return ResponseEntity.ok(new MedicoDetailsDto(medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.inativar();
        return ResponseEntity.noContent().build();
    }
}
