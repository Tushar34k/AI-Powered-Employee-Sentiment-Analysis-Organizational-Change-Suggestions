package com.aisentiment.openai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    private FeedbackRepo feedbackRepository;
    private static final Logger logger = LoggerFactory.getLogger(SentimentService.class);

    private final SentimentClient sentimentClient;
    private final SentimentAnalysisRepository sentimentAnalysisRepository;

    public SentimentService(SentimentClient sentimentClient, SentimentAnalysisRepository sentimentAnalysisRepository) {
        this.sentimentClient = sentimentClient;
        this.sentimentAnalysisRepository = sentimentAnalysisRepository;
    }

    @Transactional
    public SentimentAnalysis analyzeFeedback(Long feedbackId) {
        try {
            // Fetch feedback from the database
            Optional<Feedback> optionalFeedback = feedbackRepository.findById(feedbackId);
            if (optionalFeedback.isEmpty()) {
                throw new RuntimeException("Feedback not found with ID: " + feedbackId);
            }
            Feedback feedback = optionalFeedback.get();

            // Create SentimentRequest
            SentimentRequest sentimentRequest = new SentimentRequest(feedback.getFeedbackText());

            // Call the Sentiment API
            SentimentResponse sentimentResponse = sentimentClient.analyzeSentiment(sentimentRequest.getText());

            // Save SentimentAnalysis
            SentimentAnalysis sentimentAnalysis = new SentimentAnalysis();
            sentimentAnalysis.setFeedback(feedback);
            sentimentAnalysis.setSentimentScore(sentimentResponse.getConfidenceScore());
            sentimentAnalysis.setSentimentType(sentimentResponse.getSentimentType());

            SentimentAnalysis savedAnalysis = sentimentAnalysisRepository.save(sentimentAnalysis);
            System.out.println("Sentiment analysis saved successfully for feedback ID: " + feedback.getId());

            return savedAnalysis;
        } catch (Exception e) {
            System.err.println("Error analyzing sentiment for feedback ID: " + feedbackId);
            throw new RuntimeException("Failed to analyze sentiment", e);
        }
    }
}
