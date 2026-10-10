library package TwinEnumLibrary{

    // common parent of all twin enums, mapped once by EnumDefinition
    public abstract attribute def TwinEnum;

    
    public enum def TimeUnit :> TwinEnum{
        MICROSECOND;
        MILLISECOND;
        HOUR;
        SECOND;
        MINUTE;
        DAY;
        YEAR;
    }

    public enum def ORDER_BY :> TwinEnum {
        enum ASCENDING;
        enum DESCENDING;
    }

    public enum def CustomStrategyType :> TwinEnum{
        CONTAINER;
        LAMBDA;
    }

    public enum def FederatedLinkType :> TwinEnum{
        PUSH;
        PULL;
        SHARED_MEMORY;
        PUBSUB;
        NEGOTIATION;

    }

}