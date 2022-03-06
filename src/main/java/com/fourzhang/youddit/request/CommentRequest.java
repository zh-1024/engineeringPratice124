package com.fourzhang.youddit.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String comment_info;
    private Long contentCommentId;
    private Long commentCommentId;
    private Integer level;
}
