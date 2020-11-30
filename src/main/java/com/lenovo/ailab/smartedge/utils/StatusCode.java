package com.lenovo.ailab.smartedge.utils;

import java.util.HashMap;
import java.util.Map;

public class StatusCode {

    private static Map<Integer, String[]> dict = new HashMap<>();

    public static final Integer HTTP_OK = 0;
    public static final Integer UNKOWN_ERROR = 1;
    public static final Integer SERVER_UNAVAILABLE = 2;
    public static final Integer UNSUPPORT_API = 3;
    public static final Integer REQUEST_LIMITED = 4;
    public static final Integer UNAUTHORIZED_IP = 5;
    public static final Integer NO_PERMISSION_TO_ACCESS = 6;
    public static final Integer NO_PERMISSION_REFER = 7;

    public static final Integer INVALID_PARAMETER = 100;
    public static final Integer INVALID_API_KEY = 101;
    public static final Integer INVALID_SESSION_KEY = 102;
    public static final Integer INVALID_CALL_ID = 103;
    public static final Integer INCORRECT_SIGNATURE = 104;
    public static final Integer TOO_MANY_PARAMETES = 105;
    public static final Integer UNSUPPORTED_SIGNATURE = 106;
    public static final Integer INVALID_TIMESTAMP_PARAM = 107;
    public static final Integer INVALID_UISER_ID = 108;
    public static final Integer INVALID_USER_INFO = 109;
    public static final Integer INVALID_TOKEN = 110;
    public static final Integer TOKEN_EXPIRED = 111;
    public static final Integer SESSION_EXPIRED = 112;
    public static final Integer INVALID_IP = 114;
    public static final Integer USER_NOT_VISIBLE = 210;
    public static final Integer UNSUPPORTED_PERMISSION = 211;
    public static final Integer NO_PERMISSION_TO_EMAIL = 212;

    public static final Integer UNKOWN_DATA_STORE_ERROR = 800;
    public static final Integer INVALID_OPERATION = 801;
    public static final Integer STORE_ALLOWABLE = 802;
    public static final Integer OBJECT_CANNOT_BE_FOUND = 803;
    public static final Integer OBJECT_EXISTED = 804;
    public static final Integer DATABASE_ERROR = 805;

    public static final Integer NO_SUCH_APPLICATION = 900;
    public static final Integer BATCH_LIMIT_REACHED=952;
    //baidu ai error_no
    public static final Integer CLUSTER_EXCEEDING_QUOTA = 24;
    public static final Integer IAM_CERTIFICATION_FAILED = 14;
    public static final Integer DAILY_LIMIT_REACHED = 17;
    public static final Integer QPS_LIMIT_REACHED=18;
    public static final Integer BACKEND_ERROR = 503;
    public static final Integer AUDIO_TOO_POOR = 3301;
    public static final Integer VOICE_SERVER_PROBLEM = 3303;
    public static final Integer REQUEST_QPS_OVERRUN = 3304;
    public static final Integer AUDIO_OVERLENGTH = 3308;
    public static final Integer AUDIO_DATA_PROBLEM = 3309;
    public static final Integer FILE_TOO_LARGE = 3310;
    public static final Integer SAMPLING_RATE_ERROR = 3311;
    public static final Integer AUDIO_FORMAT_ERROR = 3312;
    public static final Integer MODULE_CLOSED=216015;

    public static final Integer FORMAT_ERROR=216201;
    public static final Integer RECOGNIZE_ERROR=216630;
    public static final Integer NUMBER_OF_IMAGE_INCORRECT=222208;
    public static final Integer GET_FACE_FAIL=222301;
    public static final Integer SYSTEM_BUSYS=222901;
    public static final Integer GROUP_LIST_TOO_LARGE=223104;
    public static final Integer APP_LIST_TOO_LARGE=223117;
    public static final Integer QUALITY_CONTROL_ERROR=223118;
    public static final Integer LIVENESS_CONTROL_ERROR=223119;
    public static final Integer CHECK_OCCLUSION=223124;
    public static final Integer INTERNAL_ERROR=282000;
    public static final Integer BATCH_PROCESSING_ERROR=282005;
    public static final Integer REQUEST_TIMEOUT=282112;
    public static final Integer ENCODING_ERROR=282002;
    public static final Integer NO_RESULT=282130;
    public static final Integer INPUT_TOO_LONG=282131;
    public static final Integer IMAGE_FRAME_ERROR=282203;
    public static final Integer MATCH_USER_NOT_FOUND=222207;
    public static final Integer WORD_ERROR=282300;

