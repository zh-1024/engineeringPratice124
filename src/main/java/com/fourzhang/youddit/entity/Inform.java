package com.fourzhang.youddit.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zh
 * @since 2022-01-28
 */
@Getter
@Setter
@ApiModel(value = "Inform对象", description = "")
public class Inform implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long newFollowNum;

    private Long newPrivateMessageNum;

    private Long newCommentNum;


}
