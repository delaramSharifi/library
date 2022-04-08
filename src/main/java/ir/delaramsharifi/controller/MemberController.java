package ir.delaramsharifi.controller;


import ir.delaramsharifi.model.MemberDto;
import ir.delaramsharifi.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController()
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping(path = "/new",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> createMember(@RequestBody MemberDto newMember) {

        MemberDto savedMemberDto = memberService.save(newMember);

        return ResponseEntity.ok(savedMemberDto);
    }

    @PutMapping(path = "/update",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateMember(@RequestBody MemberDto newMember) {

        MemberDto savedMemberDto = memberService.save(newMember);

        return ResponseEntity.ok(savedMemberDto);
    }

    @GetMapping(path = "/{memberId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> findById(@PathVariable Integer memberId) {
        MemberDto findMemberDto = memberService.findById(memberId);
        return ResponseEntity.ok(findMemberDto);
    }

    @DeleteMapping(path = "/{memberId}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> deleteById(@PathVariable Integer memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.ok("member Deleted!");
    }

    @GetMapping(path = "/all",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> allMember() {
        List<MemberDto> memberDtos = memberService.findAll();
        return ResponseEntity.ok(memberDtos);
    }
}
