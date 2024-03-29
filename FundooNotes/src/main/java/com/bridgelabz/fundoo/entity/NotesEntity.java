package com.bridgelabz.fundoo.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Notes_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "note_id")
	private long noteId;
	private String title;
	private String description;
	private boolean isArchived;
	private boolean isPinned;
	private boolean isTrashed;
	private String color;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	private LocalDateTime remainderDate;
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.MERGE)
	@JoinTable(name = "Label_note", joinColumns = { @JoinColumn(name = "note_id") }, inverseJoinColumns = { @JoinColumn(name = "label_id") })
	private List<LabelEntity> label;
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "Collaborator_Note", joinColumns = { @JoinColumn(name = "note_id") }, inverseJoinColumns = { @JoinColumn(name = "user_id") })
	@JsonIgnore
	private List<UserEntity> collabUser;

}
