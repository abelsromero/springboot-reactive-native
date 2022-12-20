package com.example.reactive;

import java.lang.reflect.Method;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Value("${message.prefix}")
	private String prefix;

	@GetMapping("/hello/{username}")
	public MessageResponse hello(@PathVariable String username) throws ClassNotFoundException, NoSuchMethodException {
		return new MessageResponse(String.format("%s %s", prefix, reflectionInvocation(username)));
	}

	private String reflectionInvocation(String username) throws NoSuchMethodException, ClassNotFoundException {
		Class<?> aClass = ClassUtils.forName(HelloService.class.getName(), getClass().getClassLoader());
		Method helloMethod = aClass.getMethod("hello", String.class);
		Object helloService = BeanUtils.instantiateClass(aClass);
		return (String) ReflectionUtils.invokeMethod(helloMethod, helloService, username);
	}

	record MessageResponse(String message) { }
}
