package com.example.study.service;

import com.example.study.dto.MemberDTO;
import com.example.study.entity.MemberEntity;
import com.example.study.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;
    public void save(MemberDTO memberDTO) {
        // 위의 save는 바꿔도 되니만 밑에는 안 됨
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        memberRepository.save(memberEntity);
        //jpa가 제공하는 repository의 save를 사용함
    }
}
