package com.lenovo.ailab.smartedge.filter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lenovo.ailab.smartedge.common.CommonParams;
import com.lenovo.brain.common.crypto.SecureUtil;
import com.lenovo.brain.common.util.JsoupUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
/**
 * Title: PreFilter.java
 * 
 * @Autohr
 * @data 2020/02/06
 * @Email
 * @description:
 */
@WebFilter(urlPatterns = "/*",filterName = "preFilter")
public class PreFilter implements Filter {
    @Value("${token.check-jwt-token}")
    public String CHECK_JWT_TOKEN;
	private static final Logger logger = LoggerFactory.getLogger(PreFilter.class);

	private ObjectMapper objectMapper = new ObjectMapper();

    private void checkJwtToken(String jwtToken,HttpServletResponse response) {
    	String url = CHECK_JWT_TOKEN + "/" + jwtToken;
		logger.info("restTemplate send url is {} "+ url);

		RestTemplate restTemplate = new RestTemplate();
    	String result = restTemplate.getForObject(url, String.class);

		if(!StringUtils.isEmpty(result) && "true".equals(result)){
			String[] strs = jwtToken.split("\\.");
			String jwtJson = new String(Base64.getDecoder().decode(strs[1]));
			try {
				JsonNode jsonNode = objectMapper.readTree(jwtJson);
				String subject = jsonNode.findValue("sub").asText();
				response.setHeader(CommonParams.DID,SecureUtil.aes().encryptBase64(subject));
				response.setStatus(200);
				logger.info("response is {} "+response.toString());
			} catch (IOException e) {
				logger.error("Failed to get developer id from jwt token", e);
				response.setStatus(401);
			}
        } else {
			response.setStatus(401);
        }
	}


	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("preFilter  init ...");
	}

	@Override
	public void doFilter(ServletRequest newRequest, ServletResponse newResponse, FilterChain chain) throws IOException, ServletException {
		logger.info(" preFilter run start  ...");
		// 获取当前请求
		HttpServletRequest request = (HttpServletRequest)newRequest;
		HttpServletResponse response = (HttpServletResponse)newResponse;

        /**
         *  获取摄像头用途类型 部署文件
         */
        if(request.getRequestURI().contains(CommonParams.GET_DEVICE_USE_TYPE)
                || request.getRequestURI().contains(CommonParams.GET_DEVICE_DEPLOYMENT_FILE)
                || request.getRequestURI().contains(CommonParams.REQUEST_SMARTSTORE_GETDEPARTMENT)
                || request.getRequestURI().contains(CommonParams.DEPARTMENT_DEVICE_LIST)
                || request.getRequestURI().contains(CommonParams.DEPARTMENT_DETAIL)  //btit访问接口
                || request.getRequestURI().contains(CommonParams.HEARTBEAT_URL)  //获取心跳机制
                || request.getRequestURI().contains(CommonParams.TOKEN_URL)  //获取Jwttoken 直接访问
                || request.getRequestURI().contains(CommonParams.HEARTBEAT_FOR_CONTROL_SERVER_URL)  //controlServer 服务调用
                || request.getRequestURI().contains(CommonParams.SERVER_STATUS)  //controlServer 服务调用
                || request.getRequestURI().contains(CommonParams.NO_TOKEN)  //摄像头部署图运营侧获取
                || request.getRequestURI().contains(CommonParams.GET_ALL_DEVICESTATUS)  //摄像头部署图运营侧获取
                || request.getRequestURI().contains(CommonParams.UPDATE_INSTALL_STATUS)  //摄像头部署图运营侧获取
                || request.getRequestURI().contains(CommonParams.GET_DEVICE_DETAIL)  //controlServer 服务init
                || request.getRequestURI().contains(CommonParams.NO_TOKEN_DEPARTMENTS) //运营侧直接访问
                || request.getRequestURI().contains(CommonParams.NO_TOKEN_EDGEDEVICES) //运营侧直接访问
                || request.getRequestURI().contains(CommonParams.HEARTBEAT_NO_TOKEN_URL) //更新tx2配置
                || request.getRequestURI().contains(CommonParams.STATIC_JPG)){ //静态文件直接访问
            chain.doFilter(request, response);
            return;
        }

        if (CommonParams.ADVANCE_REQUEST.equals(request.getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
            // 获取当前请求
            if(response != null){
                String origin = request.getHeader("Origin");
                if(origin == null || origin.contains("\n")){
                    logger.info("request origin is null");
                    response.setStatus(412);
                    return ;
                }
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                response.setHeader("Access-Control-Allow-Origin", JsoupUtil.clean(origin));//解决跨域问题
                /***
                 * 特殊的header需要定义,比如浏览器支持的原生的Content-Type是application/x-www-from-urlencoded
                 * ,text/plain,multipart/form-data
                 * 而angular的默认content-type是application/json
                 * 所以服务器有允许我设置自定义的content-type这个请求头
                 * 所以响应的时候要设置
                 * response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, "
                 + "X-Requested-With, If-Modified-Since, Pragma, Last-Modified,"
                 + " Cache-Control, Expires, Content-Type, X-E4M-With");
                 */
                response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, "
                        + "X-Requested-With, If-Modified-Since, Pragma, Last-Modified,"
                        + " Cache-Control, Expires, Content-Type, X-E4M-With, jwttoken");
                response.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,PATCH,OPTIONS");
                response.setHeader("Access-Control-Allow-Credentials", "true");

                /**
                 * Access-Control-Max-Age 这个响应首部表示 preflight request
                 * （预检请求）的返回结果（即 Access-Control-Allow-Methods
                 * 和Access-Control-Allow-Headers 提供的信息） 可以被缓存多久。
                 */
                response.setHeader("Access-Control-Max-Age", "6000");
            }
            chain.doFilter(request, response);
            return;
        }

        Enumeration<String> headerNames = request.getHeaderNames();//所有头
        Map<String, String> map = new HashMap<String, String>();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            // 排除Cookie字段
            if (key.equalsIgnoreCase("Cookie")) {
                continue;
            }
            String value = request.getHeader(key);
            map.put(key, value);
        }

        String jwtToken = request.getHeader(CommonParams.JWTTOKEN) == null ? request.getParameter(CommonParams.JWTTOKEN)
                : request.getHeader(CommonParams.JWTTOKEN);
        logger.info("request jwt token: {}", jwtToken);
        logger.info("request headerNames:{} ",map.toString());
        if (StringUtils.isEmpty(jwtToken)|| CommonParams.JWTTOKEN_VALUE.equals(jwtToken)) {
            response.setStatus(401);
        }else{
            checkJwtToken(jwtToken, (HttpServletResponse) response);
            if(response.getStatus() != 401){
                chain.doFilter(request, response);
            }
        }
	}

	@Override
	public void destroy() {
		logger.info("preFilter destroy ...");
	}
}
