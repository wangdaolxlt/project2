package com.lxlt.service.wxcommentservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.Comment;
import com.lxlt.bean.CommentExample;
import com.lxlt.bean.User;
import com.lxlt.bean.commentbean.WxCommentReqVo;
import com.lxlt.bean.commentbean.WxCommentRespDataVo;
import com.lxlt.bean.userbean.WxUserInfo;
import com.lxlt.mapper.CommentMapper;
import com.lxlt.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.wxcommentservice
 * @ClassName: WxCommentServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/6/4 3:20
 * project name: project2
 */
@Service
public class WxCommentServiceImpl implements WxCommentService{

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public Map commentList(WxCommentReqVo wxCommentReqVo) {
        // 获取所需参数
        Integer valueId = wxCommentReqVo.getValueId();
        Byte type = wxCommentReqVo.getType();
        Integer size = wxCommentReqVo.getSize();
        Integer page = wxCommentReqVo.getPage();
        Integer showType = wxCommentReqVo.getShowType();

        //开启分页，固定用法
        PageHelper.startPage(page,size);
        // 第一层Data：用map接收整个参数
        HashMap<String, Object> map = new HashMap();
        // 第二层Data：内含user表的userInfo，comment表的addTime，picList，content
        ArrayList<WxCommentRespDataVo> wxCommentRespDataVoList = new ArrayList();
        // 第三层的Comment：查找Comment：相当于select * from comment where value_id = valueid;
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria().andValueIdEqualTo(valueId).andTypeEqualTo(type).andDeletedEqualTo(false);
        // 判断是否含有图片
        if (showType==1){
            criteria.andHasPictureEqualTo(true);
        }
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        // 循环遍历commentList，把comment表中的addTime，picList，content查出添加到wxCommentRespVo中，
        for (Comment comment : commentList) {
            WxCommentRespDataVo wxCommentRespDataVo = new WxCommentRespDataVo();
            wxCommentRespDataVo.setAddTime(comment.getAddTime());
            wxCommentRespDataVo.setPicList(comment.getPicUrls());
            wxCommentRespDataVo.setContent(comment.getContent());
            // 去comment表中取出userId，到user表中找到id对应的nickName和avatarUrl
            User user = userMapper.selectByPrimaryKey(comment.getUserId());
            WxUserInfo userInfo = new WxUserInfo(user.getNickname(),user.getAvatar());
            wxCommentRespDataVo.setUserInfo(userInfo);
            wxCommentRespDataVoList.add(wxCommentRespDataVo);
        }

        // 分页插件
        PageInfo<WxCommentRespDataVo> wxCommentRespDataVoPageInfo= new PageInfo<>(wxCommentRespDataVoList);
        // 第一层，全放入map中返回
        map.put("data",wxCommentRespDataVoList);
        map.put("count",wxCommentRespDataVoPageInfo.getTotal());
        map.put("currentPage",wxCommentRespDataVoPageInfo.getPageNum());
        return map;
    }

    @Override
    public WxCommentRespDataVo commentCount(WxCommentReqVo wxCommentReqVo) {
        Integer id = wxCommentReqVo.getValueId();
        Byte type = wxCommentReqVo.getType();
        WxCommentRespDataVo wxCommentRespDataVo = new WxCommentRespDataVo();

        CommentExample hasPicExample = new CommentExample();
        // 根据是否hasPictureEqual和valueId和type查找带图评论总数
        hasPicExample.createCriteria().andValueIdEqualTo(id).andTypeEqualTo(type).andHasPictureEqualTo(true).andDeletedEqualTo(false);
        wxCommentRespDataVo.setHasPicCount(commentMapper.countByExample(hasPicExample));
        // 根据valueId和type查找所有评论
        CommentExample example = new CommentExample();
        example.createCriteria().andValueIdEqualTo(id).andTypeEqualTo(type).andDeletedEqualTo(false);
        wxCommentRespDataVo.setAllCount(commentMapper.countByExample(example));
        return wxCommentRespDataVo;
    }
}
