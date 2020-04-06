package com.example.quiz.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.exceptions.RecordNotFoundException;
import com.example.quiz.exceptions.ServiceExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.ResponseEntity;
import com.example.quiz.service.IQuizService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/Quizzes")
public class QuizController {
	
	@Autowired
	IQuizService quizservice;
	@Autowired
	RestTemplate restTemplate;

	@GetMapping(value = "/getAllQuestions")
	public  ResponseEntity getAllQuestions() throws ServiceExceptions, DBExceptions {
		List<Question> questionList = null;
			questionList = quizservice.getAllQuestions();	
		return new ResponseEntity(HttpStatus.OK.value(), "Data Fetched Successfully", questionList);
	}

	@GetMapping(value = "/getAllQuizzes")
	public ResponseEntity getAllQuizzes() throws ServiceExceptions, DBExceptions {
		List<Quiz> quizList = null;
			quizList = quizservice.getAllQuizzes();	
		return new ResponseEntity(HttpStatus.OK.value(), "Data Fetched Successfully", quizList);
	}

	@GetMapping(value = "/getQuizByID/{id}")
	public ResponseEntity getQuizById(@PathVariable int id) throws ServiceExceptions, DBExceptions {
		List<Quiz> quizzesList = null;
		quizzesList = quizservice.getQuizByID(id);
		System.out.println(quizzesList);
		if(quizzesList.isEmpty()) {
	         throw new RecordNotFoundException("Invalid employee id : " + id);
	    }
		return new ResponseEntity(HttpStatus.OK.value(), "Data Fetched Successfully", quizzesList);
	}

	@GetMapping(value = "/getQuestionsByQuizID/{id}/{poolName}")
	public ResponseEntity getQuestionsByQuizID(@PathVariable int id, @PathVariable String poolName) throws DBExceptions {
		List<Question> questionList = null;
		questionList = quizservice.getQuestionsByQuizID(id, poolName);
		return new ResponseEntity(HttpStatus.OK.value() ,"Data Inserted Successfully", questionList);	
	}

	@PostMapping(value = "/doCreateQuiz")
	public ResponseEntity doCreateQuiz(@RequestBody Quiz quiz) throws ServiceExceptions, DBExceptions {
		quiz = quizservice.createQuiz(quiz);
		return new ResponseEntity(HttpStatus.CREATED.value() ,"Data Inserted Successfully", quiz);
	}

	@PutMapping(value = "/doActiveDeactiveQuiz/{qid}")
	public ResponseEntity doDeactiveQuiz(@PathVariable int qid) throws ServiceExceptions, DBExceptions {
		int id = quizservice.activeDeactiveQuiz(qid);
		return new ResponseEntity(HttpStatus.OK.value() ,"Activation Mode Changed", id);
	}

	@DeleteMapping(value = "/doDeleteByID/{qid}")
	public ResponseEntity doDeleteByID(@PathVariable int qid) throws ServiceExceptions, DBExceptions {
		int id =  quizservice.DeleteById(qid);
		return new ResponseEntity(HttpStatus.OK.value() ,"Deletion Success", id);
	}

	@PutMapping(value = "/doUpdateQuiz")
	public ResponseEntity doUpdateQuiz(@RequestBody Quiz quiz) throws ServiceExceptions, DBExceptions {
		Quiz quiz1 = quizservice.UpdateById(quiz);
		return new ResponseEntity(HttpStatus.OK.value() ,"Updated Successfully", quiz);
	}

	@PostMapping(value = "/doClone")
	public ResponseEntity doClone(@RequestBody Quiz quiz) throws ServiceExceptions, DBExceptions {
		quiz = quizservice.createQuiz(quiz);
		return new ResponseEntity(HttpStatus.CREATED.value() ,"Data Inserted Successfully", quiz);
	}

	@GetMapping(value = "/getCategories")
	public ResponseEntity getCategory() throws ServiceExceptions, DBExceptions {
		List<Category> categories = quizservice.getCategory();
		return new ResponseEntity(HttpStatus.OK.value() ,"Data Fetched Successfully", categories);
	}

	@GetMapping(value = "/getLevels")
	public ResponseEntity getLevel() throws ServiceExceptions, DBExceptions {
		List<Level> levels = quizservice.getLevel();
		return new ResponseEntity(HttpStatus.OK.value() ,"Data fetched Successfully", levels);
	}

	@GetMapping(value = "/getPools")
	public ResponseEntity getPool() throws ServiceExceptions, DBExceptions {
		List<Pool> pools = quizservice.getPool();
		return new ResponseEntity(HttpStatus.OK.value() ,"Data fetched Successfully", pools);
	}

	@DeleteMapping(value = "/deleteQuestion/{id}")
	public ResponseEntity deleteQuestion(@PathVariable int id) throws ServiceExceptions, DBExceptions {
		id = quizservice.deleteQuestion(id);
		return new ResponseEntity(HttpStatus.OK.value() ,"Question Deleted Successfully", id);
	}
}