    static {
        dict.put(0, new String[]{"成功", "Success"});
        dict.put(1, new String[]{"未知错误", "Unknown error"});
        dict.put(2, new String[]{"服务暂不可用", "Service temporarily unavailable"});
        dict.put(3, new String[]{"未知的方法", "Unsupported openapi method"});
        dict.put(4, new String[]{"接口调用次数已达到设定的上限", "Open api request limit reached"});
        dict.put(5, new String[]{"请求来自未经授权的IP地址", "Unauthorized client IP address:%s"});
        dict.put(6, new String[]{"无权限访问该用户数据", "No permission to access data"});
        dict.put(7, new String[]{"来自该refer的请求无访问权限", "No permission to access data for this referer"});
        dict.put(100, new String[]{"请求参数无效", "Invalid parameter"});
        dict.put(101, new String[]{"api key无效", "Invalid API key"});
        dict.put(102, new String[]{"session key无效", "Session key invalid or no longer valid"});
        dict.put(103, new String[]{"call_id参数无效", "Invalid/Used call_id parameter"});
        dict.put(104, new String[]{"无效签名", "Incorrect signature"});
        dict.put(105, new String[]{"请求参数过多", "Too many parameters"});
        dict.put(106, new String[]{"未知的签名方法", "Unsupported signature method"});
        dict.put(107, new String[]{"timestamp参数无效", "Invalid/Used timestamp parameter"});
        dict.put(108, new String[]{"无效的user id", "Invalid user id"});
        dict.put(109, new String[]{"无效的用户资料字段名", "Invalid user info field"});
        dict.put(110, new String[]{"无效的access token", "Access token invalid or no longer valid"});
        dict.put(111, new String[]{"access token过期", "Access token expired"});
        dict.put(112, new String[]{"session key过期", "Session key expired"});
        dict.put(114, new String[]{"无效的ip参数", "Invalid Ip"});
        dict.put(210, new String[]{"用户不可见", "User not visible"});
        dict.put(211, new String[]{"获取未授权的字段", "Unsupported permission"});
        dict.put(212, new String[]{"没有权限获取用户的email", "No permission to access user email"});

        dict.put(800, new String[]{"未知的存储操作错误", "Unknown data store API error"});
        dict.put(801, new String[]{"无效的操作方法", "Invalid operation"});
        dict.put(802, new String[]{"数据存储空间已超过设定的上限", "Data store allowable quota was exceeded"});
        dict.put(803, new String[]{"指定的对象不存在", "Specified object cannot be found"});
        dict.put(804, new String[]{"指定的对象已存在", "Specified object already exists"});
        dict.put(805, new String[]{"数据库操作出错，请重试", "A database error occurred. Please try again"});
        dict.put(900, new String[]{"访问的应用不存在", "No such application exists"});
        dict.put(950, new String[]{"批量操作已开始，请先调用end_batch接口结束前一个批量操作",
                "begin_batch already called, please make sure to call end_batch first"});
        dict.put(951, new String[]{"结束批量操作的接口调用不应该在start_batch接口之前被调用", "end_batch called before start_batch"});
        dict.put(952, new String[]{"每个批量调用不能包含多于20个接口调用", "Each batch API can not contain more than 20 items"});
        dict.put(953, new String[]{"该接口不适合在批量调用操作中被使用", "Method is not allowed in batch mode"});

        //baidu ai error_no


        dict.put(24, new String[]{"集群超限额", "Cluster exceeding quota"});
        dict.put(14, new String[]{"IAM鉴权失败", "IAM Certification failed"});
        dict.put(17, new String[]{"每天流量超限额", "Open api daily request limit reached"});
        dict.put(18, new String[]{"QPS超限额", "Open api qps request limit reached"});
        dict.put(503, new String[]{"合成后端错误", "Backend error"});
        dict.put(3301, new String[]{"音频质量过差", "Audio quality is too poor."});
        dict.put(3303, new String[]{"语音服务器后端问题", "Voice Server Backend Problem."});
        dict.put(3304, new String[]{"用户的请求QPS超限", "User's Request QPS Overrun."});
        dict.put(3308, new String[]{"音频过长", "Audio overlength."});
        dict.put(3309, new String[]{"音频数据问题", "Audio data problem."});
        dict.put(3310, new String[]{"文件过大", "File is too large."});
        dict.put(3311, new String[]{"采样率错误", "Sampling rate error."});
        dict.put(3312, new String[]{"音频格式错误", "Audio format erro."});
        dict.put(216015, new String[]{"模块关闭", "Module closed."});

        dict.put(216201, new String[]{"参数格式错误", "Format error."});
        dict.put(222207, new String[]{"未找到匹配的用户", "Match user is not found."});
        dict.put(222208, new String[]{"图片的数量错误", "The number of image is incorrect."});
        dict.put(222301, new String[]{"获取人脸图片失败", "Get face fail."});
        dict.put(222901, new String[]{"系统繁忙", "System busy."});
        dict.put(223117, new String[]{"app_list包含app数量过多", "App_list is too large."});
        dict.put(223118, new String[]{"质量控制项错误", "Quality control error."});
        dict.put(223119, new String[]{"活体控制项错误", "Liveness control item error."});
        dict.put(223124, new String[]{"质量检测未通过", "All cheek is occlusion."});
        dict.put(223104, new String[]{"group_list包含组数量过多", "Group_list is too large."});
        dict.put(216630, new String[]{"识别错误", "Recognize error."});
        dict.put(282000, new String[]{"服务器内部错误", "Internal error."});
        dict.put(282005, new String[]{"处理批量任务时发生部分或全部错误", "Batch  processing error."});
        dict.put(282112, new String[]{"请求超时", "Request timeout."});
        dict.put(282002, new String[]{"编码错误", "Encoding error."});
        dict.put(282130, new String[]{"无结果返回", "No result."});
        dict.put(282131, new String[]{"输入长度超限", "Input too long."});
        dict.put(282203, new String[]{"gif帧数超限", "Image frame size error."});

        dict.put(282300, new String[]{"word不在算法词典中", "word error."});
    }

