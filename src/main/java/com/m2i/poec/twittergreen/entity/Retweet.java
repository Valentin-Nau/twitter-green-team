package com.m2i.poec.twittergreen.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Retweet {
	
	@Id
	@Column(name="idretweet")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="idtweet")
	private Tweet tweet;

	@ManyToOne
	@JoinColumn(name="iduser")
	private User author;

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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
	
	public Tweet getTweet() {
		return tweet;
	}

	public void setTweet(Tweet tweet) {
		this.tweet = tweet;
	}

	@Override
	public String toString() {
		return "ReTweet [id=" + id + ", tweet=" + tweet + ", author=" + author + ", creationDate=" + creationDate + "]";
	}
	
	
	
}
