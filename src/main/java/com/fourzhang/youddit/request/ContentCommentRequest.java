package com.fourzhang.youddit.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class ContentCommentRequest {
	private Long contentId;
	private Integer from;
	private Integer num;
	public ContentCommentRequest(){};
}
