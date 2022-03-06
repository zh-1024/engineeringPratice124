package com.fourzhang.youddit.request;

import lombok.Data;

/**
 * @author sharkboom
 * @creat 2022-03-06-14:42
 */
@Data
public class UserInformationRequest {
    private String username;
    private String avatar;
    private String profile;
    private String Email;
    private String phone;
}
