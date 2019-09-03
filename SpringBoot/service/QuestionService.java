package SpringBoot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import SpringBoot.entites.Question;
import SpringBoot.repository.QuestionRepository;



@Service
public class QuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	
	public List<Question>GetAllQues(){
		return questionRepository.findAll();
		
	}
	

}
