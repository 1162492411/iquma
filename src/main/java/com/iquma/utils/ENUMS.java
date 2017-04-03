package com.iquma.utils;

/**
 * Created by Mo on 2017/3/30.
 */
public class ENUMS {

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
    public static final int PRESTIGE_RID_4 = 2000;//4级账户--王者账户

    //版块ID
    public static final String SECTION_TUTORIAL = "tutorial";//教程
    public static final String SECTION_DISCUSS = "discuss";//提问
    public static final String SECTION_ARTICLE = "article";//经验
    public static final String SECTION_CODE = "code";//代码

    
}
