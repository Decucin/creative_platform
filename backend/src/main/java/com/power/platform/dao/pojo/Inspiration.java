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
@TableName("inspiration")
public class Inspiration {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("inspiration")
    private String inspiration;
    @TableField("author_id")
    private Integer authorId;

}
