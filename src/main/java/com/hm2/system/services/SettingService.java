//package com.playerone.system.services;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.playerone.common.constants.ProjectConstant;
//import com.playerone.system.entities.UploadPath;
//import com.playerone.system.repositories.UploadPathRepository;
//
//@Service
//public class SettingService {
//
//	@Autowired
//	private UploadPathRepository uploadPathRepository;
//
//	public List<UploadPath> findUploadPathAll() {
//		Iterable<UploadPath> iterable = uploadPathRepository.findAll();
//		List<UploadPath> uploadPathList = new ArrayList<>();
//		iterable.forEach(uploadPathList::add);
//		return uploadPathList;
//	}
//
//	public void addUploadPath(UploadPath uploadPath) {
//		if(uploadPath.isNew()) {
//
//			uploadPath.setIsActive(ProjectConstant.Flag.N);
//			uploadPathRepository.save(uploadPath);
//		}else {
//			List<UploadPath> entities = findUploadPathAll();
//			for (UploadPath entity : entities) {
//				entity.setIsActive(ProjectConstant.Flag.N);
//				uploadPathRepository.save(entity);
//			}
//			Optional<UploadPath> entiry = uploadPathRepository.findById(uploadPath.getId());
//			if(entiry.isPresent()) {
//				entiry.get().setIsActive(ProjectConstant.Flag.Y);
//				uploadPathRepository.save(entiry.get());
//			}
//		}
//	}
//
//	public void deleteUploadPathById(long id) {
//		uploadPathRepository.deleteById(id);
//	}
//
//}
