package com.aisentiment.openai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aisentiment.openai.dto.FeedbackRequest;
import com.aisentiment.openai.entity.Employee;
import com.aisentiment.openai.entity.Feedback;
import com.aisentiment.openai.exception.NotFoundException;
import com.aisentiment.openai.repository.EmployeeRepo;
import com.aisentiment.openai.repository.FeedbackRepo;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    

    public Feedback createFeedback(FeedbackRequest fedbackRequest) {
        Employee emp = employeeRepo.findById(fedbackRequest.getEmployeeId())
                .orElseThrow(() -> new NotFoundException("employee not found!!"));

        Feedback fd = new Feedback();
        fd.setEmployee(emp);
        fd.setFeedbackText(fedbackRequest.getText());
                    feedbackRepo.save(fd);
        return fd;
    }

}
