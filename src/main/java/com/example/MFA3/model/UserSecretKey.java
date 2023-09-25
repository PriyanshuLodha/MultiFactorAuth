package com.example.MFA3.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "secret_key")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSecretKey {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sid")
	private Integer sid;

	@Column(name = "username")
	private String username;
	@Column(name = "key_test")
	private String key;
	
	

	@Override
	public String toString() {
		return "UserSecretKey [id=" + sid + ", username=" + username + ", key=" + key + "]";
	}



}


