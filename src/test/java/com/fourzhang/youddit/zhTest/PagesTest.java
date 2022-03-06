package com.fourzhang.youddit.zhTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.mapper.ContentLabelMapper;
import com.fourzhang.youddit.mapper.ContentMapper;
import com.fourzhang.youddit.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.ListeningSecurityContextHolderStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO 分页查询测试
 * @author zh
 * @version 1.0
 * @date 2022/3/6 000611:41
 */
@SpringBootTest
public class PagesTest {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private ContentLabelMapper contentLabelMapper;
    @Test
    public void testSelectContentsByLabelId(){
        Page<Content> page = new Page<>(1,2);
        Page<Content> page1 = contentLabelMapper.selectContentsByLabel(page, 0L);
        System.out.println(new ArrayList<>(page1.getRecords()));
    }
//    @Test
//    public void mapperXmlTest() {
//        List<Content> contents = contentMapper.findSubscribeContents(1L);
//        System.out.println(contents.size());
//    }

    @Test
    public  void testSelectList(){
        List<Content> contents = contentMapper.selectList(null);
        contents.forEach(System.out::println);
        Content content = contentMapper.selectById(1L);
        System.out.println(content);
    }
    @Test
    public  void testInsert(){
        Content content = new Content();
        content.setInfoDescribe("动态信息");
        content.setUserId(1L);
        content.setCollectNum(123L);
        content.setViewsNum(123L);
        content.setLikeNum(123l);
        contentMapper.insert(content);
        System.out.println(content.getContentId());
    }
    @Test
    public  void testDelete(){

    }
    @Test
    public  void wrapperSelectTest(){
        QueryWrapper<Content>  queryWrapper=new QueryWrapper<>();
        queryWrapper.orderBy(true,true,"like_num","collect_num");
        for (Content content : contentMapper.selectList(queryWrapper)) {
            System.out.println(content);
        }
    }
    @Test
    public  void pagesListTest(){
        QueryWrapper<Content>  queryWrapper=new QueryWrapper<>();
        Page<Content> page=new Page<>(0,3);
        queryWrapper.orderBy(true,true,"like_num","collect_num");
        Page<Content> page1 = contentMapper.selectPage(page, queryWrapper);
        System.out.println(page1.getRecords());//获取当前页的数据
        System.out.println(page1.getPages());//获取总页数
        System.out.println(page1.getTotal());//总记录数
        System.out.println(page1.hasNext());//是否有下一页
        System.out.println(page1.hasPrevious());//是否有上一页

    }
}
