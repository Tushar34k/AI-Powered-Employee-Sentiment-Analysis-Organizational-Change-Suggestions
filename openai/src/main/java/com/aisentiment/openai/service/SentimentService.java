package com.aisentiment.openai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aisentiment.openai.controller.SentimentClient;
import com.aisentiment.openai.dto.SentimentRequest;
import com.aisentiment.openai.dto.SentimentResponse;
import com.aisentiment.openai.entity.Employee;
import com.aisentiment.openai.entity.Feedback;
import com.aisentiment.openai.entity.SentimentAnalysis;
import com.aisentiment.openai.exception.NotFoundException;
import com.aisentiment.openai.repository.EmployeeRepo;
import com.aisentiment.openai.repository.FeedbackRepo;
import com.aisentiment.openai.repository.SentimentAnalysisRepository;

@Service
public class SentimentService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private FeedbackRepo feedbackRepo;
    private static final Logger logger = LoggerFactory.getLogger(SentimentService.class);

    private final SentimentClient sentimentClient;
    private final SentimentAnalysisRepository sentimentAnalysisRepository;

    public SentimentService(SentimentClient sentimentClient, SentimentAnalysisRepository sentimentAnalysisRepository) {
        this.sentimentClient = sentimentClient;
        this.sentimentAnalysisRepository = sentimentAnalysisRepository;
    }

    @Transactional
    public SentimentAnalysis analyzeFeedback(Feedback feedback) {
        try {

            SentimentRequest sentimentRequest = new SentimentRequest(feedback.getFeedbackText());

            SentimentResponse sentimentResponse = sentimentClient.analyzeSentiment(sentimentRequest.getText());

            SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
            sentimentAnalysis.setFeedback(feedback);
            sentimentAnalysis.setSentimentScore(sentimentResponse.getConfidenceScore());
            sentimentAnalysis.setSentimentType(sentimentResponse.getSentimentType());

            SentimentAnalysis savedAnalysis = sentimentAnalysisRepository.save(sentimentAnalysis);
            logger.info("Sentiment analysis saved successfully for feedback ID: {}", feedback.getId());
            return savedAnalysis;
        } catch (Exception e) {
            logger.error("Error analyzing sentiment for feedback ID: {}", feedback.getId(), e);
            throw new RuntimeException("Failed to analyze sentiment", e);
        }
    }
}
