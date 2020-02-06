package com.zy.advisor;

import com.zy.utils.A;
import com.zy.utils.B;
import com.zy.utils.C;
import com.zy.utils.MyRedBlackTree;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestPointCutAdvisor {

    @Test
    public void testBeforeAdvisor() {
        ProxyFactory pf = new ProxyFactory();
        pf.setTarget(new Waiter());
        pf.addAdvice(new GreetingBeforeAdvice());

        Waiter waiter = (Waiter) pf.getProxy();
        System.out.println(waiter);
        waiter.greetTo("ZY");
    }

    @Test
    public void testPointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter = (Waiter) ctx.getBean("waiter");
        Seller seller = (Seller) ctx.getBean("seller");

        waiter.greetTo("John");
        waiter.serveTo("John");
        seller.greetTo("John");
    }

    @Test
    public void testRegexPointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter2 = (Waiter) ctx.getBean("waiter2");
        waiter2.greetTo("John");
        waiter2.serveTo("John");
    }

    @Test
    public void testDynamicPointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter3 = (Waiter) ctx.getBean("waiter3");

        waiter3.serveTo("Peter");
        waiter3.greetTo("Peter");
        waiter3.serveTo("John");
        waiter3.greetTo("John");
    }

    @Test
    public void testControlFlowPointcutAdvisor() {
        String configPath = "spring/spring-pointcut.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);

        Waiter waiter4 = (Waiter) ctx.getBean("waiter4");
        WaiterDelegate waiterDelegate = new WaiterDelegate();
        waiterDelegate.setWaiter(waiter4);

        waiter4.serveTo("Peter");
        waiter4.greetTo("Peter");

        waiterDelegate.service("Peter");
    }

    @Test
    public void testComposablePointcutAdvisor() {
//        String configPath = "spring/spring-pointcut.xml";
//        ApplicationContext ctx = new ClassPathXmlApplicationContext(configPath);
//
//        Waiter waiter5 = (Waiter) ctx.getBean("waiter5");
//        WaiterDelegate waiterDelegate = new WaiterDelegate();
//        waiterDelegate.setWaiter(waiter5);
//
//        waiter5.serveTo("Peter");
//        waiter5.greetTo("Peter");
//
//        waiterDelegate.service("Peter");
    }

    @Test
    public void testABC() {
        A a = new A();
        B b = new B();

        Comparable k1 = a;

        Comparable k2 = b;

        Object o = new String();

        // 1 什么样的泛型可以赋值
        Comparable<? super A> kt = (Comparable<C>) a;

        System.out.println(k1 + "-" + a);
        System.out.println(k2 + "-" + b);

        // new出来的具体泛型是什么类型
        List<? super A> list1 = new LinkedList<C>();
        list1.add(new A());
//        list1.add(new Object());
        list1.add(new B());
//        list1.add(new C());
        List<? extends Object> list2 = new LinkedList<C>();
//        list2.add(new C());
        A a1 = (A) list2.get(0);
        String s = (String) list2.get(0);
    }

    @Test
    public void testMyRedBlackTree() {
        MyRedBlackTree<Integer, Integer> tree = new MyRedBlackTree<>();

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 1000; i++) {
            tree.put(random.nextInt(1000), 1);
        }
        for (int i = 0; i < 10000; i++) {
            tree.remove(random.nextInt(1000));
        }
    }

    @Test
    public void testCharset() throws UnsupportedEncodingException {
        String s1 = "DL 得力 S939　原木\n铅笔 2H∮60ｘ187mm 30支/筒 (单位:筒) 原木色";
        String s2 = "JD (6494968) å\u0090´è£\u0095æ³° ä¸\u00ADå\u008D\u008Eè\u0080\u0081å\u00AD\u0097å\u008F·è\u008C\u0089è\u008E\u0089è\u008A±è\u008C¶ è\u008C\u0089è\u008E\u00891887 40g/ç½\u0090";

        String s6 = new String(s2.getBytes("ISO8859-1"), "UTF-8");

        System.out.println(new Date().toString());
        System.out.println(System.getProperty("file.encoding"));
        System.out.println(System.getProperty("user.language"));
        System.out.println(Charset.defaultCharset());
        StringBuilder sb = new StringBuilder();
        sb.append(s1);
//        sb.append(s2);
        System.out.println(sb.toString().replaceAll("[\\t\\n\\r]", " ").replace('　', ' '));
//        System.out.println(s6);
//        System.out.println(isMessyCode(s1));

    }

    /**
     * 判断字符串是否是乱码
     *
     * @param strName 字符串
     * @return 是否是乱码
     */
    public static boolean isMessyCode(String strName) {
        Pattern p = Pattern.compile("\\s*|t*|r*|n*");
        Matcher m = p.matcher(strName);
        String after = m.replaceAll("");//去重为空的情况
        String temp = after.replaceAll("\\p{P}", "");
        char[] ch = temp.trim().toCharArray();
        float count = 0;
        for (int i = 0; i < ch.length; i++) {
            char c = ch[i];
            if (!Character.isLetterOrDigit(c)) {
                if (!(c <= 0x7F
                        || c == 160
                        || c == 176
                        || c == 177
                        || c == 178
                        || c == 179
                        || c == 188
                        || c == 189
                        || c == 190
                        || c == 8482
                        || c == 8546
                        || c == 215
                        || c == 8451
                        || c == 13217
                        || c == 9334
                        || c == 8545
                        || c == 8804)) {
                    if (!isChinese(c)) {
                        System.out.println(c + "-" + (int) c);
                        count = count + 1;
                    }
                }
            }
        }
        return count == 0;
    }

    /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);

        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                //UFE30-CJK Compatibility Forms  (主要是给竖写方式使用的括号，以及间断线﹉，波浪线﹌等)
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                //UFE10-Vertical Forms (主要是一些竖着写的标点符号，    等等)
                || ub == Character.UnicodeBlock.VERTICAL_FORMS
        ) {
            return true;
        }
        return false;
    }

}
