package tv.wallbase.wechat.interceptor;

import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import tv.wallbase.wechat.common.Constants;

/**
 * 拦截用户未从微信登录访问页面获取数据
 *
 * @author wangkun23 2017/6/20.
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    /**
     * 登录URL
     */
    private String loginUrl = "/oauth2/login";

    /**
     * 拦截器
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        //如果开启开发者模式 不验证微信登录
        //if (appConfigBean.isDevMode()) {
            //return true;
        //}
        String openid = (String) session.getAttribute(Constants.WEINXIN_OPENID_ATTRIBUTE_NAME);
        if (StringUtils.isEmpty(openid)) {
            String requestType = request.getHeader("X-Requested-With");
            //ajax 请求
            if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest")) {
                logger.warn("不能直接访问,必须通过微信登录后才能访问,");
                response.addHeader("loginStatus", "accessDenied");
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return false;
            } else {
                if (request.getMethod().equalsIgnoreCase("GET")) {
                    String redirectUrl = request.getQueryString() != null ? request.getRequestURI() + "?" + request.getQueryString() : request.getRequestURI();
                    response.sendRedirect(request.getContextPath() + loginUrl + "?redirectUrl=" + URLEncoder.encode(redirectUrl, "UTF-8"));
                } else {
                    //response.sendRedirect(request.getContextPath() + loginUrl);
                }
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
