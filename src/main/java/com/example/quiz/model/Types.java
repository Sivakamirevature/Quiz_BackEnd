package com.example.quiz.model;




public class Types {
	
	private int id;

	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Types() {
		
	}
	@Override
	public String toString() {
		return "Types [id=" + id + ", name=" + name + "]";
	}
	
}
