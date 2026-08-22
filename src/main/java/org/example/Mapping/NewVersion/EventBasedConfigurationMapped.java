package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.Calculation;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinExpression.FeatureReference;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.Mapper.TwinAttributeMapped.BaseTwinAttributeMapped.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;

@MappedElementType(LibraryNameSpaces.EVENT_BASED_CONFIGURATION)
@ToString(callSuper = true)
public class EventBasedConfigurationMapped extends TriggerConfigurationMapped implements EventBasedConfiguration {

	private List<Reference<? extends TwinAttributeUsage>> triggeringAttributes = new ArrayList<>();

	private List<TwinBooleanMappedUsage> onChange = new ArrayList<>();

	public EventBasedConfigurationMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<Reference<? extends TwinAttributeUsage>> getTriggeringAttributes() {
		return new ArrayList<>(triggeringAttributes);
	}

	@Override
	public TwinBooleanAttributeUsage getOnChange() {
		return onChange.getFirst();
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		List<TwinAttributeUsageMapped> slots = context.mapSlot(this, "triggeringAtributes_", TwinAttributeUsageMapped.class);

		List<Expression> expressions = slots.stream().flatMap(slot -> slot.getTwinExpressions().stream()).toList();

		triggeringAttributes = resolveTriggeringAttributes(expressions);

		onChange = context.mapSlot(this, "onChange_", TwinBooleanMappedUsage.class);
	}

	private List<Reference<? extends TwinAttributeUsage>> resolveTriggeringAttributes(List<Expression> expressions) throws MappingException {

		List<Reference<? extends TwinAttributeUsage>> result = new ArrayList<>();

		for (Expression expression : expressions) {

			if (expression instanceof FeatureReference reference) {
				result.add(reference.getTarget());
				continue;
			}

			if (expression instanceof Calculation calculation && ",".equals(calculation.getName())) {

				for (Expression argument : calculation.getArguments()) {

					if (!(argument instanceof FeatureReference reference)) {
						throw new MappingException("triggeringAtributes_ list may only contain feature references");
					}

					result.add(reference.getTarget());
				}

				continue;
			}

			throw new MappingException("triggeringAtributes_ must be a feature reference " + "or a list of feature references");
		}

		return result;
	}
}