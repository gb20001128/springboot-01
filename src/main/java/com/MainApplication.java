package com;
//主程序类,主配置类
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import com.boot.bean.Pet;
import com.boot.bean.User;
import com.boot.config.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 这是一个SpringBoot应用
 *    @SpringBootApplication的子注解:1.@SpringBootConfiguration(代表当前是一个配置类)
 *                                 2.@ComponentScan(指定扫描哪些包)
 *                                 3.@EnableAutoConfiguration(利用Registrar给容器中导入一系列组件,将指定的一个包下的所有组件导入进MainApplication所在包下)
 */
@SpringBootApplication //默认扫描的包是主程序所在的包,及其子包,如果想直接设置扫描包,用注解属性:scanBasePackages ="com"
public class MainApplication {
    
    public static void main(String[] args) {

        //1.返回我们的IOC容器
        ConfigurableApplicationContext run=SpringApplication.run(MainApplication.class,args);
        //2.查看容器里面的组件(就是对象)
        String [] names= run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);        }

        //3、从容器中获取组件
        Pet tom = run.getBean("tom", Pet.class);
        System.out.println("组件:"+tom);


        //4、测试Configuration注解的proxyBeanMethods属性(配置类也是组件)
        MyConfig bean = run.getBean(MyConfig.class);
        System.out.println(bean);

        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法,SpringBoot总会检查这个组件是否在容器中有,保持组件单实例
        User user1 = bean.user01();
        User user2 = bean.user01();
        System.out.println(user1 == user2);//true,说明组件只会单实例

        //User类依赖着Pet类,User的Pet属性会是唯一的那个Pet对象
        User user01 = run.getBean("user01", User.class);
        Pet tom01 = run.getBean("tom", Pet.class);
        System.out.println("用户的宠物:"+(user01.getPet() == tom01));//true,说明User类依赖着Pet类


        //5.测试Import注解,获取组件
        String [] beanNamesForType = run.getBeanNamesForType(User.class);//根据组件类型获取组件名字
        System.out.println("===================");
        for (String s : beanNamesForType) {
            System.out.println(s);        //三个,一个是com.boot.bean.User,另一个是user01,还有一个是haha
        }

        PatternLayoutEncoder bean1 = run.getBean(PatternLayoutEncoder.class);
        System.out.println(bean1);//可见import的PatternLayoutEncoder对象也在容器中


        //6.测试ConditionalOnMissingBean注解(添加条件装配后,符合条件,能在容器里找到组件)
        boolean tom1 = run.containsBean("tom");
        System.out.println("容器中Tom组件："+tom1);


        //7.测试ImportResource注解
        boolean  hehe = run.containsBean("hehe");
        System.out.println("hehe:"+hehe);


    }

}
