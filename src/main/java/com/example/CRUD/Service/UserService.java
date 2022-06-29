package com.example.CRUD.Service;

import java.util.Map;

import com.example.CRUD.Entity.UserDto;

public interface UserService {

	public Map<String, Object> saveuser(UserDto user);

	public Map<String, Object> getuser(Long id);

	public Map<String, Object> updateUserEmail(Long id, String email);

	public Map<String, Object> deleteuser(Long id);

}
