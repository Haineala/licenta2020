package repository;

import org.springframework.data.repository.CrudRepository;

import manager.entity.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer>{
	
	

}
