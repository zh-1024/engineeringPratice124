package com.fourzhang.youddit.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime sendTime;
}
