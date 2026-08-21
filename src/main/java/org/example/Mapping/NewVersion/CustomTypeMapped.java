package org.example.Mapping.NewVersion;

import lombok.ToString;
import org.example.Mapping.Interfaces.CustomType;
import org.example.Mapping.Interfaces.CustomTypeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.BaseTaxonomy.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.TwinAttribute.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Definition;
import org.omg.sysml.lang.sysml.Feature;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomTypeMapped extends TwinAttributeUsageMapped implements CustomType {

	protected List<TwinAttributeUsageMapped> fields = new ArrayList<>();
	private MappedReference<? extends CustomTypeMappedDefintion> definition;

	public CustomTypeMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttributeUsage> getFields() {

		return new ArrayList<>(fields);
	}

	@Override
	public Reference<? extends CustomTypeDefinition> getDefinition() {
		return definition;
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		fields = context.mapSlot(this, "fields", TwinAttributeUsageMapped.class);

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
