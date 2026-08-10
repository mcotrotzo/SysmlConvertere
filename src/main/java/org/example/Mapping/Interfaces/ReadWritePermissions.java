package org.example.Mapping.Interfaces;

import java.util.Set;

public interface ReadWritePermissions{
	Set<ReadWriteRoles> getReadPermissions();
	Set<ReadWriteRoles> getWritePermissions();
}
