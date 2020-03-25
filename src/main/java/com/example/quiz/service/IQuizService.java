package com.example.quiz.service;

import java.util.List;
import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.exceptions.ServiceExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.Quiz_Question;



public interface IQuizService {

	List<Quiz> getAllQuizzes() throws ServiceExceptions, DBExceptions;

	List<Quiz> getQuizByID(int id) throws ServiceExceptions, DBExceptions;

	Quiz createQuiz(Quiz quiz) throws ServiceExceptions, DBExceptions;

	int activeDeactiveQuiz(int qid) throws ServiceExceptions, DBExceptions;

	int DeleteById(int qid) throws ServiceExceptions, DBExceptions;

	Quiz UpdateById(Quiz quiz) throws ServiceExceptions, DBExceptions;

	List<Quiz_Question> getPoolQuestions(int qid, String poolname) throws ServiceExceptions, DBExceptions;

	Quiz cloneQuiz(Quiz quiz) throws ServiceExceptions, DBExceptions;

	List<Category> getCategory()throws ServiceExceptions, DBExceptions;

	List<Level> getLevel()throws ServiceExceptions, DBExceptions;

	List<Pool> getPool() throws ServiceExceptions, DBExceptions;

	int deleteQuestion(int id) throws ServiceExceptions, DBExceptions;
}