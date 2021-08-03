package Assessment_MoneyLion.Assessment;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import Assessment_MoneyLion.Assessment.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	
	@Query(value = "SELECT email FROM user p WHERE p.email like ?1",nativeQuery = true)
    String findByEmail( String email);
	
	@Query(value = "SELECT name FROM user p WHERE p.email=?1",nativeQuery = true)
    String findByname( String name);
	
	@Query(value = "SELECT access FROM user p WHERE p.email=?1",nativeQuery = true)
    Boolean findByaccess( String access);

}