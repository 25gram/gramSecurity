package com.its.gramsecurity.service;

import com.its.gramsecurity.dto.MemberDTO;
import com.its.gramsecurity.dto.MsgDTO;
import com.its.gramsecurity.entity.BoardEntity;
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
                dtoList.add(MsgDTO.toSaveDto(mlist.get(i)));
        }
        return dtoList;
    }


    public void save(MsgDTO mem) {
        msgr.save(MsgEntity.toEntity(mem));
    }

    public List<MsgDTO> msglist(String loginId) {
//        List<MsgEntity>elist=msgr.findByLoginId(loginId);
//        List<MsgEntity>elist=msgr.findMsgList(loginId);
        List<MsgEntity>elist=msgr.findMsgList(loginId);
        List<MsgDTO>mlist=new ArrayList<>();
        System.out.println("MsgService.msglist");
        System.out.println("elist = " + elist);
        if(elist.isEmpty()){
            mlist=null;
        }else{
        for(int i=0;i<elist.size();i++) {
            mlist.add(MsgDTO.toDto(elist.get(i)));
            }

        }
        System.out.println("serv mlist : "+mlist);
        System.out.println("serv mlist size: "+mlist.size());
        return mlist;
    }


    public List<MsgEntity> count(MsgDTO mem) {
        System.out.println("*******************");
        System.out.println("mem.getLoginId() = " + mem.getLoginId());
        System.out.println("mem.getFriendId() = " + mem.getFriendId());
        return msgr.count(mem.getLoginId(),mem.getFriendId());

    }

    public List<MsgEntity> total(String loginId) {
        return msgr.total(loginId);
    }

    void updateProfile(MemberDTO memberDTO,String fileName){
        List<MsgEntity>msgEntityList=msgr.friendName(memberDTO.getMemberName());

        for (int i =0;i< msgEntityList.size();i++){
            msgEntityList.get(i).setLoginFileName(fileName);
            msgr.save(msgEntityList.get(i));
        }

    }
}
