package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
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
@ApiModel(value = "Follow对象", description = "")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Long id;

    private Long user1Id;

    private Long user2Id;

    private LocalDateTime followTime;


}
