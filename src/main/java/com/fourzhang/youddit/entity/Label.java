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
@ApiModel(value = "Label对象", description = "")
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long labelId;

    private String labelName;

    private Long useNum;


}
