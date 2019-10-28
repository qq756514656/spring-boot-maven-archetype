package ${package}.util;

/**
 * @author liusy
 */
public interface Constants {
    /**
     * 锁定
     */
    String STATUS_LOCK = "9";

    /**
     * 菜单
     */
    String MENU = "0";

    /**
     * 编码
     */
    String UTF8 = "UTF-8";

    /**
     * JSON 资源
     */
    String CONTENT_TYPE = "application/json; charset=utf-8";

    /**
     * 成功标记
     */
    Integer SUCCESS = 0;
    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 验证码前缀
     */
    String DEFAULT_CODE_KEY = "DEFAULT_CODE_KEY_";

    /**
     * 排序规则： descend 降序
     */

    String ORDER_DESC = "descend";

    /**
     * 排序规则： ascend 升序
     */
    String ORDER_ASC = "ascend";

    /***
     * 资源服务器默认bean名称
     */
    String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";

    String POST = "POST";
    String GET = "GET";
    String PATCH = "PATCH";
    String DELETE = "DELETE";
    String PUT = "PUT";

    String DEV = "dev";
    String TEST = "test";
    String PROD = "prod";
    String USER_ID = "userId";
    String USER_NAME = "userName";
    String REQUEST_ID = "Request-Id";
    String AUTHORIZATION_TOKEN = "Authorization-Token";

    String MOBILE_REQUEST_ID = "Mobile-Request-Id";
    String MOBILE_AUTHORIZATION_TOKEN = "Mobile-Authorization-Token";

    String PRIVATE_TOKEN = "PRIVATE-TOKEN";
    String USER = "user";
    String PROJECT = "/project";
    String USER_DIR = "user.dir";
    String GIT_SYSTEM_USER_NAME = "system";
    String GIT_SYSTEM_EMAIL = "system@demo.com.cn";
    String FILEPATTERN = ".";
    String ORIGIN = "origin";
    String INITIAL_COMMIT = "Initial commit";
    String GIT_DIR = "/.git";
}
