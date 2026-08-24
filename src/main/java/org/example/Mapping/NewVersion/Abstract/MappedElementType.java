package org.example.Mapping.NewVersion.Abstract;

import org.example.Util.LibraryNameSpaces;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface MappedElementType {
	LibraryNameSpaces value();
}
