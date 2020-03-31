package com.example.quiz.dao;

import java.util.List;
import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.Quiz_Question;

public interface IQuizDao {

	List<Quiz> getAllQuizzes()throws DBExceptions;

	List<Quiz> getQuizByID(int id)throws DBExceptions;

	Quiz createQuiz(Quiz quiz)throws DBExceptions;

	int deleteById(int qid)throws DBExceptions;

	Quiz updateById(Quiz quiz)throws DBExceptions;

	int activeDeactiveQuiz(int qid)throws DBExceptions;
	
	int DeleteAllQuizzes() throws DBExceptions;

	Quiz cloneQuiz(Quiz quiz)throws DBExceptions;

	List<Category> getCategory()throws DBExceptions;

	List<Level> getLevel()throws DBExceptions;

	List<Pool> getPool()throws DBExceptions;

	int deleteQuestion(int id) throws DBExceptions ;

	List<Question> getQuestionsByQuizID(int id, String poolName);
}