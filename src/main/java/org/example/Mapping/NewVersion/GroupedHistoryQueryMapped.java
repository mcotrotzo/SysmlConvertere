package org.example.Mapping.NewVersion;


import lombok.ToString;
import org.example.Mapping.Interfaces.GroupedHistoryQuery;
import org.example.Mapping.Interfaces.TwinAttribute;
import org.example.Mapping.NewVersion.Abstract.MappedElementType;
import org.example.Util.LibraryNameSpaces;
import org.omg.sysml.lang.sysml.PartUsage;

import java.util.Set;

@MappedElementType(LibraryNameSpaces.GROUPED_HISTORY_QUERY)
@ToString(callSuper = true)
public class GroupedHistoryQueryMapped extends AbstractMappedQuery implements GroupedHistoryQuery {

	public GroupedHistoryQueryMapped(PartUsage sysmlElement) {
		super(sysmlElement);
	}

	@Override
	public void parse(MappingContext context) throws MappingException {

		super.parse(context);

		checkIsQueryResultType(result);
		checkMatchingAttributeTypes();
	}

	private void checkIsQueryResultType(Set<TwinAttributeMapped> mappedResult) throws MappingException {

		for (TwinAttributeMapped attribute : mappedResult) {
			if (!(attribute instanceof QueryResultMapped)) {
				throw new MappingException("GroupedHistoryQuery '%s': result must be a QueryResultMapped, but was '%s'.".formatted(getName(), attribute.getClass().getSimpleName()));
			}
		}
	}

	private void checkMatchingAttributeTypes() throws MappingException {
		for (TwinAttributeMapped queriedAttribute : twinAttributes) {
			for (TwinAttributeMapped attribute : result) {
				QueryResultMapped queryResult = (QueryResultMapped) attribute;


				TwinAttribute queryResultAttribute = queryResult.getFields().get(0);

				if (queriedAttribute.getClass() != queryResultAttribute.getClass()) {

					throw new MappingException(("GroupedHistoryQuery '%s': queried attribute type '%s' " + "does not match QueryResult field type '%s'.").formatted(getName(), queriedAttribute.getName(), queryResultAttribute.getClass().getSimpleName()));
				}
			}
		}
	}


}
