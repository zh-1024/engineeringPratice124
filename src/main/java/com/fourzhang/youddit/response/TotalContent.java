package com.fourzhang.youddit.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class TotalContent {
    private Long contentId;
    private String imgUrl;
    private LocalDateTime time;
    private String userName;
    private List<String> labelNames;
    private Long likenum;
    private String info_describe;
    private Long collectnum;
    public TotalContent(){};
}
