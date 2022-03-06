package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.context.annotation.Configuration;

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
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Content implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long contentId;

    private Long userId;

    private String infoDescribe;

    private Long viewsNum;

    private Long likeNum;

    private Long collectNum;

    private LocalDateTime postTime;


}
