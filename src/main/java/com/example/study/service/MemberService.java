package com.example.study.service;

import com.example.study.dto.MemberDTO;
import com.example.study.entity.MemberEntity;
import com.example.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        // 위의 save는 바꿔도 되니만 밑에는 안 됨
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //jpa가 제공하는 repository의 save를 사용함
    }

    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        if(byMemberEmail.isPresent()) {
            //조회 결과가 있다(해당 이메일을 가진 회원정보가 있다
            MemberEntity memberEntity = byMemberEmail.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())){
                //비밀번호 일치
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }
            else {
                // 불일치
                return null;
            }
        }
        else   {
            //조회 결과가 없다
            return null;
        }
    }
}
