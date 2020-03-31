package com.example.quiz.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.exceptions.ServiceExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.Quiz_Question;
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
	public List<Question> getAllQuestions() throws ServiceExceptions, DBExceptions {
		List<Question> searchList;
		Question[] questions = (Question[]) restTemplate.getForObject("http://localhost:9004/questions/activated", Question[].class);
		searchList= Arrays.asList(questions);
		
		for (Question ques : searchList) {
        System.out.println(ques);
       }
		System.out.println("Expected Answer: "+ questions);
		return searchList;		
	}
	
	@GetMapping(value = "/getAllQuizzes")
	public List<Quiz> getAllQuizzes() throws ServiceExceptions, DBExceptions{
		List<Quiz> quizList = null;
		try {
			quizList = quizservice.getAllQuizzes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quizList;
		
	}
	
	@GetMapping(value = "/getQuizByID/{id}")
	public List<Quiz> getQuizById(@PathVariable int id) throws ServiceExceptions, DBExceptions {
		System.out.print("from Controller");
		List<Quiz> quizzesList = quizservice.getQuizByID(id);
		return quizzesList;
	}

	@GetMapping(value = "/getQuestionsByQuizID/{id}/{poolName}")
	public List<Question> getQuestionsByQuizID(@PathVariable int id, @PathVariable String poolName){
		List<Question> questionList = null;
		try {
			questionList = quizservice.getQuestionsByQuizID(id, poolName);
		}
		catch(Exception e) {
		
		}
		return questionList;
	}
	@PostMapping(value = "/doCreateQuiz")
	public Quiz doCreateQuiz(@RequestBody Quiz quiz) throws ServiceExceptions, DBExceptions {
		quiz = quizservice.createQuiz(quiz);
		return quiz;
	}

	@PutMapping(value = "/doActiveDeactiveQuiz/{qid}")
	public int doDeactiveQuiz(@PathVariable int qid) throws ServiceExceptions, DBExceptions {
		int id = quizservice.activeDeactiveQuiz(qid);
		return id;
	}

	@DeleteMapping(value = "/doDeleteByID/{qid}")
	public int doDeleteByID(@PathVariable int qid) throws ServiceExceptions, DBExceptions {
		return quizservice.DeleteById(qid);
	}

	@PutMapping(value = "/doUpdateQuiz")
	public Quiz doUpdateQuiz(@RequestBody Quiz quiz) throws ServiceExceptions, DBExceptions {
		return quizservice.UpdateById(quiz);
	}

	@PostMapping(value = "/doClone")
	public Quiz doClone(@RequestBody Quiz quiz) throws ServiceExceptions, DBExceptions {
		return quizservice.cloneQuiz(quiz);
	}
	
	@GetMapping(value = "/getCategories")
	public List<Category> getCategory()throws ServiceExceptions, DBExceptions{
		return quizservice.getCategory();		
	}
	
	@GetMapping(value = "/getLevels")
	public List<Level> getLevel()throws ServiceExceptions, DBExceptions{
		return quizservice.getLevel();		
	}
	@GetMapping(value = "/getPools")
	public List<Pool> getPool()throws ServiceExceptions, DBExceptions{
		return quizservice.getPool();		
	}
	
	@DeleteMapping(value ="/deleteQuestion/{id}")
	public int deleteQuestion(@PathVariable int id) throws ServiceExceptions, DBExceptions {
		return quizservice.deleteQuestion(id);	
	}
}