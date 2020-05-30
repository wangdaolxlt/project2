package com.lxlt.bean.issuebean;

import lombok.Data;

@Data
public class IssueRespVo<T> {

   private Integer errno;
   private T data;
   private String errmsg;
}
