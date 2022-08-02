package com.api.alvaro.lembrina.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.alvaro.lembrina.models.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer>{
	
	Optional<UsuarioModel> findByEmail(String email);
}
