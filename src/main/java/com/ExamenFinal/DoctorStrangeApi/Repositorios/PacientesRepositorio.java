package com.ExamenFinal.DoctorStrangeApi.Repositorios;

import com.ExamenFinal.DoctorStrangeApi.Entidades.Paciente;
import org.springframework.data.repository.CrudRepository;

public interface PacientesRepositorio extends CrudRepository<Paciente, Long> {
}
