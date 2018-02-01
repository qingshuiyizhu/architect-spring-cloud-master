package org.cloud.microservice.eager.entity;

public class ExamPaper {
	private Integer id;
	private String title;
	private String toptions;
	private Integer score;
  	private String answer;
    private Integer number;
	private String define;
    
    
	public Integer getId() {
		return id;
	}

	public String getDefine() {
		return define;
	}

	public void setDefine(String define) {
		this.define = define;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
 
	public String getToptions() {
		return toptions;
	}

	public void setToptions(String toptions) {
		this.toptions = toptions;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
