package com.AiPowerd.AiPowerd.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SentimentAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sentimentType; // Positive, Negative, Neutral
    private double sentimentScore; // AI Score

    @OneToOne
    @JoinColumn(name = "feedback_id")
    private Feedback feedback; // Sentiment analysis is generated for each feedback.

}
