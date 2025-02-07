package com.aisentiment.openai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aisentiment.openai.dto.FeedbackRequest;
import com.aisentiment.openai.entity.Feedback;
import com.aisentiment.openai.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
  

    @Autowired
    private FeedbackService feedbackService;



       @PostMapping("/save")
       public Feedback updatFeedback(@RequestBody FeedbackRequest fedbackRequest)
       {
                  return  feedbackService.createFeedback(fedbackRequest);  
       }

}
