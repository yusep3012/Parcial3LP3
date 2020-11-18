package com.uniremington.estudiantes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EstudianteController {

    private EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping("/estudiante")
    public ResponseEntity<Void> crearEstudiante(@RequestBody Estudiante estudiante) {
        return ResponseEntity.status(estudianteService.crearEstudiante(estudiante))
                .build();
    }

    @GetMapping("/estudiantes")
    public ResponseEntity<Iterable<Estudiante>> estudiantes() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        estudianteService.obtenerEstudiantes()
                );
    }

    @GetMapping("/estudiante/{cedula}")
    public ResponseEntity<Estudiante> obtenerEstudiante (
            @PathVariable("cedula") Long cedula
    ) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(
                        estudianteService.obtenerEstudiante(cedula)
                );
    }

    @GetMapping("/estudiantes/mejores")
    public ResponseEntity<Iterable<Estudiante>> mejoresEstudiantes() {
        return ResponseEntity
                .status(
                        HttpStatus.OK
                ).body(
                        estudianteService.mejoresEstudiantes()
                );
    }

    @DeleteMapping("/estudiante/{cedula}")
    public ResponseEntity<Map<String, String>> eliminarEstudiante(
            @PathVariable("cedula") Long cedula
    ) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "El estudiante ha sido eliminado");

        return ResponseEntity
                .status(
                        estudianteService.eliminarEstudiante(cedula)
                )
                .body(map);
    }

    @PutMapping("/estudiante")
    public ResponseEntity<Map<String, String>> actualizarEstudiante(@RequestBody Estudiante estudiante) {
        Map<String, String> map = new HashMap<>();
        map.put("message", "El estudiante ha sido actualizado");

        return ResponseEntity
                .status(
                        estudianteService.actualizarEstudiante(estudiante)
                )
                .body(map);
    }
}
