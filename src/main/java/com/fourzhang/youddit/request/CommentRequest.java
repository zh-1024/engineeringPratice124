package com.fourzhang.youddit.request;

import lombok.Data;
//进行两种评论时传入此参数
@Data
public class CommentRequest {
    private final String comment_info;
    private final Long contentId;
    private final Long contentCommentId;
    private final Long commentCommentId;
    private final Integer level;
}
