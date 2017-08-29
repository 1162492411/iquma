package com.iquma.test.utils;


public class ENUMS {

    //测试用账户ID
    public static final String TEST_SUPERADMIN_USERID = "541413140001";//测试用超管账户
    public static final String TEST_ADMIN_USERID = "541413140002";//测试用管理员账户
    public static final String TEST_TEACHER_USERID = "541413140003";//测试用教师账户
    public static final String TEST_DIAMOND_USERID = "541413140004";//测试用钻石账户
    public static final String TEST_PLATNUM_USERID = "541413140005";//测试用白金账户
    public static final String TEST_GOLD_USERID = "541413140006";//测试用黄金账户
    public static final String TEST_SILVER_USERID = "541413140007";//测试用白银账户
    public static final String TEST_BRONZE_USERID = "541413140008";//测试用青铜账户
    public static final String TEST_BLOCKED_USERID = "541401030101";//测试用封禁账户
    public static final String TEST_DOESNOTEXIST_USERID = "112233445566";//测试用不存在账户

    //威望增加/减少
    public static final int PRESTIGE_INSERT_DISUCSS = 1;//发布提问
    public static final int PRESTIGE_INSERT_ARTICLE = 2;//发布经验
    public static final int PRESTIGE_INSERT_CODE = 3;//上传代码
    public static final int PRESTIGE_INSERT_REPLY = 1;//发表回复
    public static final int PRESTIGE_BE_LIKED = 2;//问题/经验/代码/回复被赞同
    public static final int PRESTIGE_BE_HATED = -2;//问题/经验/代码/回复被反对
    public static final int PRESTIGE_BE_BLOCKED = -3;//问题/经验/代码/回复被折叠
    public static final int PRESTIGE_BE_DELETED = -5;//问题/经验/代码/回复被删除
    public static final int PRESTIGE_BE_ADOPTED = 10;//回复被采纳

    //角色对应的威望要求
    public static final int PRESTIGE_RID_7 = 100;//7级账户--白银账户
    public static final int PRESTIGE_RID_6 = 300;//6级账户--黄金账户
    public static final int PRESTIGE_RID_5 = 800;//5级账户--白金账户
    public static final int PRESTIGE_RID_4 = 2000;//4级账户--钻石账户

    //各级角色的ID
    public static final int ROLE_SUPERADMIN = 1;//超级管理员账户
    public static final int ROLE_ADMIN = 2;//管理员账户
    public static final int ROLE_TEACHER = 3;//教师账户
    public static final int ROLE_DIAMOND = 4;//钻石账户
    public static final int ROLE_PLATNUM = 5;//白金账户
    public static final int ROLE_GOLD = 6;//黄金账户
    public static final int ROLE_SILVER = 7;//白银账户
    public static final int ROLE_BRONZE = 8;//青铜账户

    //版块ID
    public static final String SEC_TUTORIAL = "tutorial";//教程
    public static final String SEC_QUESTION = "question";//提问
    public static final String SEC_ARTICLE = "article";//经验
    public static final String SEC_CODE = "code";//代码

    //高评分主贴标准
    public static final byte HIGH_RATE_COUNT = 8;

    //主贴
    public static final int TOPIC_DEFAULT_VIEWCOUNT = 0;//默认浏览量
    public static final double TOPIC_DEFAULT_LIKECOUNT = 0;//默认权重赞同值
    public static final double TOPIC_DEFAULT_HATECOUNT = 0;//默认权重反对值
    public static final byte TOPIC_DEFAULT_RATECOUNT = 0;//默认权重主贴评分
    public static final int TOPIC_DEFAULT_REPLYCOUNT = 0;//默认回复数
    public static final int TOPIC_DEFAULT_ATTID = 0;//默认主贴附件ID
    public static final boolean TOPIC_DEFAULT_ISBLOCK = false;//默认是否被关闭
    public static final int TOPIC_DEFAULT_HASBEST = 0;//默认最佳回复ID
    public static final boolean TOPIC_DEFAULT_NOREPLY = true;//默认是否存在回复
    public static final boolean TOPIC_DEFAULT_ISHIGH = false;//默认是否是高评分主贴

}
