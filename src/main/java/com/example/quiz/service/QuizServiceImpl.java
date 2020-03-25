package com.example.quiz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.quiz.dao.IQuizDao;
import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.exceptions.ServiceExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.Quiz_Question;


@Service
public class QuizServiceImpl implements IQuizService {
	@Autowired
	IQuizDao quizdao;
	@Autowired
	Quiz q;
	public List<Quiz> getAllQuizzes() throws ServiceExceptions, DBExceptions{
		return quizdao.getAllQuizzes();
	}

	@Override
	public List<Quiz> getQuizByID(int id)throws ServiceExceptions, DBExceptions{
		System.out.print("from Controller");
		return quizdao.getQuizByID(id);
	}
	@Override
	public Quiz createQuiz(Quiz quiz) throws ServiceExceptions, DBExceptions{
		q = quizdao.createQuiz(quiz);  
		return q;
	}
	
	@Override
	public int DeleteById(int qid) throws ServiceExceptions, DBExceptions{
		return quizdao.deleteById(qid);	
	}

	@Override
	public Quiz UpdateById(Quiz quiz) throws ServiceExceptions, DBExceptions{
		return quizdao.updateById(quiz);
	}

	@Override
	public int activeDeactiveQuiz(int qid) throws ServiceExceptions, DBExceptions {
		return quizdao.activeDeactiveQuiz(qid);
	}

	@Override
	public List<Quiz_Question> getPoolQuestions(int qid, String poolname) throws DBExceptions {
		return quizdao.getPoolQuestions(qid, poolname);
	}

	@Override
	public Quiz cloneQuiz(Quiz quiz) throws ServiceExceptions, DBExceptions {
		return quizdao.cloneQuiz(quiz);
	}

	@Override
	public List<Category> getCategory() throws ServiceExceptions, DBExceptions {
		return quizdao.getCategory();
	}

	@Override
	public List<Level> getLevel() throws ServiceExceptions, DBExceptions {
		return quizdao.getLevel();
	}
	
	public List<Pool> getPool() throws ServiceExceptions, DBExceptions{
		return quizdao.getPool();
	}

	@Override
	public int deleteQuestion(int id) throws ServiceExceptions, DBExceptions {
		return quizdao.deleteQuestion(id);
	}
}