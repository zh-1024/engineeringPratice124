package com.fourzhang.youddit.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PublishedResponse {
    private Long contentId;
    private List<String> imgUrl;
    private LocalDateTime time;
    private String userName;
    private List<String> labelNames;
}
