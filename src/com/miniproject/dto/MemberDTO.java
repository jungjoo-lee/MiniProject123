package com.miniproject.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MemberDTO {
	private String uid;
	private String pwd;
	private String name;
	private int age;
	private String gender;
	private Date registerDate;
}
