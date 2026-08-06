package org.example.GenerelRules;

import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Element;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class SpecialicingRules extends GenerelRules {
	private static final Logger log = LoggerFactory.getLogger(SpecialicingRules.class);

	public SpecialicingRules(Utils utils) {
		super(utils);
	}

	@Override
	public abstract boolean isValid();

	public void logSpecialicingBut(Type specific, Type general, String specialiceSymbol, String message) {
		String specificName = getNameOrDefault(specific);
		String generalName = getNameOrDefault(general);
		Optional<String> ownerName = getOwnerName(specific);

		if (ownerName.isPresent()) {
			log.error("'{}' of '{}' {} '{}' {}", specificName, ownerName.get(), specialiceSymbol, generalName, message);
		} else {
			log.error("'{}' {} '{}' {}", specificName, specialiceSymbol, generalName, message);
		}
	}

	public void logSpecialicingBut(Type specific, Collection<? extends Type> general, String specialiceSymbol, String message) {
		String specificName = getNameOrDefault(specific);
		String generalNames = formatCollection(general);
		Optional<String> ownerName = getOwnerName(specific);

		if (ownerName.isPresent()) {
			log.error("'{}' of '{}' {} {} {}", specificName, ownerName.get(), specialiceSymbol, generalNames, message);
		} else {
			log.error("'{}' {} {} {}", specificName, specialiceSymbol, generalNames, message);
		}
	}

	public void logSpecialicingBut(Collection<? extends Type> specific, Type general, String specialiceSymbol, String message) {
		String specificNames = formatCollection(specific);
		String generalName = getNameOrDefault(general);

		log.error("{} {} '{}' {}", specificNames, specialiceSymbol, generalName, message);
	}

	public void logSpecialicingBut(Collection<? extends Type> specific, Collection<? extends Type> general, String specialiceSymbol, String message) {
		String specificNames = formatCollection(specific);
		String generalNames = formatCollection(general);

		log.error("{} {} {} {}", specificNames, specialiceSymbol, generalNames, message);
	}

	private String getNameOrDefault(Type type) {
		return (type != null && type.getName() != null) ? type.getName() : "<anonymous>";
	}

	private String formatCollection(Collection<? extends Type> collection) {
		if (collection == null || collection.isEmpty()) {
			return "[]";
		}
		return collection.stream().map(this::getNameOrDefault).collect(Collectors.joining(", ", "[", "]"));
	}

	private Optional<String> getOwnerName(Type type) {
		if (type instanceof Feature feature) {
			return Optional.ofNullable(feature.getOwningType()).map(Element::getName);
		}
		if (type != null && type.getOwningNamespace() != null) {
			return Optional.ofNullable(type.getOwningNamespace().getName());
		}
		return Optional.empty();
	}
}