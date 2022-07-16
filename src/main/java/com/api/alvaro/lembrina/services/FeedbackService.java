package com.api.alvaro.lembrina.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.alvaro.lembrina.dtos.FeedbackDto;
import com.api.alvaro.lembrina.models.FeedbackModel;
import com.api.alvaro.lembrina.repositories.FeedbackRepository;

@Service
public class FeedbackService {
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Transactional
	public FeedbackModel save(FeedbackModel feedbackModel) {
		return feedbackRepository.save(feedbackModel);
	}
	
	public Page<FeedbackModel> findAll(Pageable pageable) {
		return feedbackRepository.findAll(pageable);
	}

	public Optional<FeedbackModel> findById(Integer id) {
		return feedbackRepository.findById(id);
	}

	@Transactional
	public void delete(FeedbackModel feedbackModel) {
		feedbackRepository.delete(feedbackModel);
	}
	
	public FeedbackModel dtoToModel(FeedbackDto feedbackDto) {
		var feedbackModel = new FeedbackModel();
		feedbackModel.setDescricao(feedbackDto.getDescricao());
		return feedbackModel;
	}
	
}
