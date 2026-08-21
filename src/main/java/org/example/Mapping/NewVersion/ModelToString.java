package org.example.Mapping.NewVersion;

import org.example.Mapping.Interfaces.Base.Model;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ModelToString {

	public static String print(Model model) {
		return print(model, new IdentityHashMap<>());
	}

	private static String print(Object value, Map<Object, Boolean> visiting) {
		if (value == null) return "null";
		if (!(value instanceof Model model)) return String.valueOf(value);

		if (visiting.containsKey(model)) {
			return interfaceName(model.getClass()) + "{...cycle...}";
		}
		visiting.put(model, true);

		StringBuilder sb = new StringBuilder(interfaceName(model.getClass())).append("{");
		boolean first = true;

		for (Method m : getDomainGetters(model.getClass())) {
			try {
				Object result = m.invoke(model);
				if (!first) sb.append(", ");
				first = false;
				sb.append(m.getName().replaceFirst("^get", "")).append("=");
				sb.append(formatValue(result, visiting));
			} catch (Exception e) {
				sb.append(m.getName()).append("=<error: ").append(e.getMessage()).append(">");
			}
		}

		sb.append("}");
		visiting.remove(model);
		return sb.toString();
	}

	private static String formatValue(Object result, Map<Object, Boolean> visiting) {
		if (result instanceof List<?> list) {
			return list.stream().map(item -> print(item, visiting)).collect(Collectors.joining(", ", "[", "]"));
		}
		if (result instanceof Model m) {
			return print(m, visiting);
		}
		return String.valueOf(result);
	}

	private static List<Method> getDomainGetters(Class<?> clazz) {
		Set<Class<?>> interfaces = new LinkedHashSet<>();
		collectModelInterfaces(clazz, interfaces);

		Map<String, Method> methods = new LinkedHashMap<>();

		for (Class<?> iface : interfaces) {
			for (Method method : iface.getDeclaredMethods()) {
				if (method.getParameterCount() != 0) {
					continue;
				}

				if (!method.getName().startsWith("get")) {
					continue;
				}

				if (method.getName().equals("getParent") || method.getName().equals("getOwner") || method.getName().equals("getId")) {
					continue;
				}

				String key = method.getName() + ":" + method.getReturnType().getName();

				methods.putIfAbsent(key, method);
			}
		}

		return new ArrayList<>(methods.values());
	}

	private static void collectModelInterfaces(Class<?> clazz, Set<Class<?>> result) {
		if (clazz == null || clazz == Object.class) return;
		for (Class<?> iface : clazz.getInterfaces()) {
			if (Model.class.isAssignableFrom(iface) && iface != Model.class) {
				result.add(iface);
				collectModelInterfaces(iface, result); // Super-Interfaces mit einsammeln
			}
		}
		collectModelInterfaces(clazz.getSuperclass(), result);
	}

	private static String interfaceName(Class<?> clazz) {
		for (Class<?> iface : clazz.getInterfaces()) {
			if (Model.class.isAssignableFrom(iface) && iface != Model.class) {
				return iface.getSimpleName();
			}
		}
		return clazz.getSimpleName();
	}
}
