package com.fourzhang.youddit.request;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class SignUpRequest {
	private String username;
	private String password;
	private String email;
	private String phone;
}
