package com.example.quiz.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.quiz.dao.QuizDaoImpl;
import com.example.quiz.exceptions.DBExceptions;
import com.example.quiz.exceptions.ServiceExceptions;
import com.example.quiz.model.Category;
import com.example.quiz.model.Level;
import com.example.quiz.model.Pool;
import com.example.quiz.model.Question;
import com.example.quiz.model.Quiz;
import com.example.quiz.model.Quiz_Question;
import com.example.quiz.model.Types;

@RunWith(MockitoJUnitRunner.class)
class QuizServiceImplTest {

	@InjectMocks
	QuizServiceImpl quizService;

	@Mock
	QuizDaoImpl quizDao;

	@Spy
	List<Level> levelList = new ArrayList<>();

	@Spy
	List<Category> categoryList = new ArrayList<>();

	@Spy
	List<Pool> poolList = new ArrayList<>();

	@Spy
	List<Quiz> quizList = new ArrayList<>();

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

	@Captor
	private ArgumentCaptor<Quiz> quizArg;

	@Captor
	private ArgumentCaptor<Integer> arg;

	private int id = 1;
	private String poolName = "java";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		levelList = getLevelList();
		categoryList = getCategoryList();
		quizList = getAllQuizzes();
		poolList = getPoolList();
		questionList = getQuestionList();
	}

	@Test
	void testGetAllQuizzes() throws DBExceptions {
		when(quizDao.getAllQuizzes()).thenReturn(quizList);
		assertNotNull(quizList);
		assertEquals(quizService.getAllQuizzes(), getAllQuizzes());
		verify(quizDao, times(1)).getAllQuizzes();
	}

	private List<Quiz> getAllQuizzes() {
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
	void testGetQuizByID() throws DBExceptions {
		when(quizDao.getQuizByID(id)).thenReturn(quizList);
		assertNotNull(quizList);
		assertEquals(quizService.getQuizByID(id), getQuizById());
		verify(quizDao, times(1)).getQuizByID(id);
	}

	private List<Quiz> getQuizById() {
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
	void testCreateQuiz() throws ServiceExceptions, DBExceptions {
		quizService.createQuiz(quiz);
		verify(quizDao, times(1)).createQuiz(quizArg.capture());
		assertNotNull(quizArg);
	}

	@Test
	void testDeleteById() throws DBExceptions {
		when(quizDao.getQuizByID(id)).thenReturn(quizList);
		assertNotNull(quizList);
		quizService.DeleteById(id);
		verify(quizDao, times(1)).deleteById(arg.capture());
		assertEquals(id, arg.getValue());
	}

	@Test
	void testUpdateById() throws ServiceExceptions, DBExceptions {
		quizService.UpdateById(quiz);
		verify(quizDao, times(1)).updateById(quizArg.capture());
		assertNotNull(quizArg);
	}

	@Test
	void testActiveDeactiveQuiz() throws DBExceptions {
		when(quizDao.getQuizByID(id)).thenReturn(quizList);
		assertNotNull(quizList);
		quizService.activeDeactiveQuiz(id);
		verify(quizDao, times(1)).activeDeactiveQuiz(arg.capture());
		assertEquals(id, arg.getValue());
	}

	@Test
	void testCloneQuiz() throws ServiceExceptions, DBExceptions {
		quizService.cloneQuiz(quiz);
		verify(quizDao, times(1)).cloneQuiz(quizArg.capture());
		assertNotNull(quizArg);
	}

	@Test
	void testGetCategory() throws DBExceptions {
		when(quizDao.getCategory()).thenReturn(categoryList);
		assertNotNull(categoryList);
		assertEquals(quizService.getCategory(), getCategoryList());
		verify(quizDao, times(1)).getCategory();
	}

	private List<Category> getCategoryList() {
		Category category = new Category();
		category.setCategoryId(58);
		category.setCategoryName("Java");
		categoryList.add(category);
		return categoryList;
	}

	@Test
	void testGetLevel() throws DBExceptions {
		when(quizDao.getLevel()).thenReturn(levelList);
		assertNotNull(levelList);
		assertEquals(quizService.getLevel(), getLevelList());
		verify(quizDao, times(1)).getLevel();
	}

	public List<Level> getLevelList() {
		Level level = new Level();
		level.setLevelId(1);
		level.setLevelName("level 1");
		levelList.add(level);
		return levelList;
	}

	@Test
	void testGetPool() throws DBExceptions {
		when(quizDao.getPool()).thenReturn(poolList);
		assertNotNull(poolList);
		assertEquals(quizService.getPool(), getPoolList());
		verify(quizDao, times(1)).getPool();
	}

	private List<Pool> getPoolList() {
		Pool pool = new Pool();
		pool.setId(id);
		pool.setPoolName("Java");
		poolList.add(pool);
		return poolList;
	}

	@Test
	void testGetQuestionsByQuizID() throws DBExceptions {
		when(quizDao.getQuestionsByQuizID(id, poolName)).thenReturn(questionList);
		assertNotNull(questionList);
		assertEquals(quizService.getQuestionsByQuizID(id, poolName), getQuestionList());
		verify(quizDao, times(1)).getQuestionsByQuizID(id, poolName);
	}

	@Test
	void testGetAllQuestions() throws DBExceptions {
		when(quizDao.getAllQuestions()).thenReturn(questionList);
		assertNotNull(questionList);
		assertEquals(quizService.getAllQuestions(), getQuestionList());
		verify(quizDao, times(1)).getAllQuestions();
	}

	private List<Question> getQuestionList() {
		Category category = new Category();
		category.setCategoryId(1);
		category.setCategoryName("java");
		question.setCategory(category);
		Level level = new Level();
		level.setLevelId(1);
		level.setLevelName("Java");
		question.setLevel(level);
		question.setId(1);
		question.setTitle("Question1");
		question.setContent("What is Java?");
		question.setSkill_points(1);
		question.setScore(1);
		question.setTags("Question1, Question2");
		Time time = Time.valueOf("00:30:00");
		question.setDuration(time);
		Types type = new Types();
		type.setId(1);
		type.setName("Java");
		question.setType(type);
		question.setStatus("Active");
		question.setOption("Option A, Option B, Option c, Option D");
		questionList.add(question);
		return questionList;
	}
}
