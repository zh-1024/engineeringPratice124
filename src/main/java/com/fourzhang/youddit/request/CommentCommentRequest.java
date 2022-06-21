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
	private Long contentCommentId;
	private Integer from;
	private Integer num;
	public CommentCommentRequest(){};
}
