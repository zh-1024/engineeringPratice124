package com.fourzhang.youddit.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value = "Content对象", description = "")
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long contentId;

    private Long userId;

    private String infoDescribe;

    private Long viewsNum;

    private Long likeNum;

    private Long collectNum;

    private LocalDateTime postTime;


}
