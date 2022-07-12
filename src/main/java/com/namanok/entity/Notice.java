package com.namanok.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name="\"NOTICE\"")
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
	
	@Column(name = "TITLE", nullable = false, length = 200)
	private String title;
	
	@Column(name = "contents")
	@Lob
	private String contents;
	
	@CreationTimestamp
	private Timestamp regDate;
	
	@JoinColumn(name = "userId")
	@ManyToOne
	private User user;
}
