package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.MsgDTO;
import com.its.gramsecurity.entity.MsgEntity;
import com.its.gramsecurity.repository.MsgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MsgService {
@Autowired
    MsgRepository msgr;

    public List<MsgDTO> findByFriendId(MsgDTO mem) {

//        return
                List<MsgEntity> mlist=msgr.findByFriendId(MsgEntity.toEntity(mem));
                List<MsgDTO> dtoList=null;
                for(int i = 0; i<mlist.size(); i++){
                dtoList.add(MsgDTO.toDto(mlist.get(i)));
                }
                return dtoList;
    }
}
