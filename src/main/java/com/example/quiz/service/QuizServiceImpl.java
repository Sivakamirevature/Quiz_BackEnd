package com.example.quiz.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.service.NullServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.quiz.dao.IQuizDao;
import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.exceptions.ServiceExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;


@Service
public class QuizServiceImpl implements IQuizService, Serializable{
	@Autowired
	IQuizDao quizdao;
	@Autowired
	Quiz q;

	public List<Quiz> getAllQuizzes() throws ServiceExceptions, DBExceptions {
		List<Quiz> QuizLists = null;
		try {
			QuizLists =  quizdao.getAllQuizzes();
		}
		catch(NullServiceException e) {
			throw new ServiceExceptions("unable to fetch");
		}
		catch(BadRequest e) {
			
		}
		return QuizLists;
	}

	@Override
	public List<Quiz> getQuizByID(int id) throws ServiceExceptions, DBExceptions {
		return quizdao.getQuizByID(id);
	}

	@Override
	public Quiz createQuiz(Quiz quiz) throws ServiceExceptions, DBExceptions {
		q = quizdao.createQuiz(quiz);
		return q;
	}

	@Override
	public int DeleteById(int qid) throws ServiceExceptions, DBExceptions {
		return quizdao.deleteById(qid);
	}

	@Override
	public Quiz UpdateById(Quiz quiz) throws ServiceExceptions, DBExceptions {
		return quizdao.updateById(quiz);
	}

	@Override
	public int activeDeactiveQuiz(int qid) throws ServiceExceptions, DBExceptions {
		return quizdao.activeDeactiveQuiz(qid);
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

	public List<Pool> getPool() throws ServiceExceptions, DBExceptions {
		return quizdao.getPool();
	}

	@Override
	public int deleteQuestion(int id) throws ServiceExceptions, DBExceptions {
		return quizdao.deleteQuestion(id);
	}

	@Override
	public List<Question> getQuestionsByQuizID(int id, String poolName) throws DBExceptions {
		return quizdao.getQuestionsByQuizID(id, poolName);
	}

	@Override
	public List<Question> getAllQuestions() throws DBExceptions {
		return quizdao.getAllQuestions();
	}
}