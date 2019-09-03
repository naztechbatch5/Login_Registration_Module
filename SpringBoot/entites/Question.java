package SpringBoot.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "question_table")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "tx_question_id")
	private int tx_question_id;
	
    @NotNull(message="Enter Security Question")
	@Column(name = "tx_question_name")
	private String tx_question_name;

	public int getTx_question_id() {
		return tx_question_id;
	}

	public void setTx_question_id(int tx_question_id) {
		this.tx_question_id = tx_question_id;
	}

	public String getTx_question_name() {
		return tx_question_name;
	}

	public void setTx_question_name(String tx_question_name) {
		this.tx_question_name = tx_question_name;
	}


	
	

}
