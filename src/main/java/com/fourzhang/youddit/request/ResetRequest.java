package com.fourzhang.youddit.request;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ResetRequest {
	private String username;
	private String password;
	private String newPassword;
	private String email;

}
