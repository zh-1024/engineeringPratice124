package com.fourzhang.youddit.response;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class PrivateMessageResponse {
	private final Long senderId;
	private final String message;
}
