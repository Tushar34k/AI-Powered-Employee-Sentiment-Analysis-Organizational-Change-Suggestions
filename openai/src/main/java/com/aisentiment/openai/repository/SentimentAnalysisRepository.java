package com.aisentiment.openai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aisentiment.openai.entity.SentimentAnalysis;

public interface SentimentAnalysisRepository extends JpaRepository<SentimentAnalysis,Long> {

}
