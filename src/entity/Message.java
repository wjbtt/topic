package entity;

import java.sql.Timestamp;

public class Message {
	/*
	 * 留言 -- message mid(int), content(String), author(String), tid(int),
	 * rid(int), rtime(datetime)
	 */

	private int mid;
	private String content;
	private String author;
	private int tid;
	private int rid;
	private Timestamp rtime;
	private String rauthor;

	public Message() {
	}

	public Message(int mid, String content, String author, int tid, int rid,
			Timestamp rtime) {
		super();
		this.mid = mid;
		this.content = content;
		this.author = author;
		this.tid = tid;
		this.rid = rid;
		this.rtime = rtime;
	}

	public Message(String content, String author, int tid, int rid) {
		super();
		this.content = content;
		this.author = author;
		this.tid = tid;
		this.rid = rid;
	}

	
	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
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

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public Timestamp getRtime() {
		return rtime;
	}

	public void setRtime(Timestamp rtime) {
		this.rtime = rtime;
	}

	public String getRauthor() {
		return rauthor;
	}

	public void setRauthor(String rauthor) {
		this.rauthor = rauthor;
	}

	@Override
	public String toString() {
		return "Message [mid=" + mid + ", content=" + content + ", author="
				+ author + ", tid=" + tid + ", rid=" + rid + ", rtime=" + rtime
				+ ", rauthor=" + rauthor + "]";
	}

}
