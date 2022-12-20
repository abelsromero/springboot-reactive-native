package com.example.reactive.aot;

import java.util.Set;

import com.example.reactive.Application;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class ApplicationRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationRuntimeHintsRegistrar.class);
	public final MemberCategory[] allCategories = MemberCategory.values();


	@Override
	public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
		logger.info("Registering Hints");

		final Reflections reflections = new Reflections(Application.class.getPackage().getName());

		final Set<Class<?>> types = reflections.getTypesAnnotatedWith(RegisterForReflection.class);
		for (Class<?> type : types) {
			logger.info("Registering type: " + type.getCanonicalName());
			hints.reflection().registerType(type, allCategories);
		}
	}
}
