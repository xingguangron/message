package com.book.beans;

public class Book {
private String book_id;
private String book_name;
private String author;
private String publish;//出版社
private String content;//内容简介
private String images;//图书的图片
private String reason;//推荐理由
private String state;//状态：0：未审核,1:审核通过,2:未审核通过
public String getBook_id() {
	return book_id;
}
public void setBook_id(String book_id) {
	this.book_id = book_id;
}
public String getBook_name() {
	return book_name;
}
public void setBook_name(String book_name) {
	this.book_name = book_name;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getPublish() {
	return publish;
}
public void setPublish(String publish) {
	this.publish = publish;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getImages() {
	return images;
}
public void setImages(String images) {
	this.images = images;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
}
