package gob.regionancash.atencionciudadano.repository;

import gob.regionancash.atencionciudadano.model.Atencion;
import gob.regionancash.atencionciudadano.model.Dependencia;
import gob.regionancash.atencionciudadano.model.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    @Query("SELECT COUNT(a) FROM Atencion a WHERE a.dependencia=:dependencia and DATE(a.fecha) = DATE(:fecha)")
    abstract int getCountByDependenciaAndDate(Dependencia dependencia,Date fecha);

    @Query("SELECT a.fecha,a.horaIni FROM Atencion a WHERE a.dependencia=:dependencia and DATE(a.fecha) = DATE(:fecha)")
    abstract List getCountByDependenciaAndDateAndFechaIni(Dependencia dependencia,Date fecha);

    abstract List<Atencion> findByPersona(Persona persona);

    @Query("SELECT a FROM Atencion a WHERE a.persona.nroDocumento =:nroDocumento")
    abstract List findByPersonaNroDocumento(String nroDocumento);

    @Query("SELECT a FROM Atencion a WHERE a.dependencia.id =:id")
    abstract List findByDependenciaId(int id);

    @Query("SELECT a FROM Atencion a WHERE a.activo=:activo AND (:dependenciaId IS NULL OR a.dependencia.id =:dependenciaId)")
    Page findAllByDependencia(PageRequest pageable, Long dependenciaId, Integer activo);
}
