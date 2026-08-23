package org.example.Mapping.TwinAttributeMapped.CustomTypeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.Definition.CustomTypeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeDefinitionMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomAttributeMappedDefinition
		extends TwinAttributeDefinitionMapped
		implements CustomTypeDefinition {

	protected List<TwinAttributeUsageMapped> fields = new ArrayList<>();
	private List<MappedReference<? extends CustomTypeDefinition>> parents =
			new ArrayList<>();

	public CustomAttributeMappedDefinition(Classifier sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<Reference<? extends CustomTypeDefinition>> getParents() {
		return new ArrayList<>(parents);
	}

	@Override
	public List<TwinAttributeUsage> getFields() {
		return new ArrayList<>(fields);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		fields = context.mapAttributes(
						this,
						"fields",
						TwinAttributeUsageMapped.class, Role.CUSTOM_TYPE_MEMBER
		);
	}
}