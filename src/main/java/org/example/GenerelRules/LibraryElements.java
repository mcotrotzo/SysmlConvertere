package org.example.GenerelRules;

public enum LibraryElements {
    TWIN("TwinDefLibrary::Twin");

    private final String name;

    private LibraryElements(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
