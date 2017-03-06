package com.dgq.domain;

public class Joke {

	private String author;

	private String authorIcon;

	private String title;

	private String imgUrl;

	private String content;

	private int upCount;

	private int downCount;

	private String jumpUrl;

	public Joke() {
	}

	public Joke(String author, String authorIcon, String title, String imgUrl,
			String content, int upCount, int downCount, String jumpUrl) {
		this.author = author;
		this.authorIcon = authorIcon;
		this.title = title;
		this.imgUrl = imgUrl;
		this.content = content;
		this.upCount = upCount;
		this.downCount = downCount;
		this.jumpUrl = jumpUrl;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getAuthorIcon() {
		return authorIcon;
	}

	public void setAuthorIcon(String authorIcon) {
		this.authorIcon = authorIcon;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUpCount() {
		return upCount;
	}

	public void setUpCount(int upCount) {
		this.upCount = upCount;
	}

	public int getDownCount() {
		return downCount;
	}

	public void setDownCount(int downCount) {
		this.downCount = downCount;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

}
