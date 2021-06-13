package com.ExamenFinal.DoctorStrangeApi.Controladores;

import com.ExamenFinal.DoctorStrangeApi.Entidades.Cita;
import com.ExamenFinal.DoctorStrangeApi.Entidades.Paciente;
import com.ExamenFinal.DoctorStrangeApi.Repositorios.PacientesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping(value = "/paciente")
public class PacientesControlador {
    @Autowired
    PacientesRepositorio pacientesRepositorio;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Paciente NuevoPaciente(@RequestBody Paciente paciente){
        Paciente nuevoPaciente = pacientesRepositorio.save(paciente);
        return  nuevoPaciente;
    }

    @GetMapping(value = "/paciente")
    @ResponseStatus(code = HttpStatus.OK)
    public Collection<Paciente> TodosPaciente(){
        Iterable<Paciente> pacientes = pacientesRepositorio.findAll();
        return (Collection<Paciente>) pacientes;

    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Paciente buscarPaciente(@PathVariable(name = "id") Long id){
        Optional<Paciente> paciente = pacientesRepositorio.findById(id);
        Paciente result = null;
        if(paciente.isPresent()){
            result = paciente.get();
        }
        return result;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Paciente editarPaciente(@PathVariable(name = "id") Long id, @RequestBody Paciente paciente){
        Optional<Paciente> pacienteviejo = pacientesRepositorio.findById(id);
        if (pacienteviejo.isPresent())
        {
            Paciente acutal = pacienteviejo.get();
            acutal.setId(id);
            acutal.setPrimernombre(paciente.getPrimernombre());
            acutal.setSegundonombre(paciente.getSegundonombre());
            acutal.setPrimerapellido(paciente.getPrimerapellido());
            acutal.setSegundoapellido(paciente.getSegundoapellido());
            acutal.setEdad(paciente.getEdad());
            Paciente pacientenuevo = pacientesRepositorio.save(acutal);
            return pacientenuevo;
        }
        return null;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void eliminarPaciente(@PathVariable(name = "id") Long id){
        pacientesRepositorio.deleteById(id);
    }


}
