package com.boot.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 只有在容器中的组件，才会拥有SpringBoot提供的强大功能
 */
//@Component   在容器中注册,让Spring管理,成为组件(MyConfig配置类的EnableConfigurationProperties注解也可以代替此注解)
@ConfigurationProperties(prefix = "mycar")  //配置属性注解,让mycar代替此类,并可以在application.properties配置文件中进行属性设置
public class Car {

    private String brand;
    private Integer price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }
}