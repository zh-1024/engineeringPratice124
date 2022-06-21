package com.fourzhang.youddit.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Labels {
    private List<String> names;
    private List<Long> ids;
}
