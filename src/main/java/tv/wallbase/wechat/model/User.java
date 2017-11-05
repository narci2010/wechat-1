package tv.wallbase.wechat.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by wangkun23 on 2017/3/28.
 */

public class User implements Serializable {

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String mobile;
    /**
     * 微信开放平台授权之后返回的微信唯一ID
     */
    @Getter
    @Setter
    private String openid;
}
