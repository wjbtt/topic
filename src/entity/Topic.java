package entity;

import java.sql.Timestamp;

public class Topic {
	/*
	 * 话题 tid(int), content(String), author(String), agree(int), disagree(int),
	 * orderIndex(int), publishTime(datetime), updateTime(datatime)
	 */

	private int tid;
	private String content;
	private String author;
	private int agree;
	private int disagree;
	private int orderIndex;
	private Timestamp publishTime;
	private Timestamp updateTime;

	public Topic() {
	}

	public Topic(int tid, String content, String author, int agree,
			int disagree, int orderIndex, Timestamp publishTime,
			Timestamp updateTime) {
		super();
		this.tid = tid;
		this.content = content;
		this.author = author;
		this.agree = agree;
		this.disagree = disagree;
		this.orderIndex = orderIndex;
		this.publishTime = publishTime;
		this.updateTime = updateTime;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getAgree() {
		return agree;
	}

	public void setAgree(int agree) {
		this.agree = agree;
	}

	public int getDisagree() {
		return disagree;
	}

	public void setDisagree(int disagree) {
		this.disagree = disagree;
	}

	public int getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}

	public Timestamp getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "Topic [tid=" + tid + ", content=" + content + ", author="
				+ author + ", agree=" + agree + ", disagree=" + disagree
				+ ", orderIndex=" + orderIndex + ", publishTime=" + publishTime
				+ ", updateTime=" + updateTime + "]";
	}


}
