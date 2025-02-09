package com.aisentiment.openai.controller;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import org.springframework.stereotype.Service;
import com.aisentiment.openai.dto.SentimentResponse;

import java.util.List;
import java.util.Properties;

@Service
public class SentimentClient {

    private final StanfordCoreNLP pipeline;

    public SentimentClient() {
        // Configure Stanford NLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,parse,sentiment");
        props.setProperty("pos.model", "models/english-left3words-distsim.tagger"); // Use relative path
        
        // Initialize the pipeline
        this.pipeline = new StanfordCoreNLP(props);
    }

    public SentimentResponse analyzeSentiment(String text) {
        CoreDocument doc = new CoreDocument(text);
        pipeline.annotate(doc);
        List<CoreSentence> sentences = doc.sentences();

        if (sentences.isEmpty()) {
            return new SentimentResponse("Unknown", 0.0);
        }

        String sentimentType = sentences.get(0).sentiment();
        double confidenceScore = getSentimentScore(sentimentType);

        return new SentimentResponse(sentimentType, confidenceScore);
    }

    private double getSentimentScore(String sentiment) {
        return switch (sentiment) {
            case "Very Negative" -> 0.1;
            case "Negative" -> 0.3;
            case "Neutral" -> 0.5;
            case "Positive" -> 0.7;
            case "Very Positive" -> 0.9;
            default -> 0.0;
        };
    }
}
