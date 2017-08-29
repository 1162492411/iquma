package com.iquma.controller;

import com.iquma.pojo.*;
import com.iquma.service.*;
import com.iquma.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private FavoriteService favoriteService;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private UserHelper userHelper;
    public String result;
    private static int oldCollectionCount = -1;//存储旧收藏总数
    private static String oldCollectionUid = "-1";//存储收藏条件的uid

    /**
     * 前往登录页面
     */
    @RequiresGuest
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    /**
     * 验证账户登录
     * @param user 用于存储用户信息
     * @param request 日志记录时从中提取所需信息
     * @return 默认返回网站首页
     * @throws ShiroException 验证账户密码时抛出异常，由全局异常处理器处理
     * TODO :该方法可以改造为返回json信息，登录失败时显示回传的失败提示信息，登录成功时跳转到登陆前页面(自定义shiro拦截器)
     */
    @RequestMapping(value = "loginValidator")
    public String loginValidator(User user, HttpServletRequest request) throws ShiroException {
        if(UserHelper.getCurrentUserId() == null) {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getId(), user.getPass());
            subject.login(token);//会跳到自定义的realm中以校验身份,授权
            user = userService.selectSimpleUser(user.getId());//成功验证后获取用户数据
            Session session = subject.getSession();
            session.setAttribute("userid", user.getId());//将用户数据绑定到session中
            session.setAttribute("username", user.getName());
            session.setAttribute("useravatar", user.getAvatar());
            Notification condition = new Notification();
            condition.setUid(user.getId());//查询用户是否存在未读通知
            condition.setIsnew(Boolean.TRUE);
            int ntfCount = notificationService.selectsCount(condition);
            if (ntfCount > 0)//用户存在未读通知时绑定未读通知数量到session中
                session.setAttribute("ntfCount", ntfCount);
        }
        return "index";
    }

    /**
     * 前往用户个人主页
     * @param uid 待查看的用户id
     * @param model model
     * @param condition condition
     */
    @RequestMapping(value = "{uid}/home", method = RequestMethod.GET)
    public String toHome(@PathVariable String uid, Model model, Reply condition) {
        return toAnswersByPage(uid,1,model,condition);
    }

    /**
     * 获取简短的主贴集合
     * @param topics 从数据库获取的主贴集合
     * @return 修改后的主贴集合,主贴内容仅保留前300个字符
     */
    public List<Topic> getSimpleTopics(List<Topic> topics){
        for (Topic topic: topics) {
            String content;
            if((content= topic.getContent()) != null){
                String temp = content.substring(0,content.length() >= 300?300:content.length());//首先截取前300个字符
                if(temp.contains("<pre>") && ! temp.contains("</pre>"))
                    temp = temp + "</code></pre>";//若未结束代码则结束代码
                topic.setContent(temp);
            }
        }
        return topics;
    }

    /**
     * 前往用户相关主贴列表--通用
     * @param aid 待查看用户的id
     * @param section 待查看的版块--tutorials/discusses/articles/codes
     * @param page 页数，分页查看主贴所用的页数
     */
    private String toList(String aid,String section,int page,Topic topic,Model model){
        User user = userService.selectSimpleUser(aid);
        topic.setAid(aid);
        topic.setSec(section);
        int totalPage = PageUtil.getTotalPage(topicService.selectsCount(topic));
        int currentPage = PageUtil.getCurrentPage(page,totalPage);
        model.addAttribute("sec",section);//绑定结果
        model.addAttribute("topics",GsonUtil.toJson(getSimpleTopics(topicService.selectsByPage(currentPage,topic))));
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("user",user);
        return "user/lists";
    }

    /**
     * 前往用户的教程列表
     * @param uid 待查看用户的id
     */
    @RequestMapping(value = "{uid}/tutorials", method = RequestMethod.GET)
    public String toTutorials(@PathVariable String uid,Model model, Topic topic) {
        return toList(uid,ENUMS.SEC_TUTORIAL,1,topic,model);
    }

    /**
     * 前往用户的教程列表--按分页
     * @param uid 待查看用户的id
     * @param page 页数
     */
    @RequestMapping(value = "{uid}/tutorials/{page}", method = RequestMethod.GET)
    public String toTutorialsByPage(@PathVariable String uid,@PathVariable int page, Model model, Topic topic) {
        return toList(uid,ENUMS.SEC_TUTORIAL,page,topic,model);
    }

    /**
     * 前往用户的提问列表
     * @param uid 带查看用户的id
     */
    @RequestMapping(value = "{uid}/questions", method = RequestMethod.GET)
    public String toAsks(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,ENUMS.SEC_QUESTION,1,topic,model);
    }

    /**
     * 前往用户的提问列表--按分页
     * @param uid 带查看用户的id
     * @param page 页数
     */
    @RequestMapping(value = "{uid}/questions/{page}", method = RequestMethod.GET)
    public String toAsksByPage(@PathVariable String uid,@PathVariable int page, Model model, Topic topic) {
        return toList(uid,ENUMS.SEC_QUESTION,page,topic,model);
    }

    /**
     * 前往用户的文章列表
     * @param uid 带查看用户的id
     */
    @RequestMapping(value = "{uid}/articles", method = RequestMethod.GET)
    public String toArticles(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,ENUMS.SEC_ARTICLE,1,topic,model);
    }

    /**
     * 前往用户的文章列表--按分页
     * @param uid 带查看用户的id
     * @param page 页数
     */
    @RequestMapping(value = "{uid}/articles/{page}", method = RequestMethod.GET)
    public String toArticlesByPage(@PathVariable String uid,@PathVariable int page,Model model, Topic topic) {
        return toList(uid,ENUMS.SEC_ARTICLE,page,topic,model);
    }

    /**
     * 前往用户的代码列表
     * @param uid 带查看用户的id
     */
    @RequestMapping(value = "{uid}/codes", method = RequestMethod.GET)
    public String toCodes(@PathVariable String uid, Model model, Topic topic) {
        return toList(uid,ENUMS.SEC_CODE,1,topic,model);
    }

    /**
     * 前往用户的代码列表--按分页
     * @param uid 带查看用户的id
     * @param page 页数
     */
    @RequestMapping(value = "{uid}/codes/{page}", method = RequestMethod.GET)
    public String toCodesByPage(@PathVariable String uid, @PathVariable int page, Model model, Topic topic) {
        return toList(uid,ENUMS.SEC_CODE,page,topic,model);
    }

    /**
     * 前往用户的回答列表--按分页
     * @param uid 待查看用户的id
     * @param page 页数
     * @param condition 条件参数
     */
    @RequestMapping(value = "{uid}/answers/{page}", method = RequestMethod.GET)
    public String toAnswersByPage(@PathVariable String uid,@PathVariable int page, Model model,Reply condition) {
        User user = userService.selectSimpleUser(uid);
        int totalPage = PageUtil.getTotalPage(replyService.selectsCount(condition));
        int currentPage = PageUtil.getCurrentPage(page,totalPage);
        model.addAttribute("user",user);
        model.addAttribute("replies",GsonUtil.toJson(replyService.selectsByPage(currentPage,condition)));
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
        return "user/answers";
    }

    /**
     * 前往用户的回答列表
     * @param uid 待查看用户的id
     */
    @RequestMapping(value = "{uid}/answers", method = RequestMethod.GET)
    public String toAnswers(@PathVariable String uid, Model model,Reply condition) {
        return toAnswersByPage(uid,1,model,condition);
    }

    /**
     * 前往用户的收藏列表--按分页
     * @param uid 待查看用户的id
     * @param page 页数
     */
    @RequestMapping(value = "{uid}/collections/{page}", method = RequestMethod.GET)
    public String toCollectionsByPage(@PathVariable String uid,@PathVariable int page,Favorite favorite, Model model) {
        User user = this.userService.selectSimpleUser(uid);
        int totalPage = PageUtil.getTotalPage(favoriteService.selectsCount(favorite));
        int currentPage = PageUtil.getCurrentPage(page,totalPage);
        model.addAttribute("user",user);
        model.addAttribute("collections",GsonUtil.toJson(favoriteService.selectsByPage(currentPage,favorite)));
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
//        oldCollectionUid = uid;
        return "user/collections";
    }

    /**
     * 前往用户的收藏列表
     * @param uid 待查看用户的id
     */
    @RequestMapping(value = "{uid}/collections", method = RequestMethod.GET)
    public String toCollections(@PathVariable String uid, Integer page,Favorite favorite, Model model) {
        return toCollectionsByPage(uid,1,favorite,model);
    }

    //获取用户的收藏总数
    //TODO:该方法可以减少sql频率，但可能与sql缓存发生冲突，因此待定
