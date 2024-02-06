package ru.gb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.gb.demo.MyServiceBean;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringAopDemoApplication {

	// OOP (Object oriented programing)
	// AOP (Aspect oriented programming) - главной сущностью является аспект - который можно встроить
	// в исполнение программного кода, не записывая этот код в непосредственном месте исполнения.

	// Интерфейс JoinPoint - получает информацию о месте, куда внедрились.

	// Spring создает Proxy оригинального класса, через который и работает. 2 библиотеки посредством
	// которых это реализуется
	// 1. CGLib extends MyServiceBean (используется по умолчанию)
	// 2. DynamicProxy implements ... - берет проксируемый объект с интерфейсами, имплементит их, и
	// использует.
	// При помощи @EnableAspectJAutoProxy производится конфигурация.

	// @Transactional - Spring использует aop при реаализации транзакции, а именно Around advice

	public static void main(String[] args) {
		//метод isAssignableFrom учитывает иерархию классов
//		System.out.println(RuntimeException.class.isAssignableFrom(IllegalArgumentException.class));
//		System.out.println(IllegalArgumentException.class.isAssignableFrom(RuntimeException.class));


		ConfigurableApplicationContext context = SpringApplication.run(SpringAopDemoApplication.class, args);
//		MyServiceBean myServiceBean = context.getBean(MyServiceBean.class);
//
//		myServiceBean.method1("argument");

		// object = myServiceBean
		// proxy[object]

		// method1 -> proxy[object -> method2]


		// object = Bean MyServiceBean
		// Proxy[object = MyServiceBean] != MyServiceBean, но == MyServiceInterface

	}

}
