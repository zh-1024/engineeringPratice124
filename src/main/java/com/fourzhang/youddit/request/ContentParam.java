package com.fourzhang.youddit.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ContentParam {
  //  private Long content_id;
  //  private Long user_id;
    private String info_describe;
    private List<String> label_names;
    private String image_url;
}

