package com.zy.aop;

import com.zy.aop.example.Singer;
import com.zy.aop.pointcut.*;
import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.*;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

import java.lang.reflect.Method;

public class TestPointcut {
    @Test
    public void testSimplePointcut() {
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleStaticPointcut(), new SimpleAdvice());

        GoodGuitarist goodGuitarist = new GoodGuitarist();
        GreatGuitarist greatGuitarist = new GreatGuitarist();

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(goodGuitarist);
        proxyFactory.addAdvisor(advisor);
        Singer singerA = (Singer) proxyFactory.getProxy();
        singerA.sing();

        proxyFactory = new ProxyFactory();

        proxyFactory.setTarget(greatGuitarist);
        proxyFactory.addAdvisor(advisor);
        Singer singerB = (Singer) proxyFactory.getProxy();
        singerB.sing();
    }

    @Test
    public void testDynamicPointcut() {
        Advisor advisor = new DefaultPointcutAdvisor(new SimpleDynamicPointCut(), new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GoodGuitarist());
        proxyFactory.addAdvisor(advisor);

        Singer singer = (Singer) proxyFactory.getProxy();
        GoodGuitarist guitarist = (GoodGuitarist) singer;

        singer.sing();
        guitarist.sing("1234567890");
        guitarist.sing("1234");
    }

    @Test
    public void testNameMatchMethodPointcut() {
        NameMatchMethodPointcut nameMatchMethodPointcut = new NameMatchMethodPointcut();
        nameMatchMethodPointcut.addMethodName("sing");
        nameMatchMethodPointcut.addMethodName("rest");

        Advisor advisor = new DefaultPointcutAdvisor(nameMatchMethodPointcut, new SimpleAdvice());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GoodGuitarist());
        proxyFactory.addAdvisor(advisor);

        GoodGuitarist guitarist = (GoodGuitarist) proxyFactory.getProxy();

        guitarist.sing();
        guitarist.sing("12356767");
        guitarist.rest();
        guitarist.talk();
    }

    @Test
    public void testNameMatchMethodPointcutAdvisor() {
        NameMatchMethodPointcutAdvisor nameMatchMethodPointcutAdvisor = new NameMatchMethodPointcutAdvisor(new SimpleAdvice());
        nameMatchMethodPointcutAdvisor.addMethodName("sing");
        nameMatchMethodPointcutAdvisor.addMethodName("rest");

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GoodGuitarist());
        proxyFactory.addAdvisor(nameMatchMethodPointcutAdvisor);

        GoodGuitarist guitarist = (GoodGuitarist) proxyFactory.getProxy();

        guitarist.sing();
        guitarist.sing("12356767");
        guitarist.rest();
        guitarist.talk();
    }

    @Test
    public void testJdkRegexpMethodPointcut() {
        JdkRegexpMethodPointcut jdkRegexpMethodPointcut = new JdkRegexpMethodPointcut();
        jdkRegexpMethodPointcut.setPattern(".*sing.*");

        Advisor advisor = new DefaultPointcutAdvisor(jdkRegexpMethodPointcut, new SimpleAdvice());
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GoodGuitarist());
        proxyFactory.addAdvisor(advisor);

        GoodGuitarist guitarist = (GoodGuitarist) proxyFactory.getProxy();

        guitarist.sing();
        guitarist.sing("12356767");
        guitarist.rest();
        guitarist.talk();
    }

    @Test
    public void testAspectJExpressionPointcut() {
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution (* sing*(..))");

        Advisor advisor = new DefaultPointcutAdvisor(aspectJExpressionPointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GoodGuitarist());
        proxyFactory.addAdvisor(advisor);

        GoodGuitarist guitarist = (GoodGuitarist) proxyFactory.getProxy();

        guitarist.sing();
        guitarist.sing("12356767");
        guitarist.rest();
        guitarist.talk();
    }

    @Test
    public void testAnnotationMatchingPointcut() {
        AnnotationMatchingPointcut annotationMatchingPointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);

        Advisor advisor = new DefaultPointcutAdvisor(annotationMatchingPointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GoodGuitarist());
        proxyFactory.addAdvisor(advisor);

        GoodGuitarist guitarist = (GoodGuitarist) proxyFactory.getProxy();

        guitarist.sing();
        guitarist.sing("12356767");
        guitarist.rest();
        guitarist.talk();
    }

    @Test
    public void testControlFlowPointcut() {
        Pointcut pointcut = new ControlFlowPointcut(ControlFlowUpperClass.class, "test");
        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleBeforeAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new TestBean());
        proxyFactory.addAdvisor(advisor);

        TestBean bean = (TestBean) proxyFactory.getProxy();

        bean.foo();
        new ControlFlowUpperClass().test(bean);
    }

    @Test
    public void testComposablePointcut() {
        MethodMatcher matcher1 = new StaticMethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> aClass) {
                return "sing".equals(method.getName());
            }
        };

        AnnotationMatchingPointcut annotationMatchingPointcut = AnnotationMatchingPointcut.forMethodAnnotation(AdviceRequired.class);
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution (* sing*(..))");

        // ComposablePointcut初始化时必须先指定一个参数，否则会全部匹配，影响union使用
        ComposablePointcut pointcut = new ComposablePointcut(annotationMatchingPointcut);
        pointcut.union(matcher1);
        pointcut.intersection((Pointcut) aspectJExpressionPointcut);

        Advisor advisor = new DefaultPointcutAdvisor(pointcut, new SimpleAdvice());

        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new GoodGuitarist());
        proxyFactory.addAdvisor(advisor);

        GoodGuitarist guitarist = (GoodGuitarist) proxyFactory.getProxy();

        guitarist.sing();
        guitarist.sing("12356767");
        guitarist.rest();
        guitarist.talk();

    }

}
