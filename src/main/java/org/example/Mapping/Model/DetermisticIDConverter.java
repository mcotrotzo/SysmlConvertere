package org.example.Mapping.Model;

import org.example.Mapping.Model.Annotations.Converter;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class DetermisticIDConverter implements Converter<String, String> {
	@Override
	public String apply(String s) {
		return UUID.nameUUIDFromBytes(s.getBytes(StandardCharsets.UTF_8)).toString();
	}
}
