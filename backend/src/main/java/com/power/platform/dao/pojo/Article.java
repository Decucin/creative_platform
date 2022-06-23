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
@TableName("article")
public class Article {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("title")
    private String title;
    @TableField("author_id")
    private Integer authorId;
    @TableField("description")
    private String description;
    @TableField("body_id")
    private Integer bodyId;
    @TableField("list_id")
    private Integer listId;

}
