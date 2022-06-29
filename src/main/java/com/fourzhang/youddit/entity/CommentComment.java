package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@TableName("comment_comment")
@ApiModel(value = "CommentComment对象", description = "")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long contentCommentId;

    private Long commentCommentId;

    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime commentTime;

    private Integer level;
    private String commentInfo;

}
