package com.uniremington.estudiantes;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends CrudRepository<Estudiante, Long> {

    @Query(value = "SELECT * FROM Estudiante Order By Estudiante.promedio DESC LIMIT 5", nativeQuery = true)
    List<Estudiante> ordernarPorMayorPromedio ();
}
