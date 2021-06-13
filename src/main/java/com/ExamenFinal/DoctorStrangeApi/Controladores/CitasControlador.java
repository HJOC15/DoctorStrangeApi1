package com.ExamenFinal.DoctorStrangeApi.Controladores;


import com.ExamenFinal.DoctorStrangeApi.Entidades.Cita;
import com.ExamenFinal.DoctorStrangeApi.Repositorios.CitasRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cita")
public class CitasControlador {

    @Autowired
    CitasRepositorio citasRepositorio;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Cita NuevaCita(@RequestBody Cita cita){
        Cita nuevaCita = citasRepositorio.save(cita);
        return  nuevaCita;
    }

    @GetMapping(value = "/paciente")
    @ResponseStatus(code = HttpStatus.OK)
    public Collection<Cita> TodasCita(){
        Iterable<Cita> pacientes = citasRepositorio.findAll();
        return (Collection<Cita>) pacientes;

    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Cita buscarCita(@PathVariable(name = "id") Long id){
        Optional<Cita> cita = citasRepositorio.findById(id);
        Cita result = null;
        if(cita.isPresent()){
            result = cita.get();
        }
        return result;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Cita editarCita(@PathVariable(name = "id") Long id, @RequestBody Cita cita){
        Optional<Cita> citavieja = citasRepositorio.findById(id);
        if (citavieja.isPresent())
        {
            Cita acutal = citavieja.get();
            acutal.setId(id);
            acutal.setHora(cita.getHora());
            acutal.setPaciente(cita.getPaciente());
            acutal.setEstado(cita.getEstado());
            acutal.setObservaciones(cita.getObservaciones());
            Cita citanueva = citasRepositorio.save(acutal);
            return citanueva;
        }
        return null;
    }

    @PutMapping(value = "/{id}/pendiente")
    @Transactional
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Cita editarEstadoPendiente(@PathVariable(name = "id")Long id){
        Optional<Cita> anterior = citasRepositorio.findById(id);
        if (anterior.isPresent())
        {
            Cita nueva = anterior.get();
            nueva.setEstado("Pendiente");
            return citasRepositorio.save(nueva);
        }
        return null;
    }

    @PutMapping(value = "/{id}/aceptada")
    @Transactional
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Cita editarEstadoAceptada(@PathVariable(name = "id")Long id){
        Optional<Cita> anterior = citasRepositorio.findById(id);
        if (anterior.isPresent())
        {
            Cita nueva = anterior.get();
            nueva.setEstado("Aceptada");
            return citasRepositorio.save(nueva);
        }
        return null;
    }

    @PutMapping(value = "/{id}/cancelada")
    @Transactional
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Cita editarEstadoCancelada(@PathVariable(name = "id")Long id){
        Optional<Cita> anterior = citasRepositorio.findById(id);
        if (anterior.isPresent())
        {
            Cita nueva = anterior.get();
            nueva.setEstado("Cancelada");
            return citasRepositorio.save(nueva);
        }
        return null;
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void eliminarcita(@PathVariable(name = "id") Long id){
        citasRepositorio.deleteById(id);
    }

}
