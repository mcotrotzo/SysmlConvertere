package org.example.Mapping.Interfaces;

import org.example.Mapping.Interfaces.Base.Model;
import org.example.Mapping.Interfaces.TwinAttribute.ConfigAttribute.TwinIntegerAttributeUsage;

public interface Database extends Model {
	TwinIntegerAttributeUsage getDurationInDays();
}
