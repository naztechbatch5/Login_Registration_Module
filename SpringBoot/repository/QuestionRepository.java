package SpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringBoot.entites.Question;



public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
