package com.m2i.poec.twittergreen.POJO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.m2i.poec.twittergreen.entity.Retweet;
import com.m2i.poec.twittergreen.entity.Tweet;

public class DisplayedTweet {
	
	private Long id;
	
	private String author;
	
	private Date createdOn;
	
	private String content;
	
	private String originalAuthor;

	public DisplayedTweet(Long id, String author, Date createdOn, String content, String originalAuthor) {
		this.id = id;
		this.author = author;
		this.createdOn = createdOn;
		this.content = content;
		this.originalAuthor = originalAuthor;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOriginalAuthor() {
		return originalAuthor;
	}

	public void setOriginalAuthor(String originalAuthor) {
		this.originalAuthor = originalAuthor;
	}
	
	public static List<DisplayedTweet> createList(List<Tweet> tweets, List<Retweet> retweets) {
		List<DisplayedTweet> displayedTweets = new ArrayList<DisplayedTweet>();
		
		for (Tweet tweet : tweets) {
			displayedTweets.add(new DisplayedTweet(tweet.getId(),
												   tweet.getAuthor().getUsername(),
												   tweet.getCreationDate(),
												   tweet.getContent(),
												   null));
		}
		
		for (Retweet retweet : retweets) {
			displayedTweets.add(new DisplayedTweet(retweet.getTweet().getId(),
												   retweet.getAuthor().getUsername(),
					   							   retweet.getCreationDate(),
					   							   retweet.getTweet().getContent(),
					   							   retweet.getTweet().getAuthor().getUsername()));
		}
		
		displayedTweets.sort(new DisplayedDateComparator());
		return displayedTweets;
	}
	
	static class DisplayedDateComparator implements Comparator<DisplayedTweet>{

		@Override
		public int compare(DisplayedTweet o1, DisplayedTweet o2) {
			return o2.createdOn.compareTo(o1.createdOn);
		}
		
	}
}