    private static String[] get(Integer code) {
        if (dict.containsKey(code))
            return dict.get(code);
        return new String[2];
    }

    public static String getMessage(Integer code) {
        return getMessage(code, null);
    }

    public static String getMessage(Integer code, String language) {
        if (language == null || language.equals("en-us") || language.equals("english"))
            return get(code)[1];
        return get(code)[0];
    }

    public static class Custom {
        public static Integer getCV(Integer offset) {
            return getCode("cv", offset);
        }

        public static Integer getVoice(Integer offset) {
            return getCode("voice", offset);
        }

        public static Integer getNlp(Integer offset) {
            return getCode("nlp", offset);
        }

        public static Integer getKG(Integer offset) {
            return getCode("kg", offset);
        }

        public static Integer getCA(Integer offset) {
            return getCode("ca", offset);
        }

        public static Integer getML(Integer offset) {
            return getCode("ml", offset);
        }

        private static Integer getCode(String type, Integer offset) {
            if (offset == null)
                offset = 0;
            else if (offset > 99)
                offset = 99;
            Integer base = 1100;
            switch (type) {
                case "voice":
                    base = 1200;
                case "nlp":
                    base = 1300;
                case "kg":
                    base = 1400;
                case "ca":
                    base = 1500;
                case "ml":
                    base = 1600;
            }
            return base + offset;
        }
    }
}
