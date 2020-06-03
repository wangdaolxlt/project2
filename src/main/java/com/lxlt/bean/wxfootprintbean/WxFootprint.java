package com.lxlt.bean.wxfootprintbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class WxFootprint {
    String brief;
    String picUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    Date addTime;
    Integer goodsId;
    String name;
    Integer id;
    Double retailPrice;
}
