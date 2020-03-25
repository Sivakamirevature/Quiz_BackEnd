package com.example.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="levels")
public class Level {
	@Id
	@Column(name="id")
	int levelId;
	@Column(name="level_name")
	String levelName;
	@OneToOne()
	@JoinColumn(name="prerequisite_level_id", referencedColumnName = "id")
	private Level level;
	
	public int getLevelId() {
		return levelId;
	}
	public void setLevelId(int leveId) {
		this.levelId = leveId;
	}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public Level(){
	
	}
}