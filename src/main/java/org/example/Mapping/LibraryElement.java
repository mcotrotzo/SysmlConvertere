package org.example.Mapping;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface LibraryElement {
    String value();
}
