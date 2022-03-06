package com.fourzhang.youddit.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sharkboom
 * @creat 2022-03-06-13:20
 */
@Data
@AllArgsConstructor
public class UserHomePageResponse {
    private String username;
    private String avatar;
    private Long followers_num;
    private Long following_num;
    private String profile;

}
