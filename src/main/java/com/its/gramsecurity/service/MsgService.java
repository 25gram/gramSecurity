package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.dto.MsgDTO;
import com.its.gramsecurity.entity.MsgEntity;
import com.its.gramsecurity.repository.MsgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MsgService {
@Autowired
    MsgRepository msgr;

//    public List<MsgDTO> findByLoginId(String loginId,String friendId) {
//
////        return
//                List<MsgEntity> mlist=msgr.findAll();
//                List<MsgDTO> dtoList=new ArrayList<>();
//                for(int i = 0; i<mlist.size(); i++){
//                    if(mlist.get(i).getFriendId().equals(friendId) && mlist.get(i).getLoginId().equals(loginId)){
//                dtoList.add(MsgDTO.toSaveDto(mlist.get(i)));
//                    }
//                }
//        System.out.println("MsgService.findByLoginId");
//                System.out.println("dtoList = " + dtoList);
//        System.out.println("loginId = " + loginId + ", friendId = " + friendId);
//        System.out.println("mlist: "+mlist);
//                return dtoList;
//    }


    public List<MsgDTO> findList(String loginId,String friendId) {

//        return
        List<MsgEntity> mlist=msgr.findList(loginId,friendId);
        List<MsgDTO> dtoList=new ArrayList<>();
        for(int i = 0; i<mlist.size(); i++){
//            if(mlist.get(i).getFriendId().equals(friendId) && mlist.get(i).getLoginId().equals(loginId)){
                dtoList.add(MsgDTO.toSaveDto(mlist.get(i)));
//            }
        }
        System.out.println("MsgService.findByLoginId");
        System.out.println("dtoList = " + dtoList);
//        System.out.println("loginId = " + loginId + ", friendId = " + friendId);
        System.out.println("mlist: "+mlist);
        return dtoList;
    }


    public void save(MsgDTO mem) {
        System.out.println("MsgService.save mem : "+mem);
        msgr.save(MsgEntity.toEntity(mem));
    }

    public List<MsgDTO> msglist(String loginId) {
        List<MsgEntity>elist=msgr.findMsgList(loginId);
        List<MsgDTO>mlist=new ArrayList<>();
        for(int i=0;i<elist.size();i++){
            mlist.add(MsgDTO.toDto(elist.get(i)));
        }
        return mlist;
    }
}
