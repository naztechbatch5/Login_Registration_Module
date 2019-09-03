package SpringBoot.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roled")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tx_r_id")
	private int tx_r_id;

	@Column(name = "role_name")
	private String role;

	@Column(name = "tx_role_desc")
	private String tx_desc;

	public int getTx_r_id() {
		return tx_r_id;
	}

	public void setTx_r_id(int tx_r_id) {
		this.tx_r_id = tx_r_id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getTx_desc() {
		return tx_desc;
	}

	public void setTx_desc(String tx_desc) {
		this.tx_desc = tx_desc;
	}


	
}