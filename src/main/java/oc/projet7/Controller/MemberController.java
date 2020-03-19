package oc.projet7.Controller;

import oc.projet7.Entity.Member;
import oc.projet7.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class MemberController {


    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/saveMember")
    public ResponseEntity<Member> save(@RequestBody Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberService.save(member);
        return new ResponseEntity<>(member, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Member> >listContact() {
        List<Member> Members = memberService.findAll();
        return new ResponseEntity<>(Members,HttpStatus.OK);
    }
}