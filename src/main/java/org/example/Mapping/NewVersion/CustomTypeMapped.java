package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.CustomType;
import org.example.Mapping.Interfaces.CustomTypeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.AttributeUsage;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.Feature;
import org.omg.sysml.lang.sysml.Usage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomTypeMapped extends TwinAttributeMapped implements CustomType {

	protected List<TwinAttributeMapped> fields = new ArrayList<>();
	private MappedReference<? extends CustomTypeMappedDefintion> definition;

	public CustomTypeMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute> getFields() {

		return new ArrayList<>(fields);
	}

	@Override
	public Reference<? extends CustomTypeDefinition> getDefinition() {
		return definition;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		fields = context.mapSlot(this, "fields", TwinAttributeMapped.class);

		if(getSysmlElement() instanceof Feature feature){
			Definition customDefinition = feature.getType().stream()
					.filter(Definition.class::isInstance)
					.map(Definition.class::cast)
					.filter(type -> !context.getUtils().isFromDTLibrary(type))
					.findFirst()
					.orElseThrow(() ->
							new MappingException(
									"No custom type definition found for '%s'."
											.formatted(getName())
							)
					);

			definition = context.mapReference(
					customDefinition,
					CustomTypeMappedDefintion.class
			);
		}
	}

}
