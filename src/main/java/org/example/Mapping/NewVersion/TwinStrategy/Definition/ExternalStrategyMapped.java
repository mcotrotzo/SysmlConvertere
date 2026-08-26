package org.example.Mapping.NewVersion.TwinStrategy.Definition;

import lombok.ToString;
import org.example.Mapping.Interfaces.Base.TypeKind.TypeKind;
import org.example.Mapping.Interfaces.Base.TypeKind.Usage;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.TwinBaseString;
import org.example.Mapping.Interfaces.TwinEnumPackage.CustomStrategyType;
import org.example.Mapping.Interfaces.TwinEnumPackage.EnumAttribute;
import org.example.Mapping.Interfaces.TwinStrategy.ExternalStrategy;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Definition.TwinBaseAttributeStringMapped;
import org.example.Mapping.TwinAttributeMapped.EnumCustomStrategyMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.ActionDefinition;
import org.omg.sysml.lang.sysml.ActionUsage;
import org.omg.sysml.lang.sysml.Type;

@MappedElementType(LibraryNameSpaces.EXTERNAL_STRATEGY)
@ToString(callSuper = true)
public class ExternalStrategyMapped<T extends TypeKind> extends TwinStrategyMapped<T> implements ExternalStrategy<T> {
	EnumCustomStrategyMapped customStrategyType;
	TwinBaseAttributeStringMapped<Usage> contentPath;

	public ExternalStrategyMapped(ActionUsage sysmlElement) {
		super(sysmlElement);
	}

	public ExternalStrategyMapped(ActionDefinition sysmlElement) {
		super(sysmlElement);
	}


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		contentPath = context.mapAttributes(this, "contentPath", TwinBaseAttributeStringMapped.getRawStringUsageClass(), Role.CONFIG).stream().findFirst().orElseThrow(() -> new MappingException("No contentPath found for ExternalStrategyMapped"));
		Class<EnumCustomStrategyMapped> s = rawClassOf(EnumCustomStrategyMapped.class);
		customStrategyType = context.mapSlot(this, "strategyType", s).stream().findFirst().orElseThrow(() -> new MappingException("No customStrategyType found for ExternalStrategyMapped"));
	}

	@Override
	public TwinBaseString<Usage> getContentPath() {
		return contentPath;
	}

	@Override
	public EnumAttribute<CustomStrategyType> getStrategyType() {
		return customStrategyType;
	}
}
