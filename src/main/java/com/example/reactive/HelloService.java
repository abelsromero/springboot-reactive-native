package com.example.reactive;

import com.example.reactive.aot.RegisterForReflection;

@RegisterForReflection
public class HelloService {

	public String hello(String username) {
		return String.format("Hello %s", username);
	}
}
