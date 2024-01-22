package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tbl_Book")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends BaseEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int no;
	
	@Column(length=100, nullable=false)
	String title;
	
	@Column(length=100, nullable=false)
	String writer;
	
	@Column(length=100, nullable=false)
	String company;
	
	@Column(length=500, nullable=false)
	String summary;
	
	@Column(nullable=false)
	int price;
	
	
}
