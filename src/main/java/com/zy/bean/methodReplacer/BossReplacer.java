package com.zy.bean.methodReplacer;

import com.zy.reflect.Car;
import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;

public class BossReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {

        System.out.println("Replace " + method.getName());

//        Car car = (Car) method.invoke(obj, args);
        Car car = new Car();
        car.setColor("red");
        car.setMaxSpeed(200);

        return car;
    }
}
