package com.aisentiment.openai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisentiment.openai.entity.Feedback;
import com.aisentiment.openai.entity.SentimentAnalysis;
import com.aisentiment.openai.service.SentimentService;

@RestController
@RequestMapping("/sentiment")
public class SentimentController {
    

    @Autowired
    private SentimentService sentimentService;

    @PostMapping("/save")
    public SentimentAnalysis createSentimentAnalysis(@RequestBody Feedback feedback)
    {
                   return  sentimentService.analyzeFeedback(feedback);
    }


}
