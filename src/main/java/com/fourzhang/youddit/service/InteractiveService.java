package com.fourzhang.youddit.service;

import com.fourzhang.youddit.data.Result;

public interface InteractiveService {

    public Result togglelike(Long contentId, Long userId);

    public Result isLiked(Long contentId, Long userId);

    public Result toggleCollect(Long contentId, Long userId);

    public Result isCollected(Long contentId, Long userId);

    public Result addView(Long contentId);
}
