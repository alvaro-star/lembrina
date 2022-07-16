package com.api.alvaro.lembrina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.alvaro.lembrina.models.FeedbackModel;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModel, Integer>{

}
