package com.fourzhang.youddit.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ResetRequest {
	private final String username;
	private final String password;
	private final String newPassword;
	private final String email;
}
