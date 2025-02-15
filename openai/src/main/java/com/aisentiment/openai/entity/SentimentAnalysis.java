package com.aisentiment.openai.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class SentimentAnalysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sentimentType; // Positive, Negative, Neutral
    private double sentimentScore; // AI Score

     @ManyToOne(cascade = CascadeType.PERSIST)  // Ensure Feedback gets saved automatically
    @JoinColumn(name = "feedback_id", nullable = false)
    private Feedback feedback;


}
