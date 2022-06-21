package com.fourzhang.youddit.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentcommentResponse {
    private Long id;

    private Long contentCommentId;

    private Long commentCommentId;

    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime commentTime;

    private Integer level;
    private String comment_info;
}
