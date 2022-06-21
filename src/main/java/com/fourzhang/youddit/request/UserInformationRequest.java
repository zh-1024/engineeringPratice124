package com.fourzhang.youddit.request;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author sharkboom
 * @creat 2022-03-06-14:42
 */
@Data
@NoArgsConstructor
public class UserInformationRequest {
    private String username;
    private String avatar;
    private String profile;
    private String Email;
    private String phone;
}
