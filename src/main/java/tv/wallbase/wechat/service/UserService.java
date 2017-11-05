package tv.wallbase.wechat.service;

import tv.wallbase.wechat.model.User;

/**
 * 用户管理相关
 * Created by wangkun23 on 2017/6/18.
 */
public interface UserService {

    public User findByOpenid(String openid);
}
