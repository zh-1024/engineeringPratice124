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
@ApiModel(value = "Follow对象", description = "")
public class Follow implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long user1Id;

    private Long user2Id;

    private LocalDateTime followTime;


}
