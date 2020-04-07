package com.example.quiz.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.Quiz_Question;

class QuizDaoImplTest {
	
	@InjectMocks
	QuizDaoImpl quizDao;

	 @Mock
	 private SessionFactory sessionFactory;
	   
	 @Mock
	 private Session session;
	 
	 @Mock
	 Query<Level> mockLevelQuery;
	 
	 @Mock
	 Query<Category> mockCategoryQuery;
	 
	 @Mock
	 Query<Quiz> mockQuizQuery;
	 
	 @Mock
	 Transaction transaction;
	 
	
	List<Level> levelList = new ArrayList<Level>();

	
	List<Category> categoryList = new ArrayList<Category>();

	
	List<Quiz> quizList = new ArrayList<Quiz>();
	
	@Spy
	Quiz quiz = new Quiz();

	@Spy
	List<Quiz_Question> quizQuestionObjList = new ArrayList<>();

	@Spy
	Quiz_Question quizQuestionobj = new Quiz_Question();

	@Spy
	List<Question> questionList = new ArrayList<>();

	@Spy
	Question question = new Question();
	
	private Integer id = 1;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.beginTransaction()).thenReturn(transaction);	
		quizList = getQuizList();	
		//quizList = getQuiz();
		levelList = getLevelList();
	}
	

	@Test
	void testGetAllQuizzes() throws DBExceptions {
		doReturn(mockQuizQuery).when(session).createQuery(Mockito.anyString(),Mockito.any());
		when(mockQuizQuery.getResultList()).thenReturn(quizList);
		System.out.println("quiz list "+quizList);
//		assertNotNull(quizDao.getAllQuizzes());
//		assertEquals(quizDao.getAllQuizzes(),quizList);
	}


	public List<Quiz> getQuizList(){
		quiz.setQuiz_id(id);
		quiz.setQuiz_name("Test1");
		quiz.setTags("Test1, Test");
		quiz.setActivity_points((short) 10);
		Time time = Time.valueOf("00:30:00");
		quiz.setDuration(time);
		quiz.setMax_no_of_attempts((short) 3);
		quiz.setLevel_override(false);
		quiz.setSlug("Test1");
		quiz.setDescription("This is Testing Purpose");
		quiz.setMeta_keywords("Test1, Test");
		quiz.setMeta_description("This is Testing Purpose");
		quiz.setIcon("Images/Path1");
		quiz.setInstructions("No Negative Marks");
		quiz.setPass_percentage(85);
		quiz.setIs_available_pre_signup(false);
		quiz.setIs_available_via_slug(true);
		quiz.setIs_available_dashboard(true);
		quiz.setIs_timer_enabled(false);
		quiz.setIs_shuffle_questions(false);
		quiz.setIs_shuffle_answers(false);
		quiz.setIs_display_score(true);
		quiz.setIs_allow_attempt_review(true);
		quiz.setIs_show_whether_correct(false);
		quiz.setIs_show_correct_answers_passed(false);
		quiz.setIs_show_correct_answers_failed(false);
		quiz.setIs_show_answer_explanations(false);
		quiz.setIs_enable_save_resume(true);
		LocalDateTime created_on = LocalDateTime.now();
		quiz.setCreated_on(created_on);
		quiz.setCreated_by("Creator");
		LocalDateTime modified_on = LocalDateTime.now();
		quiz.setModified_on(modified_on);
		quiz.setModified_by("Modifier");
		quiz.setModified_count(1);
		Level level = new Level();
		level.setLevelId(1);
		level.setLevelName("Level 1");
		quiz.setLevel(level);
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("Java");
		quiz.setCategory(category);
		quiz.setStatus(true);
		quiz.setMode("Save");
		Quiz_Question quizQuestionobj = new Quiz_Question();
		quizQuestionobj.setId(1);
		quizQuestionobj.setQuestion_id(1);
		Pool pool = new Pool();
		pool.setId(1);
		pool.setPoolName("test1");
		quizQuestionObjList.add(quizQuestionobj);
		quiz.setQuizQuestionObj(quizQuestionObjList);
		quizList.add(quiz);
		return quizList;
	}
	
	private List<Quiz> getQuiz() {
		quiz.setQuiz_id(id);
		quiz.setQuiz_name("Test1");
		quiz.setTags("Test1, Test");
		quiz.setActivity_points((short) 10);
		Time time = Time.valueOf("00:30:00");
		quiz.setDuration(time);
		quiz.setMax_no_of_attempts((short) 3);
		quiz.setLevel_override(false);
		quiz.setSlug("Test1");
		quiz.setDescription("This is Testing Purpose");
		quiz.setMeta_keywords("Test1, Test");
		quiz.setMeta_description("This is Testing Purpose");
		quiz.setIcon("Images/Path1");
		quiz.setInstructions("No Negative Marks");
		quiz.setPass_percentage(85);
		quiz.setIs_available_pre_signup(false);
		quiz.setIs_available_via_slug(true);
		quiz.setIs_available_dashboard(true);
		quiz.setIs_timer_enabled(false);
		quiz.setIs_shuffle_questions(false);
		quiz.setIs_shuffle_answers(false);
		quiz.setIs_display_score(true);
		quiz.setIs_allow_attempt_review(true);
		quiz.setIs_show_whether_correct(false);
		quiz.setIs_show_correct_answers_passed(false);
		quiz.setIs_show_correct_answers_failed(false);
		quiz.setIs_show_answer_explanations(false);
		quiz.setIs_enable_save_resume(true);
		LocalDateTime created_on = LocalDateTime.now();
		quiz.setCreated_on(created_on);
		quiz.setCreated_by("Creator");
		LocalDateTime modified_on = LocalDateTime.now();
		quiz.setModified_on(modified_on);
		quiz.setModified_by("Modifier");
		quiz.setModified_count(1);
		Level level = new Level();
		level.setLevelId(1);
		level.setLevelName("Level 1");
		quiz.setLevel(level);
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("Java");
		quiz.setCategory(category);
		quiz.setStatus(true);
		quiz.setMode("Save");
		Quiz_Question quizQuestionobj = new Quiz_Question();
		quizQuestionobj.setId(1);
		quizQuestionobj.setQuestion_id(1);
		Pool pool = new Pool();
		pool.setId(1);
		pool.setPoolName("test1");
		quizQuestionObjList.add(quizQuestionobj);
		quiz.setQuizQuestionObj(quizQuestionObjList);
		quizList.add(quiz);
		return quizList;
	}

	@Test
	void testGetQuizByID() {
		//fail("Not yet implemented");
	}

	@Test
	void testCreateQuiz() {
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteById() {
		//fail("Not yet implemented");
	}

	@Test
	void testUpdateById() {
		//fail("Not yet implemented");
	}

	@Test
	void testActiveDeactiveQuiz() {
		//fail("Not yet implemented");
	}

	@Test
	void testCloneQuiz() {
		//fail("Not yet implemented");
	}

	@Test
	void testGetCategory() {
		//fail("Not yet implemented");
	}

	@Test
	void testGetLevel() throws DBExceptions {
		doReturn(mockLevelQuery).when(session).createQuery(Mockito.anyString(),Mockito.any());
		when(mockLevelQuery.getResultList()).thenReturn(levelList);
		//assertNotNull(quizDao.getLevel());
		//assertEquals(quizDao.getLevel(),levelList);
	}
	public List<Level> getLevelList() {
		Level level = new Level();
		level.setLevelId(1);
		level.setLevelName("level 1");
		levelList.add(level);
		return levelList;
	}
	

	@Test
	void testGetPool() {
		//fail("Not yet implemented");
	}

	@Test
	void testDeleteQuestion() {
		//fail("Not yet implemented");
	}

	@Test
	void testGetQuestionsByQuizID() {
		//fail("Not yet implemented");
	}

	@Test
	void testGetAllQuestions() {
		//fail("Not yet implemented");
	}
}