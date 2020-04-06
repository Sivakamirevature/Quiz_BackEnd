package com.example.quiz.model;
import java.sql.Time;

public class Question {
	private int id;

	private String title;

	private Category category;

	private String content;

	private Level level;

	private int skill_points;

	private int score;

	private String tags;

	private Time duration;

	private Types type;
	
	private String status;
	
	private String option;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel_Id(Level level) {
		this.level = level;
	}

	public int getSkill_points() {
		return skill_points;
	}

	public void setSkill_points(int skill_points) {
		this.skill_points = skill_points;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
	}

	public Types getType_Id() {
		return type;
	}

	public void setType_Id(Types type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}
	
	public Types getType() {
		return type;
	}

	public void setType(Types type) {
		this.type = type;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Question() {}
}
