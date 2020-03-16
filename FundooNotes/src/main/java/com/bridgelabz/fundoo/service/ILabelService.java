package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.LabelDTO;
import com.bridgelabz.fundoo.entity.LabelEntity;

public interface ILabelService {

	public LabelEntity createLabel(LabelDTO labelDTO, String token);
	public LabelEntity getLabelById(int labelId, String token);
	public LabelEntity getLabelByName(String labelName);
	public void updateLabel(LabelDTO labelDTO, int labelId, String token);
	public void deleteLabel(String labelName, String token);
	public void addLabeltoNote(int labelId, int noteId, String token);
	public void createLabelToNote(int noteId, String token, LabelDTO labelDTO);
	public void removeLabelFromNote(String labelName, int noteId, String token);
	
}
