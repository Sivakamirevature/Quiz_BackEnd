package com.example.quiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Category {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
		@Column(name="category_id")
		private int categoryId;
		
		@Column(name="category_name")
		private String categoryName;
		
		public int getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}	
		
		public Category() {}
}