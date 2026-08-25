package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Util.LibraryPackageNames;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface PackageTypeMeta {

	LibraryPackageNames value();

	LibraryPackageNames[] canImportFromPackages() default {};

}
