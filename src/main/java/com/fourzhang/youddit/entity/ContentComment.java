package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zh
 * @since 2022-01-28
 */
@Getter
@Setter
@TableName("content_comment")
@ApiModel(value = "CotentComment对象", description = "")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContentComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long contentCommentId;

    private Long contentId;

    private Long userId;

    private String commentInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime  commentTime;


}
