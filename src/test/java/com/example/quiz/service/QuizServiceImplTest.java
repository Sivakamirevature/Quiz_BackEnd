package com.example.quiz.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
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

@RunWith(MockitoJUnitRunner.class)
class QuizServiceImplTest {
	
	@InjectMocks
	QuizServiceImpl quizService;
	
	@Mock
	QuizDaoImpl quizDao;

	@Spy
	List<Level> levelList=new ArrayList<>();
	
	@Spy
	List<Category> categoryList = new ArrayList<>();
	
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
     	levelList = getLevelList();
     	categoryList = getCategoryList();
	}
	
	private List<Category> getCategoryList() {
		Category category = new Category();
		category.setCategoryId(58);
		category.setCategoryName("Java");
		categoryList.add(category);
		return categoryList;
	}

	public List<Level> getLevelList() {
		Level level = new Level();
		level.setLevelId(1);
		level.setLevelName("level 1");
		levelList.add(level);
		return levelList;
	}


	@Test
	void testGetAllQuizzes() {
		////fail("Not yet implemented");
	}

	@Test
	void testGetQuizByID() {
		////fail("Not yet implemented");
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
		////fail("Not yet implemented");
	}

	@Test
	void testGetCategory() {
		////fail("Not yet implemented");
	}

	@Test
	void testGetLevel() throws DBExceptions {
		////fail("Not yet implemented");
		when(quizDao.getLevel()).thenReturn(levelList);
		assertNotNull(levelList);
		assertEquals(quizService.getLevel(),  getLevelList());
		verify(quizDao, times(1)).getLevel();
	}
		
//	@Test(expected = ServiceExceptions.class)
//	void testFailureGetLevel() {
//		when(quizDao.getLevel()).thenReturn(null);
//		assertNotNull(levelList);
//		assertEquals(quizService.getLevel(),  getLevelList());
//	}
	

	@Test
	void testGetPool() {
		////fail("Not yet implemented");
	}

	@Test
	void testDeleteQuestion() {
		////fail("Not yet implemented");
	}

	@Test
	void testGetQuestionsByQuizID() {
		////fail("Not yet implemented");
	}

	@Test
	void testGetAllQuestions() {
		////fail("Not yet implemented");
	}

}
