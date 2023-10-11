package com.facu.restfake.repositories;

import com.facu.restfake.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PersonaRepository extends BaseRepository<Persona, Long>{
    boolean existsByDni(int dni);

    @Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%",
    nativeQuery = true)
    List<Persona> search(@Param("filtro") String filtro);

    @Query(value = "SELECT * FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%",
            countQuery = "SELECT count(*) FROM persona",
            nativeQuery = true)
    Page<Persona> search(@Param("filtro") String filtro, Pageable pageable);
}
