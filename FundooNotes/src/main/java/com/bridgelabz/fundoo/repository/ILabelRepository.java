package com.bridgelabz.fundoo.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.fundoo.entity.LabelEntity;

public interface ILabelRepository extends JpaRepository<LabelEntity, Integer>{

	@Query(value = "select * from label_details where label_id=? and user_id=?", nativeQuery = true)
	Optional<LabelEntity> getLabelById(int labelId, int userId);
	
	@Query(value = "select * from label_details where name=?", nativeQuery = true)
	Optional<LabelEntity> getLabelByName(String labelName);
	
	@Query(value = "select * from label_details where user_id=?", nativeQuery = true)
	List<LabelEntity> findAllLabels(int userId);
	
	@Modifying
	@Query(value = "update label_details set name=? where user_id=? and label_id=?", nativeQuery = true)
	void updateLabel(String labelName, int userId, int labelId);

	@Modifying
	@Transactional
	@Query(value = "Delete from label_details where name=? and user_id=?", nativeQuery = true)
	void deleteLabel(String labelName, int userId);
	
}
