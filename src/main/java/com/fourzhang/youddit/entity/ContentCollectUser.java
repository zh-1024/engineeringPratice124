package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContentCollectUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long contentId;

    private Long userId;


}
