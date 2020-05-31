package com.lxlt.bean.storagebean;

import com.lxlt.bean.BaseQueryBean;
import lombok.Data;

/**
 * @PackgeName: com.lxlt.bean.storagebean
 * @ClassName: StorageQueryBean
 * @Author: Li Haiquan
 * Date: 2020/5/30 18:34
 * project name: project2
 */
@Data
public class StorageQueryBean extends BaseQueryBean {

    /**
     * 对象key
     */
    private String key;
    /**
     * 对象名称
     */
    private String name;
}
