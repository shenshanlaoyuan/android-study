package com.itheima.news.domain;
/*
 * /*

<item>
  <title>军报评徐才厚</title> 
  <description>人死账不消 反腐步不停，支持，威武，顶，有希望了。</description>
  <image>http://192.168.1.100:8080/img/a.jpg</image>
  <type>1</type>
  <comment>163</comment>
</item>

面向对象的思想, 会将数据 封装到  javabean 中 --- newsItem

 * 						
 */
public class NewsItem {
	
	private String title;
	private String description;
	private String image;
	private String type;
	private String comment;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
