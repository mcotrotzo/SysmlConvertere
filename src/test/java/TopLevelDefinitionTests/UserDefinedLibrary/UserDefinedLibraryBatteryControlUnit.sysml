package BatteryControlUnitLibrary{

    private import TwinLibrary::*;
    private import ScalarValues::*;
    
    calc def All:>CustomCalculationAction{
        in attribute bools[0..*]:TwinBoolean;
        return out attribute alltrue:TwinBoolean;

        for value:TwinBoolean in bools{
            assign alltrue := alltrue & value;
        }
    }

}