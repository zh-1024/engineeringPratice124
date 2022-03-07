package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;
import com.fourzhang.youddit.entity.Content;

/**
 * @author zh
 * TODO: 本模块包括与点赞、关注、收藏、浏览等交互接口
 */
public interface InteractiveService {
    /**
     * @author zh
     * TODO: 点赞内容
     */
    public Result likeContent(Long contentId,Long userId);
    /**
     * @author zh
     * TODO: 取消对某内容的点赞
     */
    public Result cancelLikeContent(Long contentId,Long userId);
    /**
     * @author zh
     * TODO: 判断是否已对此内容点赞
     */
    public Result<Boolean> contentIsLiked(Long contentId,Long userId);
    /**
     * @author zh
     * TODO: 收藏内容
     */
    public Result collectContent(Long contentId,Long userId);
    /**
     * @author zh
     * TODO: 取消收藏内容
     */
    public Result cancelCollectContent(Long contentId,Long userId);
    /**
     * @author zh
     * TODO: 收藏内容
     */
    public Result<Boolean> contentIsCollect(Long contentId,Long userId);
    /**
     * @author zh
     * TODO: 浏览量增加
     */
    public Result addView(Long contentId);
}
