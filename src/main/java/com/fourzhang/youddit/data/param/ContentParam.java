package com.fourzhang.youddit.data.param;

import lombok.Data;

@Data
public class ContentParam {
    private Long content_id;
    private Long user_id;
    private String info_describe;
    private String label_id;
    private String image_url;
}
