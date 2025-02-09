package com.aisentiment.openai.dto;

public class SentimentResponse {
    private String sentimentType;
    private double confidenceScore;

    public SentimentResponse(String sentimentType, double confidenceScore) {
        this.sentimentType = sentimentType;
        this.confidenceScore = confidenceScore;
    }

    public String getSentimentType() {
        return sentimentType;
    }

    public double getConfidenceScore() {
        return confidenceScore;
    }
}
