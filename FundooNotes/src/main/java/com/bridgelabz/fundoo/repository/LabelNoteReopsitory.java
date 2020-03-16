package com.bridgelabz.fundoo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.bridgelabz.fundoo.entity.NotesEntity;

@Repository
@Transactional
public interface LabelNoteReopsitory extends JpaRepository<NotesEntity, Integer>{

	@Modifying
	@Query(value = "insert into label_note(note_id,label_note_id) values(?,?)", nativeQuery = true)
	public void labelMapToNote(int noteId, int labelId);
}
