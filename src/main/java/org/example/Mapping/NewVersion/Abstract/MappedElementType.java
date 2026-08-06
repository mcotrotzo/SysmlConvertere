package org.example.Mapping.NewVersion.Abstract;

import org.example.Util.LibraryNameSpaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MappedElementType {
	LibraryNameSpaces value();
}
