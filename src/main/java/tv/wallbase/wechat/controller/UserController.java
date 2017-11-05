package tv.wallbase.wechat.controller;

import tv.wallbase.wechat.rest.ResponseData;
import tv.wallbase.wechat.service.UserService;
import tv.wallbase.wechat.service.WechatOAuth2Service;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Map;

/**
 * 员工管理相关
 * Created by wangkun23 on 2017/6/20.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @Resource
    private WechatOAuth2Service wechatOAuth2Service;

    /**
     * 给微信公众号调用获取JSAPITIecket
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/get_ticket", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData getTicket(HttpServletRequest request, String url) {
        if (StringUtils.isEmpty(url)) {
            url = request.getRequestURL().toString();
        } else {
            //前端已经做encode
            url = URLDecoder.decode(url);
        }
        logger.info("getTicket url {}", url);
        ResponseData result = ResponseData.success("200");
        Map<String, Object> data = wechatOAuth2Service.sign(url);
        logger.info("signature {}", data);

        result.setData(data);
        return result;
    }
}
