package com.aisentiment.openai.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.aisentiment.openai.entity.Feedback;

public interface FeedbackRepo  extends JpaRepository<Feedback, Long> {

}
