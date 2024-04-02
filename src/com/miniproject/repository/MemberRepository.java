package com.miniproject.repository;

import com.miniproject.dto.MemberDTO;

public interface MemberRepository {
	void selectAllMember();
	void insertMember(MemberDTO memberDTO);
}
