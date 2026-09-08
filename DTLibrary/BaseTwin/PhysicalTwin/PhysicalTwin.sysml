library package PhysicalTwinLibrary {
    private import TwinTypeSystem::*;
    private import TwinStateMachineLibrary::*;



    abstract attribute def CommunicationProtocol {
        
    }

    attribute def HTTP_Protocol :> CommunicationProtocol {
        attribute url[1] : TwinString;
    }
    attribute def MQTT_Protocol :> CommunicationProtocol {
        attribute broker[1] : TwinString;
        attribute topic[1] : TwinString;
    }





    abstract port def TwinPort{
        abstract attribute communicationProtocol : CommunicationProtocol;
        attribute deviceKey[1]: TwinString;
    }

    port def Sensor :> TwinPort {
        out attribute measurements[0..*] : TwinAttribute;
    }

    port def Actuator :> TwinPort {
        in attribute commands[0..*] : TwinAttribute;
    }

    port def ConstPort :> TwinPort {
        out attribute measurements[0..*] : TwinAttribute;

    }

  


    state def ControlUnit:>State  {

        state states:ControlUnitState[0..*]:>>states;
    }

    state def ControlUnitState:>State {
        state states:ControlUnitState[0..*]:>>states;
    }



}