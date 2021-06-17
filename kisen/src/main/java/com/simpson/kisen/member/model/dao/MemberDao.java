package com.simpson.kisen.member.model.dao;

import com.simpson.kisen.agency.model.vo.Agency;
import com.simpson.kisen.fan.model.vo.Fan;

public interface MemberDao {

	Fan selectOneMember(String id);

	int insertMember(Fan member);

	int insertAgency(Agency agency);

	int insertAgencyMember(Fan member);

	int insertMemberAuthority(Fan member);

	int insertAgencyAuthority(Fan member);

}
