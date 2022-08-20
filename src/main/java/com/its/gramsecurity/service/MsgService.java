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
//
        List<MsgEntity> elist = msgr.findLeft(loginId);

        List<MsgDTO> mlist = new ArrayList<>();
        String friendId = "";
        if(elist.isEmpty()){
                List<MsgEntity> member= msgr.findLeft1(loginId);
                if(member.isEmpty()){
                    mlist=null;
                }
                else{
                    friendId = member.get(0).getLoginId();
                    List<MsgEntity> flist = msgr.findMsgList(loginId, friendId);
                    mlist.add(MsgDTO.toDto(flist.get(0)));
                }
        }else{

        for (int i = 0; i < elist.size(); i++) {
//            if (elist.isEmpty()) {
//                    mlist=null;
//            }
//            else {
                friendId = elist.get(i).getFriendId();
                List<MsgEntity> flist = msgr.findMsgList(loginId, friendId);
                mlist.add(MsgDTO.toDto(flist.get(0)));

//            }
        }}
        return mlist;
    }


    public List<MsgEntity> count(MsgDTO mem) {
        return msgr.count(mem.getLoginId(),mem.getFriendId());

    }

    public List<MsgEntity> total(String loginId) {
        return msgr.total(loginId);
    }



    void updateProfile(MemberDTO memberDTO,String fileName){
        List<MsgEntity>msgEntityList=msgr.findByLoginId(memberDTO.getLoginId());
        for (int i =0;i< msgEntityList.size();i++){
            System.out.println("MsgService.updateProfile");
            System.out.println("memberDTO = " + memberDTO + ", fileName = " + fileName);
            msgEntityList.get(i).setLoginFileName(fileName);
            msgr.save(msgEntityList.get(i));
        }

    }
}
