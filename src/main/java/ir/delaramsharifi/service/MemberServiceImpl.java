package ir.delaramsharifi.service;


import ir.delaramsharifi.domain.MemberEntity;
import ir.delaramsharifi.mapper.MemberMapper;
import ir.delaramsharifi.model.MemberDto;
import ir.delaramsharifi.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public MemberDto save(MemberDto newMemberDto) {
        MemberEntity newMember = memberMapper.toMemberEntity(newMemberDto);
        MemberEntity savedMember = memberRepository.save(newMember);
        return memberMapper.toMemberDto(savedMember);
    }

    @Override
    public MemberDto findMemberDtoById(Integer id) {
        MemberDto findMemberDto = memberRepository.findById(id)
                .filter(Objects::nonNull)
                .map(memberMapper::toMemberDto)
                .orElse(null);
        return findMemberDto;
    }

    @Override
    public void deleteMember(Integer id) {
        memberRepository.deleteById(id);
    }

    @Override
    public List<MemberDto> findAll() {
        List<MemberDto> memberDtos = StreamSupport
                .stream(memberRepository.findAll().spliterator(), false)
                .map(memberMapper::toMemberDto)
                .collect(Collectors.toList());
        return memberDtos;
    }

    @Override
    public MemberDto last_nameContains(String lastname) {
        MemberEntity memberContains = memberRepository.findByLastNameContains(lastname);
        return memberMapper.toMemberDto(memberContains);
    }
}
