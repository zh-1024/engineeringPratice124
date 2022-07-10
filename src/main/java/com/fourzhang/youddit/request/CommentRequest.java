package com.fourzhang.youddit.request;

import lombok.*;

//进行两种评论时传入此参数
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class CommentRequest {
    private String comment_info;
    private Long contentId;
    private Long contentCommentId;
    private Long commentCommentId;
    private Integer level;

    public CommentRequest() {
    };
}
