package com.codingdojo.javaexam.models;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="courses")

public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 200, message= "Class name must be at least 3 characters.")
	private String className;
	
	@NotNull
	@Size(min = 3, max = 4, message = "Weekday must ba an abbrievated day name (i.e. Wed).")
	private String day;
	
	@NotNull
	@Min(value = 1, message = "You must charge for your yoga course.")
	private double price;
	
	@NotNull
	@Size(min = 10, max = 200, message = "Description must be at least 10 characters")
	private String description;
	

	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
				name="courses_students",
				joinColumns = @JoinColumn(name= "course_id"),
				inverseJoinColumns =  @JoinColumn(name= "student_id")
				)
	private List<Student> students;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;
	
	
	
	public Course() {

	}
	
	
	


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
	
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@PrePersist
	protected void onCreate()
	{
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate()
	{
		this.updatedAt = new Date();
	}

}