//    private int getOldCollectionCount(Favorite condition){
//        if(!oldCollectionUid.equals(condition.getUid())) oldCollectionCount = favoriteService.selectsCount(condition);
//        return oldCollectionCount % 10 == 0 ? oldCollectionCount / 10 : oldCollectionCount / 10 + 1;
//    }

    /**
     * 前往个人资料页面
     */
    @RequiresUser
    @RequestMapping(value = "{diu}/profile", method = RequestMethod.GET)
    public String toProfile(Model model) {
        User user = this.userService.selectSimpleUser(UserHelper.getCurrentUserId());//为保证安全从session中取值
        model.addAttribute("user", user);
        return "user/profile";
    }

    /**
     * 修改个人资料验证
     * @param record 待存储的用户资料
     * @return (json)是否成功存储用户资料并修改session中的对应信息
     */
    @RequiresUser
    @RequestMapping(value = "{diu}/profile", method = RequestMethod.PUT)
    public @ResponseBody Boolean profileValidator(@RequestBody User record) {
        record.setId(UserHelper.getCurrentUserId());
        if (userService.update(record)){//成功修改用户资料后修改session中的useravatar的值(bannar.jsp访问该值)
            SecurityUtils.getSubject().getSession().setAttribute("useravatar",record.getAvatar());
            return Boolean.TRUE;
        }
        else return Boolean.FALSE;
    }

    //前往修改密码页面
    @RequiresUser
    @RequestMapping(value = "{diu}/account", method = RequestMethod.GET)
    public String toAccount( Model model) {
        User user = this.userService.selectSimpleUser(UserHelper.getCurrentUserId());
        model.addAttribute("user", user);
        return "user/account";
    }

    /**
     * 修改密码验证
     * 注 : 修改密码时将新密码绑定在邮箱字段中,将旧密码保存在密码字段中
     * @param record 条件参数
     * @return (json)是否成功保存修改后的密码
     */
    @RequiresUser
    @RequestMapping(value = "{diu}/account", method = RequestMethod.PUT)
    public @ResponseBody Boolean accountValidator(@RequestBody User record) {
        User oldUser = userService.selectDetailUser(UserHelper.getCurrentUserId());
        record.setId(UserHelper.getCurrentUserId());
        record.setSalt(oldUser.getSalt());
        passwordHelper.encryptPassword(record);
        if(record.getPass().equals(oldUser.getPass())){
            record.setPass(record.getEmail());//验证密码后将新密码保存在密码字段中并加密
            record.setEmail(null);
            passwordHelper.resetSalt(record);
            passwordHelper.encryptPassword(record);
            return userService.update(record);
        }
        return false;
    }

    /**
     * 退出登录
     */
    @RequiresUser
    @RequestMapping(value = "{diu}/logout")
    public String logout(){
        SecurityUtils.getSubject().logout();
        Session session = SecurityUtils.getSubject().getSession();
        session.removeAttribute("userid");
        session.removeAttribute("username");
        session.removeAttribute("useravatar");
        return "index";
    }

    /**
     *  前往用户通知页面
     *  @param condition 条件参数
     *  @param page 页数
     *  @return 通知页面
     */
    @RequiresUser
    @RequestMapping(value = "{uuuid}/ntfs/{page}", method = RequestMethod.GET)
    public String toNotificationsByPage(Notification condition,@PathVariable int page, Model model) {
        condition.setUid(UserHelper.getCurrentUserId());
        int totalPage = PageUtil.getTotalPage(notificationService.selectsCount(condition));
        int currentPage = PageUtil.getCurrentPage(page,totalPage);
        model.addAttribute("ntfs", GsonUtil.toJson(notificationService.selectsByPage(currentPage,condition)));
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("currentPage",currentPage);
        return "user/notifications";
    }

    /**
     * 前往用户通知页面--默认
     * @param condition 条件参数
     * @return
     */
    @RequiresUser
    @RequestMapping(value = "{uuuid}/ntfs", method = RequestMethod.GET)
    public String toNotifications(Notification condition, Model model){
        return toNotificationsByPage(condition,1,model);
    }

    /**
     * 将通知标记为已读
     * @param record 条件参数
     * @return (json)是否已成功将通知标记为已读
     */
    @RequiresUser
    @RequestMapping(value = "{uuuid}/ntfs/{id}" , method = RequestMethod.PUT)
    public @ResponseBody Boolean readNotifications(Notification record){
        record.setUid(UserHelper.getCurrentUserId());
        Boolean result = notificationService.read(record);
        if(result){
            Session session = SecurityUtils.getSubject().getSession();
            int ntfCount = Integer.parseInt(session.getAttribute("ntfCount").toString());
            session.setAttribute("ntfCount",ntfCount > 0 ? ntfCount -1 : 0);
        }
        return result;
    }

    /**
     * 前往提问页面
     * 注 : 要求添加问题权限
     */
    @RequiresPermissions("question:create")
    @RequestMapping(value = "ask", method = RequestMethod.GET)
    public String toAsk() {
        return "editor/ask";
    }

    /**
     * 添加提问
     * @param record 待添加的提问
     * 注 : 要求添加问题权限
     * @return (json)是否成功添加提问到数据库
     */
    @RequiresPermissions("question:create")
    @RequestMapping(value = "ask", method = RequestMethod.PUT)
    public @ResponseBody Boolean ask(@RequestBody Topic record) {
        record.parseDefaultQuestion();
        return topicService.insert(record);
    }

    /**
     * 前往分享经验页面
     * 注 : 要求添加文章权限
     */
    @RequiresPermissions("article:create")
    @RequestMapping(value = "write", method = RequestMethod.GET)
    public String toWrite() {
        return "editor/write";
    }

    /**
     * 添加经验
     * 注 : 要求添加文章权限
     * @param record 待添加的文章
     * @return (json)是否成功添加分享到数据库
     */
    @RequiresPermissions("article:create")
    @RequestMapping(value = "write", method = RequestMethod.PUT)
    public @ResponseBody Boolean write(@RequestBody  Topic record) {
        record.parseDefaultArticle();
        return topicService.insert(record);
    }

    /**
     * 前往分享代码页面
     * 注 : 要求添加代码权限
     */
    @RequiresPermissions("code:create")
    @RequestMapping(value = "upload", method = RequestMethod.GET)
    public String toUpload() {
        return "editor/upload";
    }

    /**
     * 添加代码
     * 注 : 要求添加代码权限
     * @param record 待添加的代码
     * @return (json)是否成功添加代码到数据库
     */
    @RequiresPermissions("code:create")
    @RequestMapping(value = "upload", method = RequestMethod.PUT)
    public @ResponseBody Boolean upload(@RequestBody Topic record) {
        record.parseDefaultCode();
        return topicService.insert(record);
    }

    /**
     * 前往写教程页面
     * 注 : 要求添加教程权限
     */
    @RequiresPermissions("tutorial:create")
    @RequestMapping(value = "teach", method = RequestMethod.GET)
    public String toTeach() {
        return "editor/teach";
    }

    /**
     * 添加教程
     * 注 : 要求添加教程权限
     * @param record 待添加的教程
     * @return (json)是否成功添加教程到数据库
     */
    @RequiresPermissions("tutorial:create")
    @RequestMapping(value = "teach", method = RequestMethod.PUT)
    public @ResponseBody Boolean teach(@RequestBody Topic record) {
        record.parseDefaultTutorial();
        return topicService.insert(record);
    }

    /**
     * 封禁/解封账户
     * 注 : 要求具有封禁学生账户以上的权限
     * @param uid 待封禁/解封的账户
     * @return 是否成功封禁/解封账户
     */
    @RequiresPermissions("suser:block")
    @RequestMapping(value = "{diu}/block", method = RequestMethod.POST)
    public @ResponseBody boolean blockUser(@PathVariable String uid){
        if(userHelper.hasEnoughRole(uid)) return userService.changeStatus(uid);//仅当角色符合条件时进行操作
        else return false;
    }

    /**
     * 删除账户
     * @param uid 待删除的账户
     * @return 是否成功删除账户
     */
    @RequiresPermissions("suser:delete")
    @RequestMapping(value = "{diu}/delete", method = RequestMethod.POST)
    public @ResponseBody boolean deleteUser(@PathVariable String uid){
        if(userHelper.hasEnoughRole(uid)) return userService.delete(uid);//仅当角色符合条件时进行操作
        else return false;
    }

    /**
     * 前往添加用户页面
     */
    @RequiresPermissions("suser:create")
    @RequestMapping(value = {"addStudent","addTeacher"}, method = RequestMethod.GET)
    public String toAddUser(){
        return "user/add";
    }

    /**
     * 添加学生用户
     * @param user 待添加的学生账户
     * @return 是否成功将学生账户保存到数据库
     */
    @RequiresPermissions("suser:create")
    @RequestMapping(value = "addStudent", method = RequestMethod.POST)
    public @ResponseBody boolean addStudent(@RequestBody User user){
        user.setRid(Byte.parseByte(String.valueOf(ENUMS.ROLE_BRONZE)));//设置待添加账户的角色为青铜账户
        user.parseDefaultAddUser();
        passwordHelper.resetSalt(user);
        passwordHelper.encryptPassword(user);
        return userService.insert(user);
    }

    /**
     * 添加教师用户验证
     * @param user 待添加的教师账户
     * @return 是否成功将教师账户保存到数据库
     */
    @RequiresPermissions("muser:create")
    @RequestMapping(value = "addTeacher", method = RequestMethod.POST)
    public @ResponseBody boolean addTeacher(@RequestBody User user){
        user.setRid(Byte.parseByte(String.valueOf(ENUMS.ROLE_TEACHER)));//设置待添加账户的角色为教师账户
        user.parseDefaultAddUser();
        passwordHelper.resetSalt(user);
        passwordHelper.encryptPassword(user);
        return userService.insert(user);
    }
    
}
