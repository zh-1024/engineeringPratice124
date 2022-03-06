package com.fourzhang.youddit.response;

import com.fourzhang.youddit.entity.Content;
import lombok.*;

import java.util.List;

/**
 * TODO: 内容列表以及分页信息的返回结果实体类
 * @author zh
 * @version 1.0
 * @date 2022/3/6 000615:15
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ContentResponse {
    private long pages; //总页数
    private long  total; //总记录数
    private boolean hasNext; //是否有下一页
    private boolean hasPrevious; //是否有前一页
    private List<Content> contents;
}
