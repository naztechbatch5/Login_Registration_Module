package SpringBoot.repository;

import org.springframework.data.repository.CrudRepository;

import SpringBoot.entites.ConfirmationToken;


public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
