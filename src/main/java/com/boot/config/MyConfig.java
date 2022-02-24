package com.boot.config;
//配置类
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import com.boot.bean.Car;
import com.boot.bean.Pet;
import com.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件,默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods:代理bean的方法
 *      Full(proxyBeanMethods = true)  (默认值,保证每个@Bean方法被调用多少次返回的组件都是单实例的)
 *      Lite(proxyBeanMethods = false)(每个@Bean方法被调用多少次返回的组件都是新创建的)
 *      组件依赖必须使用Full模式默认.其他默认是Lite模式
 * 4、@Import({User.class, DBHelper.class})
 *  *      给容器中自动创建出这两个类型的组件,默认组件的名字就是全类名
 */

//@ConditionalOnBean(name = "Mary")               Conditional是一个根注解,这个注解的意思是:只有此类有id名为Mary的组件时,类下方法的配置才有效
@ConditionalOnMissingBean(name = "Mary")          //只有此类没有id名为Mary的组件时,类下方法的配置才有效,与上面的注解一样,也可以标志在方法上
@Import({User.class, PatternLayoutEncoder.class})
@ImportResource("classpath:beans.xml")            //导入xml配置文件的资源,从此xml文件里的配置也能生效
@EnableConfigurationProperties(Car.class)         //将Car类开启配置绑定功能,并将Car组件在容器中注册,让Spring管理
@Configuration(proxyBeanMethods = true)           //告诉SpringBoot这是一个配置类 == 配置文件
                                                  //属性意思:相对于是成为代理对象(类似一个传声筒,它调用被代理的对象,并且把返回结果再传给调用代理对象的对象)
public class MyConfig {


     /*Full:外部无论对配置类中的这个组件注册方法调用多少次获取的都是之前注册容器中的单实例对象*/

    @Bean //给容器中添加组件,以"方法名"作为组件的id.返回类型就是组件类型.返回的值,就是组件在容器中的实例
    public User user01(){
        User zhangsan = new User("zhangsan", 18);
        //user组件依赖了Pet组件
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }

    @Bean("tom")//自己定义tom是此组件的id
    public Pet tomcatPet(){
        return new Pet("tomcat");
    }
}
