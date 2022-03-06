package com.fourzhang.youddit.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class CommentCommentRequest {
	private final Long contentCommentId;
	private final Integer from;
	private final Integer num;
}
