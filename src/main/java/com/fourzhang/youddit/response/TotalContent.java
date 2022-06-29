package com.fourzhang.youddit.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time;
    private String userName;
    private List<String> labelNames;
    private Long likenum;
    private String info_describe;
    private Long collectnum;
    public TotalContent(){};
}
