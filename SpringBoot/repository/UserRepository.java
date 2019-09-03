package SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import SpringBoot.entites.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {

	 User findByEmail(String email);
     User findByisEnabled(boolean isEnabled);
     
}
