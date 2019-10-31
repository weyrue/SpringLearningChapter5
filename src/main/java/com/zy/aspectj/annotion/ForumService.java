package com.zy.aspectj.annotion;

public class ForumService {
    @NeedTest
    public void deleteService(int forumId) {
        System.out.println("删除论坛ID：" + forumId);
    }

    @NeedTest(false)
    public void deleteTopic(int postId) {
        System.out.println("删除主题ID：" + postId);
    }
}
