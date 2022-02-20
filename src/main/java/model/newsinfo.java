package model;

import model.thenewsdb.Article;
//� ����� newsinfo ������������ �� ��������� ��� �������, ������ ��� �����,��� ��������� ����.
// ���������� ��� ���������� ��� �����������
public class newsinfo {
private String title;
private String description;
private String post_date;
private String url_location;

//���������� constractor
public newsinfo(String title, String description, String post_date, String url_location) {
	super();
	this.title = title;
	this.description = description;
	this.post_date = post_date;
	this.url_location = url_location;

}
//���������� �� ����������� ��� 4 ����������� ��� ��������������
public newsinfo(Article theResult) {
	this.title = theResult.getTitle();
	this.description = theResult.getDescription();
	this.post_date = theResult.getPublishedAt();
	this.url_location = theResult.getUrl();
}

//���������� getters ��� setters
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	description = description;
}
public String getPost_date() {
	return post_date;
}
public void setPost_date(String post_date) {
	post_date = post_date;
}
public String getUrl_location() {
	return url_location;
}
public void setUrl_location(String url_location) {
	url_location = url_location;
}

@Override
	public String toString() {
	 return "newsinfo{" +
             "title='" + title + "'\n" +
             ", description='" + description + "'\n" +
             ", post_date='" + post_date + "'\n" +
             ", url_location='" + url_location + "'\n" +
             '}';
 }
	}


