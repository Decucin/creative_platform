package com.power.platform.dao.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("body")
public class Body {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("raw_body")
    private String rawBody;
    @TableField("html_body")
    private String htmlBody;

}
