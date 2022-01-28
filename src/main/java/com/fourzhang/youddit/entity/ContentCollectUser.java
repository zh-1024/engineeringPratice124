package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("content_collect_user")
@ApiModel(value = "ContentCollectUser对象", description = "")
public class ContentCollectUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long contentId;

    private Long userId;


}
