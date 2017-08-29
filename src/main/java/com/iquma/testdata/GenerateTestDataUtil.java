package com.iquma.testdata;

import com.iquma.pojo.*;
import com.iquma.utils.ENUMS;
import com.iquma.utils.PasswordHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 生成测试数据类
 * Created by Mo on 2017/7/29.
 */
public class GenerateTestDataUtil {
    private static final String[] secs = {"tutorial", "question", "article", "code"};//版块
    private static final byte[] tags = {1, 2, 3, 4, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44};//标签
    private static final double Z = 0.84;//默认置信水平，单侧置信区间80%
    private static final int defaultIPCounts = 400;
    private static final int defaultMaxSize = 1048576000;//默认的文件最大内存,1G
    private static final int defaultMaxContentLength = 500;//默认的内容最长长度
    private static final int defaultMaxViewCount = 1000;//默认的浏览量最大值

    /**
     * 生成用户基础信息表的数据,用于user_basinfo表
     *
     * @param capacity 每个班级的学生数
     * @return 生成的基本数据
     */
    public static List<User> generateUsers(int capacity) {
        List<User> users = new ArrayList<>(capacity);
        String[] userIdPrefixes = {"5414010301", "5414010302", "5414070101", "5414070701", "5414070901", "5414071101", "5414131401", "5414131402", "5414131501", "5414131502"};//每个班级的学号前缀,可按需修改
        PasswordHelper passwordHelper = new PasswordHelper();//用户加密工具类
        for (int i = 0; i < userIdPrefixes.length; i++) {
            for (int j = 1; j <= capacity; j++) {
                User user = new User();
                if(j < 10) user.setId(userIdPrefixes[i] + "0" +  j);
                else user.setId(userIdPrefixes[i] +  j);
                user.setName(user.getId() + "同学");
                user.parseDefaultAddUser();
                passwordHelper.resetSalt(user);
                passwordHelper.encryptPassword(user);
                users.add(user);
            }
        }
        return users;
    }

    /**
     * 生成账户登录记录的数据,用于user_sucloginfo表
     *
     * @param users    候选账户
     * @param capacity 生成的数据的总量
     * @return 生成的基本数据
     */
    public static List<Suclog> generateSuclogs(List<User> users, List<Date> dates, int capacity) {
        Random random = new Random();
        List<Suclog> suclogs = new ArrayList<>(capacity);
        List<String> ips = RandomUtil.generateRandomIPs(random,defaultIPCounts);
        for (int i = 0; i < capacity; i++) {
            Suclog suclog = new Suclog();
            suclog.setUid(users.get(random.nextInt(users.size())).getId());
            suclog.setLoginip(ips.get(random.nextInt(ips.size())));
            suclog.setLogintime(dates.get(random.nextInt(dates.size())));
            suclogs.add(suclog);
        }
        return suclogs;
    }

    /**
     * 生成话题附件表的数据,用于topic_attinfo表
     *
     * @param users    候选账户
     * @param dates    候选日期
     * @param maxSize  单个附件最大内存
     * @param capacity 生成的数据的总量
     * @return 生成的基本数据
     */
    public static List<Attachment> generateAttachments(List<User> users, List<Date> dates, long maxSize, int capacity) {
        Random random = new Random();
        List<Attachment> attachments = new ArrayList<>(capacity);
        for (int i = 1; i < capacity; i++) {
            Attachment attachment = new Attachment();
            attachment.setAddtime(dates.get(random.nextInt(dates.size())));
            attachment.setUid(users.get(random.nextInt(users.size())).getId());
            attachment.setName("测试附件" + i + ".zip");
            attachment.setPrice((byte) random.nextInt(10));
            attachment.setSize((long) random.nextInt(maxSize == 0 ? defaultMaxSize : (int) maxSize));
            attachment.setPath("/static/file/" + attachment.getName());
            attachments.add(attachment);
        }
        return attachments;
    }

