package com.demo.oragejobsite.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.oragejobsite.dao.QuizQuestionRepository;
import com.demo.oragejobsite.entity.QuizQuestion;

@RestController
@CrossOrigin(origins="http://159.203.168.51")
public class QuizController {
//    private final QuizQuestionService quizQuestionService;
    @Autowired
    private QuizQuestionRepository questionRepository;
//    @Autowired
//    public QuizController(QuizQuestionService quizQuestionService) {
//        this.quizQuestionService = quizQuestionService;
//    }

//    @GetMapping("/questions")
//    public ResponseEntity<List<QuizQuestion>> getAllQuizQuestions() {
//        List<QuizQuestion> questions = quizQuestionService.getAllQuizQuestions();
//        return ResponseEntity.ok(questions);
//    }
    
	@CrossOrigin(origins="http://159.203.168.51")
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody QuizQuestion question) {
        try {
            questionRepository.save(question);
            return ResponseEntity.ok("Question added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add question");
        }
    }
	
	@CrossOrigin(origins="http://159.203.168.51")
	@GetMapping("/fetchquestion")
	public List<QuizQuestion> fetchquestion(){
		return questionRepository.findAll();
	}
	
	@CrossOrigin(origins = "http://159.203.168.51")
	@PostMapping("/deletequestion")
	public void deleteQuestionById(@RequestBody Map<String, Long> request) {
	    Long id = request.get("id");
	    if (id != null) {
	        questionRepository.deleteById(id);
	    }
	}
	
//    @PostMapping("/calculate-marks")
//    public ResponseEntity<QuizResult> calculateMarks(@RequestBody List<UserAnswer> userAnswers) {
//        // Implement logic to calculate marks based on user answers
//        double totalMarks = calculateTotalMarks(userAnswers);
//        
//        // Create a QuizResult object to hold the result
//        QuizResult quizResult = new QuizResult(totalMarks);
//        
//        // You can save the user's answers and marks to the database here if needed
//        
//        return ResponseEntity.ok(quizResult);
//    }
//
//    // Implement a method to calculate marks based on user answers
//    private double calculateTotalMarks(List<UserAnswer> userAnswers) {
//        // Implement your logic to calculate marks here
//        // You can compare userAnswers with the correct answers from the database
//        // and calculate the total marks
//        // Example: Loop through userAnswers and compare with correct answers
//        // Calculate total marks based on the correctness of answers
//        return 0.0; // Replace with your actual calculation
//    }	
}


