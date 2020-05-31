package com.lxlt.bean.commentbean;

import com.lxlt.bean.BaseQueryBean;
import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.commentbean
 * @ClassName: CommentQueryBean
 * @Author: Li Haiquan
 * Date: 2020/5/30 10:42
 * project name: project2
 */
@Data
public class CommentQueryBean extends BaseQueryBean {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 商品id
     */
    private Integer valueId;
}
