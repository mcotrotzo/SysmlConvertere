package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.*;
import org.example.Mapping.Mapper.TwinExpression.TwinCalculationExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinExpression;
import org.example.Mapping.Mapper.TwinExpression.TwinFeatureReferenceExpression;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@MappedElementType(LibraryNameSpaces.EVENT_BASED_CONFIGURATION)
@ToString(callSuper = true)
public class EventBasedConfigurationMapped extends TriggerConfigurationMapped implements EventBasedConfiguration {

	private List<Reference<? extends TwinAttribute>> triggeringAttributes = new ArrayList<>();

	private List<TwinBooleanMapped> onChange = new ArrayList<>();

	public EventBasedConfigurationMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<Reference<? extends TwinAttribute>> getTriggeringAttributes() {
		return new ArrayList<>(triggeringAttributes);
	}

	@Override
	public TwinBooleanAttribute getOnChange() {
		return onChange.getFirst();
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);

		List<TwinAttributeMapped> slots = context.mapSlot(this, "triggeringAtributes_", TwinAttributeMapped.class);

		List<Expression> expressions = slots.stream().flatMap(slot -> slot.getTwinExpressions().stream()).toList();

		triggeringAttributes = resolveTriggeringAttributes(expressions);

		onChange = context.mapSlot(this, "onChange_", TwinBooleanMapped.class);
	}

	private List<Reference<? extends TwinAttribute>> resolveTriggeringAttributes(List<Expression> expressions) throws MappingException {

		List<Reference<? extends TwinAttribute>> result = new ArrayList<>();

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