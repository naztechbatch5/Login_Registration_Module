package SpringBoot.entites;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "create_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name = "tx_u_id")
	private int tx_u_id;
	
	@NotNull(message="Enter username is compulsory")
	@Column(name = "tx_user_name")
	private String tx_user_name;

	@NotNull(message="Email is compulsory")
	@Email(message = "Email is invalid")
	@Column(name = "email")
	private String email;
	
	@NotNull(message="Enter your password")
	@Column(name = "tx_user_password")
	private String tx_user_password;
	
	@NotNull(message="Enter Role is compulsory")
	@Column(name = "tx_role_name")
	private String tx_role_name;
	
	@NotNull(message="Select Security Question")
	@Column(name = "tx_question_name")
	private String tx_question_name;
	
	@NotNull(message="Enter Security Question Answer")
	@Column(name = "tx_question_answer")
	private String tx_question_answer;
	
	@NotNull(message="Enter Your Question Answer")
	@Column(name = "tx_customes_question")
	private String tx_customes_question;
	
	@NotNull(message="Enter Question Answer")
	@Column(name = "tx_customes_answer")
	private String tx_customes_answer;
	
	@Column(name = "isEnabled")
	private boolean isEnabled;
	
    private Date tx_created_date;
    private Date tx_update_date;
    private Date tx_deleteDate;
	
    @ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "create_user_role", joinColumns = @JoinColumn(name = "tx_u_id"), inverseJoinColumns = @JoinColumn(name = "tx_r_id"))
	private Set<Role> roles;

	public int getTx_u_id() {
		return tx_u_id;
	}

	public void setTx_u_id(int tx_u_id) {
		this.tx_u_id = tx_u_id;
	}

	public String getTx_user_name() {
		return tx_user_name;
	}

	public void setTx_user_name(String tx_user_name) {
		this.tx_user_name = tx_user_name;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTx_user_password() {
		return tx_user_password;
	}

	public void setTx_user_password(String tx_user_password) {
		this.tx_user_password = tx_user_password;
	}

	public String getTx_role_name() {
		return tx_role_name;
	}

	public void setTx_role_name(String tx_role_name) {
		this.tx_role_name = tx_role_name;
	}

	public String getTx_question_name() {
		return tx_question_name;
	}

	public void setTx_question_name(String tx_question_name) {
		this.tx_question_name = tx_question_name;
	}

	public String getTx_question_answer() {
		return tx_question_answer;
	}

	public void setTx_question_answer(String tx_question_answer) {
		this.tx_question_answer = tx_question_answer;
	}

	public String getTx_customes_question() {
		return tx_customes_question;
	}

	public void setTx_customes_question(String tx_customes_question) {
		this.tx_customes_question = tx_customes_question;
	}

	public String getTx_customes_answer() {
		return tx_customes_answer;
	}

	public void setTx_customes_answer(String tx_customes_answer) {
		this.tx_customes_answer = tx_customes_answer;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public Date getTx_created_date() {
		return tx_created_date;
	}

	public void setTx_created_date(Date tx_created_date) {
		this.tx_created_date = tx_created_date;
	}

	public Date getTx_update_date() {
		return tx_update_date;
	}

	public void setTx_update_date(Date tx_update_date) {
		this.tx_update_date = tx_update_date;
	}

	public Date getTx_deleteDate() {
		return tx_deleteDate;
	}

	public void setTx_deleteDate(Date tx_deleteDate) {
		this.tx_deleteDate = tx_deleteDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
    



}
