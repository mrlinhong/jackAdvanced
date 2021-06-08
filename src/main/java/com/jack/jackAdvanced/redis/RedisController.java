package com.jack.jackAdvanced.redis;

import com.jack.jackAdvanced.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.jack.jackAdvanced.result.CodeMsg.BIND_ERROR;


@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public Result getAuthCode(@RequestParam String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        String key = "auth:code:" + telephone;
        redisService.set(key, sb.toString());
        redisService.expire(key, 60000);
        return Result.success(String.format("获取验证码成功[%s]",sb.toString()));
    }

    @RequestMapping(value = "/verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public Result updatePassword(@RequestParam String telephone,@RequestParam String authCode) {
        if (StringUtils.isEmpty(authCode)) {
            return Result.error(BIND_ERROR);
        }
        String realAuthCode = redisService.get("auth:code:" + telephone).toString();
        boolean result = authCode.equals(realAuthCode);
        if (result) {
            return Result.success("验证码校验成功");
        } else {
            return Result.error(BIND_ERROR);
        }
    }


    /**
     * redis 五种类型基本命令
     * https://zhuanlan.zhihu.com/p/263390414
     */
    @RequestMapping(value = "/redisCommand", method = RequestMethod.GET)
    @ResponseBody
    public Result redisCommand() {
        // redis 基本类型：string，hash,list,set,zset
        redisService.set("address","南京");

        // 未设置过期时间，永不过期，如果实际内存超过你设置的最大内存，就会使用LRU删除
        // Hash 适合存储对象，比如用户对象，那么为啥不直接用string? 因为string得解析为json,hash可以直接根据key,field获取
        Map<String, Object> userMap = new HashMap<>();
        userMap.put("age",29);
        userMap.put("money",10000);
        userMap.put("address","广州");
        redisService.hSetAll("user:10001", userMap);

        System.out.println(redisService.hHasKey("user:10001","address"));
        System.out.println(redisService.hGetAll("user:10001"));

        // list存储
        /**
         * lpush key value [value1 ...] ：在指定key的列表左边插入一个或多个值；
         * rpush key value [value1 ...] ：在指定key的列表右边插入一个或多个值；
         * lpop key ：从指定key的列表左边取出第一个值；
         * rpop key：从指定key的列表右边取出第一个值；
         */
        //  栈：先进后出，lpush+lpop 或 rpush+rpop
        // 队列：先进先出，lpush+rpop 或 rpush+lpop；

        redisService.lPushAll("myStack","how","are","you");
        System.out.println(redisService.lPop("myStack")); // how
        System.out.println(redisService.lRange("myStack",0,-1));


        // set命令一般以s开头，里面元素无序且不重复，着重分享以下命令：
        /**
         * sadd key member [member ...]：在集合中增加一个或多个元素；
         * srem key member [member ...]：从集合中删除一个或多个元素；
         * smembers key：获取集合中的所欲元素；
         * scard key：获取集合中的元素个数；
         * sismember key member：判断指定member是否在集合中；
         * srandmember key [count]：从集合中获取count个元素，不从集合中删除；
         * spop key [count]：从集合中获取count个元素，从集合中删除
         * sinter key [key1 ...]：指定多个集合进行交集运算；
         * sinterstore dest key [key1 ...]：指定多个集合进行交集运算，存入dest集合；
         * sunion key [key1 ...]：指定多个集合进行并集运算；
         * sunionstore dest key [key1 ...]：指定多个集合进行并集运算，存入dest集合；
         * sdiff key [key1 ...]：指定多个集合进行差集运算；
         * sdiffstore dest key [key1 ...]：指定多个集合进行差集运算，并存入dest集合；
         */
        // set随机抽奖场景：把人员都放在一起，然后随机抽取，set很符合这种应用场景
        //  SPOP key  移除并返回集合中的一个随机元素 SRANDMEMBER key 返回集合中一个或多个随机数
        // set集合的运算，共同好友，可能认识

        redisService.sAdd("luckUser","jack1","jack2","jack3","jack4");
        System.out.println(redisService.sMembers("luckUser"));
        System.out.println("随机一个"+redisService.sPop("luckUser"));

        // zset的命令一般以z开头，里面元素是有序不可重复的。和Set用法基本一样，只是每个元素中多了一个分值，用于元素排序。
        // Redis 有序集合和集合一样也是 string 类型元素的集合,且不允许重复的成员。
        //不同的是每个元素都会关联一个 double 类型的分数。redis 正是通过分数来为集合中的成员进行从小到大的排序。有序集合的成员是唯一的,但分数(score)却可以重复。
        // 用于排名：比如考试成绩的排名，新闻热度排行榜，直播打赏排名等。

        // 	ZADD key score1 member1 [score2 member2] 向有序集合添加一个或多个成员，或者更新已存在成员的分数

        redisService.zAdd("mathScore","student1",95);
        redisService.zAdd("mathScore","student2",90);
        redisService.zAdd("mathScore","student3",100);

        // 输出95到100分之间学生
        System.out.println(redisService.zRangeByScore("mathScore",95,100));

        // bitMap签到统计，日活
        // bitMap 使用，底层还是string结构
        // # 设置值，其中value只能是 0 和 1
        //setbit key offset value
        //# 获取值
        //getbit key offset
        //# 获取指定范围内值为 1 的个数
        //# start 和 end 以字节为单位
        //bitcount key start end

        return Result.success(redisService.hGetAll("user:10001"));
    }

}
