package org.example.Mapping.NewVersion.NameSpace.NameSpacePackage;

import org.example.Util.LibraryPackageNames;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface PackageTypeMeta {

	LibraryPackageNames value();
	LibraryPackageNames[] canImportFromPackages() default {};

}
