package com.lxlt.bean.issuebean;

import lombok.Data;

@Data
public class IssueRep {
    Integer page;
    Integer limit;
    String sort;
    String order;
    String question;
}
