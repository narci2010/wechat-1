package tv.wallbase.wechat.handler;

import tv.wallbase.wechat.rest.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.remoting.RemoteConnectFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 针对所有rest接口异常处理
 * Created by wangkun23 on 2017/7/6.
 */
@RestControllerAdvice
public class RestControllerExceptionHandler {
    final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData defaultErrorHandler(HttpServletRequest request, Exception ex) {
        logger.info("url {}", request.getRequestURL());
        logger.error("{}", ex);
        return ResponseData.error("500", ex.getMessage());
    }


    /**
     * 目前只有hessian抛出RemoteConnectFailureException
     *
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {RemoteConnectFailureException.class})
    public ResponseData defaultRemoteConnectFailureHandler(HttpServletRequest request, Exception ex) {
        logger.info("url {}", request.getRequestURL());
        logger.error("{}", ex);
        return ResponseData.error("500", "运营中心服务接口调用失败,请联系管理员");
    }
}
