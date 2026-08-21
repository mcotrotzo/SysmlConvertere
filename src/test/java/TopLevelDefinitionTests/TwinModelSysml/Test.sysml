package Test{

     private import TwinLibrary::*;

     private import BatteryControlUnitLibrary::*;
     private import TwinTypeSystem::TwinReal;
     private import TwinTypeSystem::*;
     private import UserLibrary::*;
     private import BatteryControlUnitLibrary::*;
     private import PositionThings::*;

     part def Battery :> Twin {

         part physicalBattery :>> physicalTwin {

             port p11[30] :> sensors {

                  out attribute temp[2]    : TwinReal    :>> measurements;
                  out attribute temp2[2] :>temp;

                  out attribute voltage : TwinReal    :>> measurements;
                  out attribute current : TwinReal    :>> measurements;
                  out attribute plug    : TwinBoolean :>> measurements;
                  out attribute pos     : Position    :>> measurements;
                  out attribute pos2     : Position    :>> measurements;
                  out attribute pos3     : Position    :>> measurements;
             }

            out attribute collectionTest    : TwinReal    :>> constAttributes = (2, 3, 4);
            out attribute baseFunctionTest : TwinReal    :>> constAttributes = DIV_real(y=2, x=10);
            out attribute constructorTest : TwinReal    :>> constAttributes = new TwinReal(0);
            out attribute constructorTestBoolean    : TwinBoolean :>> constAttributes = new TwinBoolean(false);
            out attribute customCalculationTest     : Position    :>> constAttributes = AddPosition(pos_a=new Position(x=10, y=20, z=30), pos_b=new Position(x=0, y=0, z=0));
            out attribute maxCharge        : TwinReal :>> constAttributes = 100;
            out attribute nominalVoltage   : TwinReal :>> constAttributes = 48;
            out attribute defaultNetDemand : TwinReal :>> constAttributes = 0;

             port p12 :> actuators {
                 in attribute charge : TwinReal :>> commands;
             }

             port p13[23]:>p11;
             port p14[2]:>p13,p11;
             port p15[1]:>p14;


             state cm1 :> controlUnit {

                 in attribute temp:TwinReal :>> inputs;
                 in attribute plug:TwinBoolean :>> inputs;
                 out attribute charge:TwinReal :>> outputs;
                 in attribute maxCharge:TwinReal :>>inputs;

                 state idle:>>states;
                 state charging:>>states {

                                 state test34:>states{
                                     entry assign charge := temp;
                                     do assign charge := 50;
                                     exit assign charge := 100;
                                 }
                                 entry assign charge := 50;
                                 do assign charge := 50;
                                 exit assign charge := 100;
                             }

                 first idle then idle;
                 first idle then idle;
                 transition
                 first idle
                 if plug
                 then charging;

                 transition
                 first idle
                 if NOT_bool(plug)
                 then idle;

                 transition
                 first charging
                 if NOT_bool(plug)
                 then idle;
             }
             flow :>physicalFlows from p11.temp to cm1.temp;
             flow :>physicalFlows from p11.plug to cm1.plug;
             flow :>physicalFlows from maxCharge to cm1.maxCharge;
         }



         part shadowBattery :>> shadow {
             :>>databases:RelationalDatabase{
                 :>>durationInDays default 30;
             }
         }

        flow :>queryFlows from physicalBattery.p11.voltage to descriptiveBattery.soc.voltage{
            :>>limit = 1;
        }
        flow :>queryFlows from physicalBattery.nominalVoltage to descriptiveBattery.soc.nominalVoltage{
            :>>limit = 1;
        }

        flow :>queryFlows from physicalBattery.p11.current to descriptiveBattery.LLM_Request.current{
            :>>limit = 1;
        }

        flow :>queryFlows from physicalBattery.p11.temp to descriptiveBattery.temp30.temps{
            :>>limit = 30;
        }

        flow :>queryFlows from physicalBattery.maxCharge to prescriptiveBattery.chargeStrategyExternal.maxCharge {
            :>> limit = 1;
        }

        flow :>queryFlows from physicalBattery.maxCharge to prescriptiveBattery.chargeStrategyInternal.maxCharge {
            :>> limit = 1;
        }

        part descriptiveBattery :>> descriptiveModel {

             action soc :> derivedAttributes{
                in attribute voltage:TwinReal:>>inputs;
                in attribute nominalVoltage:TwinReal:>>inputs;
                out attribute soc:TwinReal:>>outputs;

                assign soc := MULT_real(DIV_real(voltage, nominalVoltage), 100);
             }

             action temp30:>derivedAttributes{
                in attribute temps:TwinReal[30]:>>inputs;
                out attribute res:TwinReal[0..29]:>>outputs;
                assign res := temps;
             }


             state test12 :> descriptiveStateMachine_{
                         state sa:>>states;
                         state sd:>>states;
                     }

             action LLM_Request:ExternalStrategy:> descriptiveStrategies {
                 attribute :> contentPath = "battery/LLM_Request";
                 attribute :> strategyType = CustomStrategyType::CONTAINER;
                 in attribute avgTemperature :TwinReal:>> inputs;
                 in attribute current:TwinReal:>>inputs;
                 in attribute soc:TwinReal:>>inputs;

                 out attribute llmCurrent:TwinReal[1]:>> outputs;
             }

             action avgTemp :> derivedAttributes{
                in attribute temps:TwinReal[0..*]:>>inputs;
                out attribute avgTemp:TwinReal:>>outputs = Avg(temps);
                assign avgTemp := Avg(temps);
             }

             flow :>descriptiveFlows from temp30.res to avgTemp.temps;
             flow :>descriptiveFlows from avgTemp.avgTemp to LLM_Request.avgTemperature;
             flow :>descriptiveFlows from soc.soc to LLM_Request.soc;
         }

         flow :>descriptiveToPredictiveFlows from descriptiveBattery.avgTemp.avgTemp to predictiveBattery.consForecast.avgTemperature;
         flow :>descriptiveToPredictiveFlows from descriptiveBattery.LLM_Request.llmCurrent to predictiveBattery.consForecast.current;

        part predictiveBattery :>> predictiveModel {

            action consForecast:ExternalStrategy :> predictiveStrategies {
                 attribute :>strategyType = CustomStrategyType::LAMBDA;
                 attribute :>> contentPath = "battery/consForecast";
                 in attribute avgTemperature:TwinReal:>> inputs;
                 in attribute current:TwinReal :>>inputs;
                 out attribute predicted : TwinReal :> outputs;
             }
         }

        flow :>predictiveToPrescriptiveFlows from predictiveBattery.consForecast.predicted to prescriptiveBattery.chargeStrategyExternal.predictedCurrent;
        flow :>predictiveToPrescriptiveFlows from predictiveBattery.consForecast.predicted to prescriptiveBattery.chargeStrategyInternal.predictedCurrent;
        flow :>prescriptiveToPhysicalFlows from prescriptiveBattery.chargeStrategyExternal.chargeCmd to physicalBattery.p12.charge;
        flow :>prescriptiveToPhysicalFlows from prescriptiveBattery.chargeStrategyInternal.chargeCmd to physicalBattery.p12.charge;

        part prescriptiveBattery :>> prescriptiveModel {

            action chargeStrategyExternal : ExternalStrategy :> prescriptiveStrategies {
                attribute :> strategyType = CustomStrategyType::LAMBDA;
                attribute :> contentPath = "battery/chargeStrategy";

                in attribute predictedCurrent : TwinReal :>> inputs;
                in attribute maxCharge : TwinReal :>> inputs;

                out attribute chargeCmd : TwinReal :>> outputs;
            }

            action chargeStrategyInternal : CustomStrategy :> prescriptiveStrategies {
                in attribute predictedCurrent : TwinReal :>> inputs;
                in attribute maxCharge : TwinReal :>> inputs;

                out attribute chargeCmd : TwinReal :>> outputs;

                action :> execution {
                    if LT_real(predictedCurrent, maxCharge) {
                        assign chargeCmd := predictedCurrent;
                    }
                }
            }


         }
     }
}