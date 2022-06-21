package com.fourzhang.youddit.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContenCommentResponse {
    private Long contentCommentId;

    private Long contentId;

    private String userName;

    private String commentInfo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime commentTime;
}
