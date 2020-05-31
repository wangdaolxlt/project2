package com.lxlt.service.commentservice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lxlt.bean.Comment;
import com.lxlt.bean.CommentExample;
import com.lxlt.bean.commentbean.CommentQueryBean;
import com.lxlt.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @PackgeName: com.lxlt.service.commentservice
 * @ClassName: CommentServiceImpl
 * @Author: Li Haiquan
 * Date: 2020/5/30 10:54
 * project name: project2
 */
@Service
public class CommentServiceImpl implements CommentService{


    @Autowired
    CommentMapper commentMapper;



    @Override
    public Map queryComment(CommentQueryBean commentQueryBean) {
        PageHelper.startPage(commentQueryBean.getPage(), commentQueryBean.getLimit());
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        //排除逻辑删除
        criteria.andDeletedEqualTo(false);
        //查询用户id
        if(commentQueryBean.getUserId() != null){
            criteria.andUserIdEqualTo(commentQueryBean.getUserId());
        }
        // 查询商品id
        if(commentQueryBean.getValueId() != null){
            criteria.andValueIdEqualTo(commentQueryBean.getValueId());
        }
        commentExample.setOrderByClause(commentQueryBean.getSort() + " " + commentQueryBean.getOrder());
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        long total = pageInfo.getTotal();
        Map<String, Object> commentMap = new HashMap<>();
        commentMap.put("total", total);
        commentMap.put("items", comments);
        return commentMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteById(Comment comment) {
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andIdEqualTo(comment.getId());
        comment.setDeleted(true);
        comment.setUpdateTime(new Date());
        int commentCode = commentMapper.updateByExampleSelective(comment, commentExample);
        if(commentCode == 0){
            return 500;
        }
        return 200;
    }
}
