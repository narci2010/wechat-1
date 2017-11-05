package tv.wallbase.wechat.rest;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户ajax提交之后的相应结果
 *
 * @author wangkun23
 */
public class ResponseData implements Serializable {

    /**
     * 类型
     */
    private ResponseMessageStatus status;

    /**
     * 结果Code
     */
    private String code;

    /**
     * 结果message
     */
    private String message;

    /**
     * 额外属性集
     */
    private Map<String, Object> data;

    /**
     * 初始化一个新创建的 Message 对象，使其表示一个空消息。
     */
    public ResponseData() {

    }

    /**
     * 初始化一个新创建的 Message 对象
     *
     * @param status 类型
     * @param code   内容
     */
    public ResponseData(ResponseMessageStatus status, String code) {
        this.status = status;
        this.code = code;
    }

    /**
     * 返回成功消息
     *
     * @param code 内容
     * @return 成功消息
     */
    public static ResponseData success(String code) {
        return new ResponseData(ResponseMessageStatus.SUCCESS, code);
    }

    public static ResponseData success(String code,String message){
        ResponseData result = new ResponseData(ResponseMessageStatus.SUCCESS, code);
        result.setMessage(message);
        return result;
    }

    /**
     * 返回警告消息
     *
     * @param code 内容
     * @return 警告消息
     */
    public static ResponseData warn(String code) {
        return new ResponseData(ResponseMessageStatus.WARN, code);
    }

    public static ResponseData warn(String code , String message) {
        ResponseData result = new ResponseData(ResponseMessageStatus.WARN, code);
        result.setMessage(message);
        return result;
    }

    /**
     * 返回错误消息
     *
     * @param code 内容
     * @return 错误消息
     */
    public static ResponseData error(String code) {
        return new ResponseData(ResponseMessageStatus.ERROR, code);
    }

    public static ResponseData error(String code,String message) {
        ResponseData result = new ResponseData(ResponseMessageStatus.ERROR, code);
        result.setMessage(message);
        return result;
    }

    /**
     * 获取类型
     *
     * @return 类型
     */
    public ResponseMessageStatus getStatus() {
        return status;
    }

    /**
     * 设置类型
     *
     * @param status 类型
     */
    public void setStatus(ResponseMessageStatus status) {
        this.status = status;
    }

    /**
     * 获取内容
     *
     * @return 内容
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置内容
     *
     * @param code 内容
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取额外属性集
     *
     * @return
     */
    public Map<String, Object> getData() {
        return data;
    }

    /**
     * 设置额外属性集
     *
     * @param data
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 添加额外属性
     *
     * @param key
     * @param value
     */
    public void addData(String key, Object value) {
        if (this.data == null) {
            this.data = new LinkedHashMap<String, Object>();
        }
        this.data.put(key, value);
    }

    /**
     * 移除额外属性
     *
     * @param key
     * @return
     */
    public Object removeData(String key) {
        return this.data != null ? this.data.remove(key) : null;
    }
}
