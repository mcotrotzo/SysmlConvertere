package org.example.Mapping.NewVersion.TwinStrategy.Usage;

import lombok.ToString;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Role;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseStringUsage;
import org.example.Mapping.Interfaces.TwinEnumPackage.CustomStrategyType;
import org.example.Mapping.Interfaces.TwinStrategy.Usage.ExternalStrategyUsage;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Mapping.NewVersion.MappingContext;
import org.example.Mapping.NewVersion.MappingException;
import org.example.Mapping.TwinAttributeMapped.BaseTwinAttributeMapped.Usage.TwinStringMappedUsage;
import org.example.Mapping.TwinAttributeMapped.EnumCustomStrategyMapped;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Feature;
@MappedElementType(LibraryNameSpaces.EXTERNAL_STRATEGY)
@ToString(callSuper = true)
public class ExternalStrategyUsageMapped extends TwinStrategyUsageMapped implements ExternalStrategyUsage {
	public ExternalStrategyUsageMapped(Feature sysmlElement) {
		super(sysmlElement);
	}

	EnumCustomStrategyMapped customStrategyType;
	TwinStringMappedUsage contentPath;


	@Override
	public void parse(MappingContext context) throws MappingException {
		super.parse(context);
		contentPath = context.mapAttributes(this,"contentPath",TwinStringMappedUsage.class, Role.CONFIG).stream().findFirst().orElseThrow(() -> new MappingException("No contentPath found for ExternalStrategyMapped"));
		customStrategyType = context.mapSlot(this,"strategyType", EnumCustomStrategyMapped.class).stream().findFirst().orElseThrow(() -> new MappingException("No customStrategyType found for ExternalStrategyMapped"));
	}

	@Override
	public TwinBaseStringUsage getContentPath() {
		return contentPath;
	}

	@Override
	public CustomStrategyType getStrategyType() {
		return customStrategyType.getValue();
	}
}
