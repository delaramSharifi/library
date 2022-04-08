package ir.delaramsharifi.service;


import ir.delaramsharifi.model.MemberDto;

import java.util.List;

public interface MemberService {

    MemberDto save (MemberDto newMember);

    MemberDto findById(Integer memberId);

    void deleteMember(Integer memberId);

    List<MemberDto> findAll();

    MemberDto last_nameContains(String lastname);
}
