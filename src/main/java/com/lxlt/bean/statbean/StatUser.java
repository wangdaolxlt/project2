package com.lxlt.bean.statbean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class StatUser {
    private Integer users;

    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date day;
}
