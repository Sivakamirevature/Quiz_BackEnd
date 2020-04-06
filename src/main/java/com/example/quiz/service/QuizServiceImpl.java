package com.example.quiz.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.service.NullServiceException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.example.quiz.dao.IQuizDao;
import com.example.quiz.exceptions.BadRequestException;
import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.exceptions.ServiceExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;

@Service
public class QuizServiceImpl implements IQuizService, Serializable {
	private static final long serialversionUID = 129348938L;
	@Autowired
	IQuizDao quizdao;
	@Autowired
	Quiz q;

	public List<Quiz> getAllQuizzes() throws ServiceExceptions, DBExceptions {
		List<Quiz> QuizLists = null;
		try {
			QuizLists = quizdao.getAllQuizzes();
			return QuizLists;
		} catch (NullServiceException e) {
			throw new ServiceExceptions("unable to fetch list of Quizzes");
		}
	}

	@Override
	public List<Quiz> getQuizByID(int id) throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.getQuizByID(id);
		} catch (NullServiceException e) {
			throw new ServiceExceptions("unable to fetch the Quiz");
		}
	}

	@Override
	public Quiz createQuiz(Quiz quiz) throws ServiceExceptions, DBExceptions {
		try {
			q = quizdao.createQuiz(quiz);
			return q;
		} catch (NullServiceException e) {
			throw new ServiceException("Service not Available");
		} catch (BadRequest e) {
			throw new BadRequestException("Bad Request");
		}
	}

	@Override
	public int DeleteById(int qid) throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.deleteById(qid);
		} catch (NullServiceException e) {
			throw new ServiceException("Service not Available");
		} catch (Exception e) {
			throw new ServiceException("sevice Exception");
		}
	}

	@Override
	public Quiz UpdateById(Quiz quiz) throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.updateById(quiz);
		} catch (NullServiceException e) {
			throw new ServiceException("Service not Available");
		}
	}

	@Override
	public int activeDeactiveQuiz(int qid) throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.activeDeactiveQuiz(qid);
		} catch (NullServiceException e) {
			throw new ServiceException("Service not Available");
		}
	}

	@Override
	public Quiz cloneQuiz(Quiz quiz) throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.cloneQuiz(quiz);
		} catch (NullServiceException e) {
			throw new ServiceException("Service not Available");
		}
	}

	@Override
	public List<Category> getCategory() throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.getCategory();
		} catch (NullServiceException e) {
			throw new ServiceException("Service not Available");
		}
	}

	@Override
	public List<Level> getLevel() throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.getLevel();
		} catch (NullServiceException e) {
			throw new ServiceExceptions("Service Exception");
		}
	}

	public List<Pool> getPool() throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.getPool();
		} catch (NullServiceException e) {
			throw new ServiceExceptions("Service Exception");
		}
	}

	@Override
	public int deleteQuestion(int id) throws ServiceExceptions, DBExceptions {
		try {
			return quizdao.deleteQuestion(id);
		} catch (NullServiceException e) {
			throw new ServiceExceptions("Service Exception");
		}
	}

	@Override
	public List<Question> getQuestionsByQuizID(int id, String poolName) throws DBExceptions {
		try {
			return quizdao.getQuestionsByQuizID(id, poolName);
		} catch (NullServiceException e) {
			throw new ServiceExceptions("Service Exception");
		}
	}

	@Override
	public List<Question> getAllQuestions() throws DBExceptions {
		try {
			return quizdao.getAllQuestions();
		} catch (NullServiceException e) {
			throw new ServiceExceptions("Service Exception");
		}
	}
}