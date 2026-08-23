package org.example.Mapping.TwinAttributeMapped.CustomTypeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.Usage.CustomTypeUsage;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.Definition.CustomTypeDefinition;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinAttributeUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinAttributeUsageMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;

import java.util.ArrayList;
import java.util.List;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomTypeUsageMapped extends TwinAttributeUsageMapped implements CustomTypeUsage {

	protected List<TwinAttributeUsageMapped> fields = new ArrayList<>();
	private MappedReference<? extends CustomAttributeMappedDefinition> definition;

	public CustomTypeUsageMapped(Feature sysmlElement) {
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
		super.parse(context);
		fields = context.mapAttributes(this, "fields", TwinAttributeUsageMapped.class,getRole());
	}

}
