package org.example.Mapping.Interfaces.DataBase;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.TwinAttribute.BaseTwinAttribute.Usage.TwinBaseIntegerUsage;

public interface Database extends Model {
	TwinBaseIntegerUsage getDurationInDays();
}
