package com.fourzhang.youddit.request;

import lombok.Data;

import java.util.List;

@Data
public class ContentParam {
  //  private Long content_id;
    private Long user_id;
    private String info_describe;
    private List<String> label_names;
    private List<String> image_url;
}

