package tv.wallbase.wechat.service.impl;

import tv.wallbase.wechat.model.User;
import tv.wallbase.wechat.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 用户管理
 * Created by wangkun23 on 2017/6/19.
 */
@Service
public class UserServiceImpl implements UserService {

    public void save(User user) {
        Assert.notNull(user);
    }

    @Override
    public User findByOpenid(String openid) {
        User user = new User();
        user.setId(1l);
        user.setOpenid("test");
        user.setMobile("");
        return user;
    }
}
