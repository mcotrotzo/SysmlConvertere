package org.example.Mapping.Interfaces;

import java.util.List;

public interface Database extends Model {
	List<TwinIntegerAttribute> getDurationInDays();
}
