package com.m2i.poec.twittergreen.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Tweet {
	//TODO ajouter les noms de colonnes correspondentes
	@Id
	@Column(name="idtweet")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name="iduser")
	private Users author;

	@Column(name="content")
	private String content;

	@Column(name="date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	public Date getCreationDate() {
		return creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getAuthor() {
		return author;
	}

	public void setAuthor(Users author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Tweet [id=" + id + ", author=" + author + ", content=" + content + ", date_creation=" + creationDate
				+ "]";
	}
	
	
}
