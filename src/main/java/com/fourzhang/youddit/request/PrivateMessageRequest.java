package com.fourzhang.youddit.request;

import lombok.*;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString

public class PrivateMessageRequest {
	private Long receiveId;
	private String message;

	public PrivateMessageRequest() {
	};
}
