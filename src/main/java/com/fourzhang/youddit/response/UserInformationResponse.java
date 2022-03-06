package com.fourzhang.youddit.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author sharkboom
 * @creat 2022-03-06-14:23
 */
@Data
@AllArgsConstructor
public class UserInformationResponse {
    private String username;
    private String avatar;
    private String profile;
    private String Email;
    private String phone;
}
