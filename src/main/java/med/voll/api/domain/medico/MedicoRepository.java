package med.voll.api.domain.medico;

import jakarta.persistence.TemporalType;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
        select 
            m 
        from 
            Medico m
            left join Consulta c ON c.medico = m 
                and c.dataDe < :dataAte 
                and c.dataAte > :dataDe
                and c.motivoCancelamento is null
        where
            m.ativo = true
            and m.especialidade = :especialidade
            and c.id is null
        order by rand()
        limit 1
            """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime dataDe, LocalDateTime dataAte);

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select
                m.ativo
            from
                Medico m
            where
                m.id = :id
            """)
    boolean findAtivoById(Long id);
}
