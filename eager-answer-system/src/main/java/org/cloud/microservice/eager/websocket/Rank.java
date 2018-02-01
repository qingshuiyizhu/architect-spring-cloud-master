package org.cloud.microservice.eager.websocket;

public class Rank {
	private String name;
	private Integer score = 0;
	private Integer state = 1;
	
    public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Rank(String name) {
		super();
		this.name = name;
		 
	}

	 

}
