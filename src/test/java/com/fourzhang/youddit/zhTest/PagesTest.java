package com.fourzhang.youddit.zhTest;

import com.fourzhang.youddit.entity.Content;
import com.fourzhang.youddit.mapper.ContentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    public  void testSelectList(){
        List<Content> contents = contentMapper.selectList(null);
        contents.forEach(System.out::println);
    }
}
