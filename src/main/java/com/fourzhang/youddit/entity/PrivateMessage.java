package com.fourzhang.youddit.entity;

import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("private_message")
@ApiModel(value = "PrivateMessage对象", description = "")
public class PrivateMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long senderId;

    private Long receiveId;

    private String message;

    private LocalDateTime sendTime;


}
