package dto;

import java.io.Serializable;

public class Log implements Serializable {

	private static final long serialVersionUID = 7171767918205614257L;
	private int id;
	private String content;
	private String dateTime;

	public Log() {

	}

	public Log(int id, String content, String dateTime) {
		super();
		this.id = id;
		this.content = content;
		this.dateTime = dateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "Log [id=" + id + ", content=" + content + ", dateTime=" + dateTime + "]";
	}

}
