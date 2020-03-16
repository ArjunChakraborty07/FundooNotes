package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.LabelDTO;
import com.bridgelabz.fundoo.response.UserResponse;
import com.bridgelabz.fundoo.service.ILabelService;

@RestController
@RequestMapping("/label")
public class LabelController {

	@Autowired
	private ILabelService labelServiceImpl;
	
	@PostMapping("/createLabel/{token}")
	public ResponseEntity<UserResponse> createLabel(@RequestBody LabelDTO labelDTO, @PathVariable("token") String token)
	{
		labelServiceImpl.createLabel(labelDTO, token);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(token, "Label Creation Successful...",null));
	}
	
	@PutMapping("/addLabelToNote/{token}")
	public ResponseEntity<UserResponse> addLabeltoNote(@RequestParam("labelId") int labelId,@RequestParam("noteId") int noteId,@PathVariable String token)
	{
		labelServiceImpl.addLabeltoNote(labelId, noteId, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(token, "Label Added to Note Successfully...",null));

	}
	
	@PostMapping("/createLabelToNote/{token}")
	public ResponseEntity<UserResponse> createLabelToNote(@RequestBody LabelDTO labelDTO,@RequestParam("noteId") int noteId,@PathVariable("token") String token)
	{
		labelServiceImpl.createLabelToNote(noteId, token, labelDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(token, "Label Creation Successful...",null));

	}
	
	@DeleteMapping("/removeLabelFromNote/{token}")
	public ResponseEntity<UserResponse> removeLabelFromNote(@RequestParam("labelName")String labelName,@RequestParam("noteId") int noteId,@PathVariable("token") String token)
	{
		labelServiceImpl.removeLabelFromNote(labelName, noteId, token);
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(token, "Label Removed Successfully...",null));

	}
	
	@PutMapping("/update/{token}")
	public ResponseEntity<UserResponse> update(@RequestBody LabelDTO labelDTO,@RequestParam("labelId") int labelId,@PathVariable("token") String token)
	{
		labelServiceImpl.updateLabel(labelDTO, labelId, token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new UserResponse(token, "Label Updated Successfully...",null));

	}
	
	@DeleteMapping("/delete/{token}")
	public ResponseEntity<UserResponse> delete(@RequestParam("labelName")String labelName,@PathVariable("token") String token)
	{
		labelServiceImpl.deleteLabel(labelName, token);
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(token, "Label Deleted Successfully...",null));

	}
	
}
