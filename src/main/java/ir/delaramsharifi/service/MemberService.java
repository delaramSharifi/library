package ir.delaramsharifi.service;


import ir.delaramsharifi.mapper.MappingUtil;
import ir.delaramsharifi.model.MemberDto;

import java.util.List;
@MappingUtil.MemberService
public interface MemberService {

    MemberDto save (MemberDto newMember);

    @MappingUtil.MemberIdToMemberEntity
    MemberDto findMemberDtoById(Integer memberId);


    void deleteMember(Integer memberId);

    List<MemberDto> findAll();

    MemberDto last_nameContains(String lastname);

    boolean isMemberDateValid(String expiryDate);
}
