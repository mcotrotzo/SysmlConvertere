package org.example.Mapping.TwinAttributeMapped.CustomTypeMapped;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.Definition;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.Reference;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinAttribute;
import org.example.Mapping.Interfaces.TwinAttribute.CustomType.CustomType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinAttributeMapped;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.Abstract.MappedReference;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Classifier;
import org.omg.sysml.lang.sysml.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ToString(callSuper = true)
@MappedElementType(LibraryNameSpaces.TWIN_CUSTOM_TYPE)
public class CustomAttributeMapped<T extends TypeKind>
		extends TwinAttributeMapped<T>
		implements CustomType<T> {

	protected List<TwinAttributeMapped<Usage>> fields = new ArrayList<>();

	public CustomAttributeMapped(Type sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public List<TwinAttribute<Usage>> getFields() {
		return new ArrayList<>(fields);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		fields = context.mapAttributes(
						this,
						"fields",
						TwinAttributeMapped.getRawUsageClass(), Role.CUSTOM_TYPE_MEMBER
		);
	}
}