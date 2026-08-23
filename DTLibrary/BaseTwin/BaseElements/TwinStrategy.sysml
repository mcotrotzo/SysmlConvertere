library package TwinStrategyLibrary {

    private import TwinTypeSystem::*;
    private import TwinActionLibrary::*;
    private import TwinEnumLibrary::*;


    action def Strategy:> TwinAction {
      
    }
    action def CustomStrategy :> Strategy {
    }

    action def ExternalStrategy :> Strategy {
        attribute strategyType : CustomStrategyType;
        attribute contentPath : TwinString;
    }
}