package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
@TableName("comment_comment")
@ApiModel(value = "CommentComment对象", description = "")
public class CommentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long contentCommentId;

    private Long commentCommentId;

    private String userId;

    private LocalDateTime commentTime;

    private Integer level;

}
