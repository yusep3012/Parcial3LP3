package com.uniremington.estudiantes;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public HttpStatus crearEstudiante(Estudiante estudiante) {

        Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(estudiante.getCedula());

        if (estudianteBuscado.isPresent())
            throw new EstudianteException(
                    HttpStatus.BAD_REQUEST, "El estudiante ya existe"
            );
            estudianteRepository.save(estudiante);
            return HttpStatus.CREATED;
    }

    public Iterable<Estudiante> obtenerEstudiantes(){
        return estudianteRepository.findAll();
    }

    public Estudiante obtenerEstudiante(Long cedula){

        Optional<Estudiante> estudianteObtenido = estudianteRepository.findById(cedula);

        if (estudianteObtenido.isPresent()){
            return estudianteObtenido.get();
        } else {
            throw new EstudianteException(
                    HttpStatus.NOT_FOUND, "El estudiante no existe"
            );
        }
    }

    public HttpStatus eliminarEstudiante(Long cedula){
        try {
            estudianteRepository.deleteById(cedula);
        } catch(EmptyResultDataAccessException ex){
            throw new EstudianteException(
                    HttpStatus.NOT_FOUND, "El estudiante a eliminar no existe"
            );
        }
            return HttpStatus.OK;
    }

    public HttpStatus actualizarEstudiante(Estudiante estudiante){

        Optional<Estudiante> estudianteBuscado = estudianteRepository.findById(estudiante.getCedula());

        if (estudianteBuscado.isPresent()){
            estudianteRepository.save(estudiante);
            return HttpStatus.ACCEPTED;
        }else{
            throw new EstudianteException(
                    HttpStatus.NOT_FOUND, "El estudiante no se encuentra"
            );
        }
    }

    public Iterable<Estudiante> mejoresEstudiantes() {

        return estudianteRepository.ordernarPorMayorPromedio();
    }
}