    /**
     * 生成话题基本信息表的数据,用于topic_topinfo表
     *
     * @param users           候选用户
     * @param dates           候选日期
     * @param maxAttachmentID 候选附件的最大ID
     * @param capacity        生成的数据的总量
     * @return 生成的基本数据
     */
    public static List<Topic> generateTopics(List<User> users, List<Date> dates, int maxAttachmentID, int capacity) {
        Random random = new Random();
        List<Integer> attachments = new ArrayList<>(maxAttachmentID + 1);
        for (int i = 1; i <= maxAttachmentID + 1; i++) attachments.add(i);
        List<Topic> topics = new ArrayList<>(capacity);
        for (int i = 1; i <= capacity; i++) {
            Topic topic = new Topic();
            topic.setTitle("测试主贴" + i);
            topic.setSec(secs[random.nextInt(secs.length)]);
            topic.setTid(tags[random.nextInt(tags.length)]);
            topic.setAid(users.get(random.nextInt(users.size())).getId());
            if(random.nextInt(10) >= 9 && attachments.size() > 0){//以理论上1/10的概率分配附件id
                int attid = random.nextInt(attachments.size());
                topic.setAttid(attid);
                attachments.remove(attid);
            }
            else topic.setAttid(0);
            Date date = dates.get(random.nextInt(dates.size()));
            topic.setAddTime(date);
            topic.setReTime(date);
            topic.setContent(RandomUtil.generateRandomJianHan(random, random.nextInt(defaultMaxContentLength)));
            topic.setViewCount(random.nextInt(defaultMaxViewCount));
            topic.setLikeCount(1 + random.nextDouble() * 9);
            topic.setHateCount(random.nextDouble() * 10);
            topic.setRateCount(getRateCount(topic.getLikeCount(), topic.getHateCount()));
            topic.setIsHigh(topic.getRateCount() >= ENUMS.HIGH_RATE_COUNT);
            topic.setIsBlock(false);
            //其他字段需要其他表更新数据
            topic.setReplyCount(0);
            topic.setNoReply(true);
            topic.setHasBest(0);
            topics.add(topic);
        }
        return topics;
    }

    /**
     * 生成仅包含ID的主贴表，用于更新话题回复总数字段
     * @param capacity 主贴的最大ID
     * @return 生成的主贴列表
     */
    public static List<Topic> generateTopicsForReplyCount(int capacity){
        List topics = new ArrayList(capacity);
        for (int i = 0; i < capacity; i++) {
            Topic topic = new Topic();
            topic.setId(i);
            topics.add(topic);
        }
        return topics;
    }

    /**
     * 生成话题回复表的数据,用于topic_repinfo表
     *
     * @param users      候选用户
     * @param dates      候选日期
     * @param maxTopicID 候选主贴的最大ID
     * @param capacity   生成的数据的总量
     * @return 生成的基本数据
     */
    public static List<Reply> generateReplies(List<User> users, List<Date> dates, int maxTopicID, int capacity) {
        Random random = new Random();
        List<Reply> replies = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Reply reply = new Reply();
            reply.setTid(random.nextInt((maxTopicID - 1)) + 1);
            reply.setUid(users.get(random.nextInt(users.size())).getId());
            reply.setContent(RandomUtil.generateRandomJianHan(random, defaultMaxContentLength));
            reply.setAddTime(dates.get(random.nextInt(dates.size())));
            reply.setLikeCount(1 + random.nextDouble() * 9);
            reply.setHateCount(random.nextDouble() * 10);
            reply.setRateCount(getRateCount(reply.getLikeCount(), reply.getHateCount()));
            reply.setIsBest(false);
            reply.setIsBlock(false);
            replies.add(reply);
        }
        return replies;
    }

    /**
     * 生成账户收藏表的数据,用于user_favinfo表
     *
     * @param users      候选用户
     * @param dates      候选日期
     * @param maxTopicID 候选主贴的最大ID
     * @param capacity   生成的数据的总量
     * @return 生成的基本数据
     */
    public static List<Favorite> generateFavorites(List<User> users, List<Date> dates, int maxTopicID, int capacity) {
        Random random = new Random();
        List<Favorite> favorites = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            Favorite favorite = new Favorite();
            favorite.setUid(users.get(random.nextInt(users.size())).getId());
            favorite.setObid(random.nextInt(maxTopicID - 1) + 1);
            favorite.setFavTime(dates.get(random.nextInt(dates.size())));
            favorites.add(favorite);
        }
        return favorites;
    }

    //计算最终评分
    public static Byte getRateCount(Double u, Double v) {
        double n = u + v;//计算加权赞同数与加权反对数之和
        double p = u / n;//计算加权赞同数在总数中的比例
        //计算score(威尔逊置信区间下限)
        if (n == 0 || u == 0) return 0;//若赞同为0或者总和为0快速返回0
        double score = (p + Math.pow(Z, 2) / (2 * n) + Z / (2 * n) * Math.sqrt(4 * n * p * (1 - p) + Math.pow(Z, 2))) / (1 + Math.pow(Z, 2) / n);
        return (byte) (score * 10);
    }

}
