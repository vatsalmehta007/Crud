package com.example.CRUD.ServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CRUD.Constant.ApplicationConstant;
import com.example.CRUD.Entity.UserDto;
import com.example.CRUD.Entity.UserEntity;
import com.example.CRUD.Reposetory.UserRepository;
import com.example.CRUD.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public Map<String, Object> saveuser(UserDto user) {
		Map<String, Object> map = new HashMap<String, Object>();
		// TODO Auto-generated method stub
		try {
			Optional<UserEntity> userEntity = userRepository.findOneByEmailIgnoreCase(user.getEmail());

			if (!userEntity.isPresent()) {
				userRepository.save(populateData(user));
				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
				map.put(ApplicationConstant.RESPONSE_DATA, new ArrayList<>());
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.REGISTRATION_SUCCESS);

			} else {
				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.REGISTRATION_EMAIL_EXISTS);
			}

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("problem occur : ", e.getMessage());
		}
		return map;
	}

	private UserEntity populateData(UserDto user) {
		UserEntity userEntity = new UserEntity();
		userEntity.setName(user.getName());
		userEntity.setEmail(user.getEmail());
		userEntity.setAddress(user.getAddress());
		userEntity.setNumber(user.getNumber());
		return userEntity;
	}

	@Override
	public Map<String, Object> getuser(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Optional<UserEntity> userEntity = userRepository.findById(id);
			if (userEntity.isPresent()) {
				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
				map.put(ApplicationConstant.RESPONSE_DATA, userEntity.get());
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.USER_LIST_SUCCESS);

			} else {
				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.USER_NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("problem occur : ", e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> updateUserEmail(Long id, String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> mapObj = new HashMap<String, Object>();

		// TODO Auto-generated method stub
		try {
			Optional<UserEntity> userEntity = userRepository.findById(id);
			if (userEntity.isPresent()) {
				UserEntity user = userEntity.get();
				user.setEmail(email);
				userRepository.save(user);

				mapObj.put("name", user.getName());
				mapObj.put("email", user.getEmail());
				mapObj.put("number", user.getNumber());
				mapObj.put("address", user.getAddress());
				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
				map.put(ApplicationConstant.RESPONSE_DATA, mapObj);
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.USER_UPDATE_SUCCESS);

			} else {
				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.USER_NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("proble occur " + e.getMessage());
		}
		return map;
	}

	@Override
	public Map<String, Object> deleteuser(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Optional<UserEntity> userEntity = userRepository.findById(id);
			if (userEntity.isPresent()) {
				userRepository.deleteUser(id);

				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_200);
				map.put(ApplicationConstant.RESPONSE_DATA, new ArrayList<>());
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.USER_DELETE);
			} else {
				map.put(ApplicationConstant.RESPONSE_STATUS, ApplicationConstant.STATUS_400);
				map.put(ApplicationConstant.RESPONSE_MESSAGE, ApplicationConstant.USER_NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("problem occur : ", e.getMessage());
		}
		return map;
	}
}