package org.example.Mapping.Model.Type;

import lombok.Getter;
import org.example.Mapping.Model.Mapper;
import org.example.Mapping.Model.Slots;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Subclassification;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Definition<C extends Core<? super D>, D extends Classifier> extends Type<D, C>
{
	@Getter private List<Definition<?, ?>> superDefinitions = List.of();

	public Definition(D sysmlElement, Supplier<C> coreFactory, Mapper newMappe) {
		super(sysmlElement, coreFactory, newMappe);
	}

	@Override
	public void fillSlots() {
		super.fillSlots();
		List<Classifier> generals = new ArrayList<>();
		for (Subclassification subclassification : sysmlElement.getOwnedSubclassification()) {
			Classifier general = subclassification.getSuperclassifier();
			if (general == null || instance.getNewUtil().isFromStandardLibrary(general) || isTwinLibraryRoot(general)) {
				continue;
			}
			generals.add(general);
		}
		superDefinitions = Slots.mapAll(instance, null, generals, Slots.<Definition<?, ?>>rawClassOf(Definition.class));
	}
}
