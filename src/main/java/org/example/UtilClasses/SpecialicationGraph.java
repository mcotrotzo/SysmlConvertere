package org.example.UtilClasses;

import lombok.Getter;
import org.example.Util.Utils;
import org.omg.sysml.lang.sysml.Specialization;
import org.omg.sysml.lang.sysml.Type;

import java.util.*;

public abstract class SpecialicationGraph<T extends Type, C extends Type, S extends Specialization> {

	private final Class<T> specificClass;
	private final Class<C> generalClass;
	private final Class<S> specializationClass;
	@Getter
	private final Utils utils;
	@Getter
	private Map<T, Set<C>> forward = new HashMap<>();
	@Getter
	private Map<C, Set<T>> backward = new HashMap<>();

	public SpecialicationGraph(Utils utils, Class<T> specificClass, Class<C> generalClass, Class<S> specializationClass) {
		this.specificClass = specificClass;
		this.generalClass = generalClass;
		this.specializationClass = specializationClass;
		this.utils = utils;
		computeMaps();
	}

	public Set<C> getSpecializedBy(T redefining) {
		return forward.getOrDefault(redefining, Collections.emptySet());
	}

	public Set<T> getSpecificationsOf(C redefined) {
		return backward.getOrDefault(redefined, Collections.emptySet());
	}

	public Set<T> getAllSpecialized() {
		return Collections.unmodifiableSet(forward.keySet());
	}

	public Set<C> getAllSpecifications() {
		return Collections.unmodifiableSet(backward.keySet());
	}

	public void computeMaps() {
		Set<S> allSpecializations = utils.collect(specializationClass);

		for (S specialization : allSpecializations) {

			Type general = utils.convertBasicFeatureToType(specialization.getGeneral());

			Type specific = utils.convertBasicFeatureToType(specialization.getSpecific());

			if (general == null || specific == null) {
				continue;
			}

			if (general == specific) {
				continue;
			}

			if (general.getName() == null || specific.getName() == null) {
				continue;
			}

			if (!specificClass.isInstance(specific) || !generalClass.isInstance(general)) {
				continue;
			}

			if (utils.isFromStandardLibrary(specific) || utils.isFromStandardLibrary(general)) {
				continue;
			}

			addSpecialization(specificClass.cast(specific), generalClass.cast(general));
		}
	}

	private void addSpecialization(T specialization, C general) {
		forward.computeIfAbsent(specialization, k -> new HashSet<>()).add(general);
		backward.computeIfAbsent(general, k -> new HashSet<>()).add(specialization);
	}

}