package com.fourzhang.youddit.response;

import com.fourzhang.youddit.entity.Content;
import lombok.Data;

import java.util.List;

/**
 * @author sharkboom
 * @create 2022-03-06-19:07
 */
//返回用户头像和用户名的列表，用于查询关注、粉丝列表
@Data
public class NameWithImageListResponse {
    private long pages; //总页数
    private long  total; //总记录数
    private boolean hasNext; //是否有下一页
    private boolean hasPrevious; //是否有前一页
    private List<NameWithImageResponse> contents;
}
