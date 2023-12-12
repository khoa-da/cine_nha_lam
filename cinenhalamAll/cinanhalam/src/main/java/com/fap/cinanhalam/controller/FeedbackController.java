package com.fap.cinanhalam.controller;


import com.fap.cinanhalam.dto.FeedbackDTO;
import com.fap.cinanhalam.output.ListOutput;
import com.fap.cinanhalam.service.impl.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @GetMapping(value = "/feedback")
    public ListOutput showFeedback(){
        ListOutput result = new ListOutput();
        result.setListResult(feedbackService.findAllWithStatusIsTrue());
        return result;
    }

    @PostMapping(value= "/feedback")
    public FeedbackDTO createFeedback(@RequestBody FeedbackDTO model){
        return feedbackService.save(model);
    }

    @PutMapping(value="/staff/feedback/{id}")
    public FeedbackDTO updateFeedback(@RequestBody FeedbackDTO model, @PathVariable("id") long id ){
        model.setId(id);
        return feedbackService.save(model);
    }

    @DeleteMapping(value="/staff/feedback/{id}")
    public void changeFeedbackStatus(@PathVariable("id") Long id){
        feedbackService.changeStatus(id);
    }

}
