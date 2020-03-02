package com.bridgelabz.fundoo.dto;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class NotesDTO {

	private String title;
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
	private String description;
}
