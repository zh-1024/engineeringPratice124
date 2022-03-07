package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

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

    private Long viewsNum=0L;

    private Long likeNum=0L;

    private Long collectNum=0L;

    private LocalDateTime postTime;


}
