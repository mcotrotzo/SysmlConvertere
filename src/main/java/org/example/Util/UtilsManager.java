package org.example.Util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.omg.sysml.lang.sysml.Element;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class UtilsManager {

    @Getter
    private final static UtilsManager instance = new UtilsManager();

    private RessourceSetUtils ressourceSetUtils;

    private LibraryUtils libraryUtils;

    public void init(Element rootElement) {
        ressourceSetUtils = new RessourceSetUtils(rootElement);
        libraryUtils = new LibraryUtils();
    }



}
