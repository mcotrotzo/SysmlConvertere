package org.example.Mapping.NewVersion;


import lombok.ToString;
import org.example.Mapping.Interfaces.GroupedHistoryQuery;
import org.example.Mapping.Interfaces.QueryResult;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.Interfaces.TwinTypeDefinition;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.Function;
import org.omg.sysml.lang.sysml.PartUsage;

import java.util.Set;

@MappedElementType(LibraryNameSpaces.GROUPED_HISTORY_QUERY)
@ToString(callSuper = true)
public class GroupedHistoryQueryMapped extends AbstractMappedQuery implements GroupedHistoryQuery {

	public GroupedHistoryQueryMapped(Function sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		super.parse(context);

		QueryResultDefinitionMapped queryResult = checkIsQueryResultType();
		checkMatchingAttributeTypes(queryResult);
	}

	private QueryResultDefinitionMapped checkIsQueryResultType()
			throws MappingException {

		TwinTypeDefinition definition =
				result.getDefinition().getReferent();

		if (!(definition instanceof QueryResultDefinitionMapped queryResult)) {
			throw new MappingException(
					"GroupedHistoryQuery '%s': result type '%s' must be a QueryResult."
							.formatted(
									getName(),
									definition.getName()
							)
			);
		}

		return queryResult;
	}

	private void checkMatchingAttributeTypes(
			QueryResultDefinitionMapped queryResult
	) throws MappingException {

		TwinAttribute innerResult =
				queryResult.getFields()
						.stream()
						.findFirst()
						.orElseThrow(() ->
								new MappingException(
										"GroupedHistoryQuery '%s': QueryResult '%s' has no result field."
												.formatted(
														getName(),
														queryResult.getName()
												)
								)
						);

		TwinTypeDefinition queriedType =
				twinAttribute
						.getDefinition()
						.getReferent();

		TwinTypeDefinition innerResultType =
				innerResult
						.getDefinition()
						.getReferent();

		if (queriedType != innerResultType) {
			throw new MappingException(
					("GroupedHistoryQuery '%s': twinAttribute type '%s' "
							+ "must match QueryResult field type '%s'.")
							.formatted(
									getName(),
									queriedType.getName(),
									innerResultType.getName()
							)
			);
		}
	}



}
