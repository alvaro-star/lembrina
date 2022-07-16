package com.api.alvaro.lembrina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.alvaro.lembrina.models.ArquivoModel;

@Repository
public interface ArquivoRepository extends JpaRepository<ArquivoModel, Integer>{

}
