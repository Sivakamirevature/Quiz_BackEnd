package com.example.quiz.dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.DataException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.RestTemplate;

import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.Quiz_Question;

@Repository
public class QuizDaoImpl implements IQuizDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	RestTemplate restTemplate;

	Transaction transaction;

	@Override
	public List<Quiz> getAllQuizzes() throws DBExceptions {
		DBExceptions dbExceptions = new DBExceptions();
		List<Quiz> quizzes = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Quiz> query = session.createQuery("from Quiz");
			quizzes = ((org.hibernate.query.Query) query).list();
			if (quizzes.isEmpty()) {
				dbExceptions.IDNotFound("Given Id is not found");
			}
		} 
		catch(DataAccessException e) {
			throw new DBExceptions();
		}
		finally {
			session.close();
		}
		return quizzes;
	}

	@Override
	public List<Quiz> getQuizByID(int id) throws DBExceptions {
		DBExceptions dbExceptions = new DBExceptions();
		List<Quiz> quizzesList = null;
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			String queryString = "From Quiz where quiz_id=:id";
			TypedQuery<Quiz> query = (TypedQuery<Quiz>) session.createQuery(queryString);
			query.setParameter("id", id);
			quizzesList = ((org.hibernate.query.Query) query).list();
			if (quizzesList.isEmpty()) {
				dbExceptions.IDNotFound("Given Id is not found");
			}
		}
		catch (NullPointerException e) {
			throw new DBExceptions("Id not found", e);
		}
		catch (DataAccessException e) {
			throw new DBExceptions("Unable to fetch the Data", e);
		}
		catch (Exception e) {
			throw new DBExceptions("Exceptions", e);
		}
		finally {
			session.close();
		}
		return quizzesList;
	}

	@Override
	public Quiz createQuiz(Quiz quiz) throws DBExceptions{
		Session session = null;
		try {
		session = sessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		LocalDateTime today = LocalDateTime.now();
		quiz.setSlug("https://qa.revature.com/Revature Pro/quiz/" + quiz.getSlug());
		quiz.setCreated_on(today);
		quiz.setCreated_by("Sivakami");
		quiz.setModified_on(today);
		quiz.setModified_by("Sivakami");
		quiz.setModified_by("Sivakami");
		quiz.getQuizQuestionObj().forEach(quizObj -> quizObj.setQuiz(quiz));
		session.save(quiz);
		transaction.commit();
		}
		catch(DataException e) {
			throw new DBExceptions("Mismatched types or incorrect cardinality");
		}
		catch(SQLGrammarException e) {
			throw new DBExceptions("SQL syntax error, invalid object references,");
		}
		catch(HibernateException e) {
		        throw new DBExceptions("Object can not persist",e);
		}
		catch(Throwable e) {
			if(e.getCause() instanceof ConstraintViolationException) 
			{
     		throw new DBExceptions("Quiz Name and Slug Should not be Repeated");
			}
			if(e instanceof BadRequest) {
				throw new DBExceptions("Request body missing some data");
			}
			if(e instanceof Exception) {
				throw new DBExceptions("Unable to insert the Record");
			}
		}
		finally {
		session.close();
		}
		return quiz;
	}

	@Override
	public int DeleteAllQuizzes() {
		Session session = null;
		DBExceptions dbExceptions = new DBExceptions();
		int id = 0;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			org.hibernate.query.Query query = session.createQuery("truncate table Quiz");
			id = query.executeUpdate();
		} catch (NullPointerException e) {
			dbExceptions.IDNotFound("Nothing will deleted");
		}
		transaction.commit();
		session.close();
		return id;
	}

	@Override
	public int deleteById(int qid) {
		Session session = sessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		session.delete(session.get(Quiz.class, qid));
		transaction.commit();
		session.close();
		return qid;
	}

	@Override
	public Quiz updateById(Quiz quiz) throws DBExceptions {
		Session session = null;
		int id = 0;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			id = quiz.getQuiz_id();
			System.out.println("id Count1" + id);
			int modified_count = ((Integer) session
					.createSQLQuery("SELECT modified_count from quiz_settings where quiz_id=" + id + " LIMIT 1")
					.uniqueResult()).intValue();
			String created_by = ((String) session
					.createSQLQuery("select created_by from quiz_settings where quiz_id= " + id + " Limit 1")
					.uniqueResult().toString());
			LocalDateTime today = LocalDateTime.now();
			quiz.setModified_on(today);
			quiz.setCreated_on(today);
			quiz.setModified_count(++(modified_count));
			quiz.setCreated_by(created_by);
			quiz.getQuizQuestionObj().forEach(quizObj -> quizObj.setQuiz(quiz));
			session.update(quiz);
			transaction.commit();
		} catch (NullPointerException e) {
			throw new DBExceptions("Cannot Find the id", e);
		} 
		catch(DataException e) {
			throw new DBExceptions("Mismatched types or incorrect cardinality");
		}
		catch(SQLGrammarException e) {
			throw new DBExceptions("SQL syntax error, invalid object references,");
		}
		catch(HibernateException e) {
		     throw new DBExceptions("Constraint Violation Exceptions",e);
		}
		catch(Exception e) {
			throw new DBExceptions("Unable to update",e);
		}
		finally {
		session.close();
		}
		return quiz;
	}

	@Override
	public int activeDeactiveQuiz(int qid) throws DBExceptions {
		Session session = null;
		int id;
		String hql;
		byte status;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			status = ((Byte) session
					.createSQLQuery("SELECT status from quiz_settings where quiz_id=" + qid + " LIMIT 1")
					.uniqueResult()).byteValue();
			if (status == 1) {
				hql = "Update Quiz set status = 0 where id = :id";
			} else {
				hql = "Update Quiz set status = 1 where id = :id";
			}
			org.hibernate.query.Query q = session.createQuery(hql).setParameter("id", qid);
			id = q.executeUpdate();
			transaction.commit();
		} catch (NullPointerException e) {
			throw new DBExceptions("Cannot Find the id", e);
		} 
		catch(DataException e) {
			throw new DBExceptions("Mismatched types or incorrect cardinality");
		}
		catch(SQLGrammarException e) {
			throw new DBExceptions("SQL syntax error, invalid object references,");
		}
		catch(HibernateException e) {
		     throw new DBExceptions("Constraint Violation Exceptions",e);
		}
		catch(Exception e) {
			throw new DBExceptions("Unable to update",e);
		}
		finally {
		session.close();
		}
		return id;
	}

	@Override
	public Quiz cloneQuiz(Quiz quiz) throws DBExceptions {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			transaction = session.beginTransaction();
			quiz.setQuiz_id(null);
			quiz.setCreated_by("Sivakami");
			quiz.setSlug("https://qa.revature.com/Revature Pro/quiz/" + quiz.getSlug() + "-copy");
			quiz.getQuizQuestionObj().forEach(quizObj -> quizObj.setQuiz(quiz));
			session.save(quiz);
			transaction.commit();
		} catch (NullPointerException e) {
			throw new DBExceptions("Due to id mismatch cant do the clone operation", e);
		} 
		catch(DataException e) {
			throw new DBExceptions("Mismatched types or incorrect cardinality");
		}
		catch(HibernateException e) {
		     throw new DBExceptions("Constraint Violation Exceptions",e);
		}
		catch(Exception e) {
			throw new DBExceptions("Unable to update",e);
		}
		finally {
		session.close();
		}
		return quiz;
	}

	@Override
	public List<Category> getCategory() throws DBExceptions {
		Session session = null;
		List<Category> categoryList = null;
		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Quiz> query = session.createQuery("from Category");
			categoryList = ((org.hibernate.query.Query) query).list();
		} catch (NullPointerException e) {
			throw new DBExceptions("Due to id mismatch cant do the clone operation", e);
		} finally {
			session.close();
		}
		return categoryList;
	}

	@Override
	public List<Level> getLevel() throws DBExceptions {
		Session session = null;
		List<Level> levelList = null;
		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Quiz> query = session.createQuery("from Level");
			levelList = ((org.hibernate.query.Query) query).list();
		} catch (NullPointerException e) {
			throw new DBExceptions("Due to id mismatch cant do the clone operation", e);
		} catch(DataException e) {
			throw new DBExceptions("Mismatched types or incorrect cardinality");
		}
		catch(SQLGrammarException e) {
			throw new DBExceptions("SQL syntax error, invalid object references,");
		}
		catch(HibernateException e) {
		     throw new DBExceptions("Constraint Violation Exceptions",e);
		}
		catch(Exception e) {
			throw new DBExceptions("Unable to update",e);
		}
		finally {
		session.close();
		}
		return levelList;
	}

	@Override
	public List<Pool> getPool() throws DBExceptions {
		Session session = null;
		List<Pool> poolList = null;
		try {
			session = sessionFactory.getCurrentSession();
			TypedQuery<Quiz> query = session.createQuery("from Pool");
			poolList = ((org.hibernate.query.Query) query).list();
		} catch (NullPointerException e) {
			throw new DBExceptions("Due to id mismatch cant do the clone operation", e);
		} catch(DataException e) {
			throw new DBExceptions("Mismatched types or incorrect cardinality");
		}
		catch(SQLGrammarException e) {
			throw new DBExceptions("SQL syntax error, invalid object references,");
		}
		catch(HibernateException e) {
		     throw new DBExceptions("Persistence Related Issue",e);
		}
		catch(Exception e) {
			throw new DBExceptions("Unable to update",e);
		}
		finally {
		session.close();
		}
		return poolList;
	}

	@Override
	public int deleteQuestion(int id) throws DBExceptions {
		Session session = sessionFactory.getCurrentSession();
		transaction = session.beginTransaction();
		String hql = "DELETE FROM Quiz_Question WHERE id=:id";
		Query query = session.createQuery(hql);
		query.setParameter("id",id);
		int rowCount = query.executeUpdate();
		transaction.commit();
		session.close();
		return id;
	}

	@Override
	public List<Question> getQuestionsByQuizID(int id, String poolName) throws DBExceptions {
		List<Quiz_Question> quizQuestionList = null;
		List<Integer> questionIds = new ArrayList<Integer>();
		List<Question> questionList = null;
		Session session = null;
		String SQuestionIds = "";
		try {
			session = sessionFactory.getCurrentSession();
			quizQuestionList = session.createQuery(
					"from Quiz_Question where quiz_id = " + id
							+ " and pool_id = (select id from Pool where poolName =  '" + poolName + "')",
					Quiz_Question.class).getResultList();
			if (quizQuestionList.isEmpty()) {
				System.out.println("quizzes list is: " + quizQuestionList);
			} else {
				int length = quizQuestionList.size();
				System.out.println("Quiz Question List: " + length);
				for (int i = 0; i < length; i++) {
					System.out.println("id : " + quizQuestionList.get(i).getQuestion_id());
					int value = quizQuestionList.get(i).getQuestion_id();
					System.out.println("value: " + value);
					questionIds.add(value);
				}
				SQuestionIds += questionIds.get(0);
				for (int i = 1; i < questionIds.size(); i++) {
					SQuestionIds += ",";
					SQuestionIds += questionIds.get(i);
				}
			}
			Question[] questions = (Question[]) restTemplate
					.getForObject("http://localhost:9004/questions/getQuestions/" + SQuestionIds, Question[].class);
			questionList = Arrays.asList(questions);
			System.out.println("Question List: " + questionList);
		} 
		catch(DataException e) {
			throw new DBExceptions("Mismatched types or incorrect cardinality");
		}
		catch(SQLGrammarException e) {
			throw new DBExceptions("SQL syntax error, invalid object references,");
		}
		catch(HibernateException e) {
		     throw new DBExceptions("Constraint Violation Exceptions",e);
		}
		catch(Exception e) {
			throw new DBExceptions("Unable to update",e);
		}
		finally {
		session.close();
		}
		return questionList;
	}

	@Override
	public List<Question> getAllQuestions() throws DBExceptions {
		List<Question> questionList;
		DBExceptions dbExceptions = null;
		try {	
		Question[] questions = (Question[]) restTemplate.getForObject("http://localhost:9004/questions/activated",
				Question[].class);
		questionList = Arrays.asList(questions);
		if (questionList.isEmpty()) {
			dbExceptions.IDNotFound("Given Id is not found");
		}
	} 
	catch(DataAccessException e) {
		throw new DBExceptions();
	}
		return questionList;
	}
}