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
                   c1 :>>communicationProtocol:MQTT_Protocol{
                       attribute :>>broker = "localhost";
                       attribute :>>topic = "battery/measurements";
                   }
                  out attribute temp[2]    : TwinReal    :>> measurements;
                  out attribute temp2[2] :>temp;

                  out attribute voltage : TwinReal    :>> measurements;
                  out attribute current : TwinReal    :>> measurements;
                  out attribute plug    : TwinBoolean :>> measurements;
                  out attribute pos     : Position    :>> measurements;
                  out attribute pos2     : Position    :>> measurements;
                  out attribute pos3     : Position    :>> measurements;
             }

            out port constPort:>>constPort{
                c1 :>>communicationProtocol:MQTT_Protocol{
                                   attribute :>>broker = "localhost";
                                   attribute :>>topic = "battery/measurements";
                               }
                out attribute collectionTest    : TwinReal    :>> measurements = (2, 3, 4);
                out attribute baseFunctionTest : TwinReal    :>> measurements = DIV_real(y=2, x=10);
                out attribute constructorTest : TwinBoolean    :>> measurements = new TwinBoolean(true);
                out attribute constructorTestBoolean    : TwinBoolean :>> measurements = new TwinBoolean(false);
                out attribute customCalculationTest     : Position    :>> measurements = AddPosition(pos_a=new Position(x=10, y=20, z=30), pos_b=new Position(x=0, y=0, z=0));
                out attribute maxCharge        : TwinReal :>> measurements = INDEX_seq(nominalVoltage, 1) * 10;
                out attribute nominalVoltage   : TwinReal :>> measurements = 48;
                out attribute defaultNetDemand : TwinReal :>> measurements = 0;
                out attribute defaultNetSupply : TwinReal :>> measurements = DIV_real(y=defaultNetDemand, x=defaultNetDemand);

            }

             port p12 :> actuators {
             c1 :>>communicationProtocol:MQTT_Protocol{
                                    attribute :>>broker = "localhost";
                                    attribute :>>topic = "battery/measurements";
                                }
                 in attribute charge : TwinReal :>> commands;
             }

             port p13[23]:>p11;
             port p14[2]:>p13,p11;
             port p15[1]:>p14;


             state cm1 :> controlUnit {

                 in attribute temp:TwinReal :>> inputs;
                 out attribute charge:TwinReal :>> outputs;
                 in attribute plug:TwinBoolean :>> inputs;
                 in attribute maxCharge:TwinReal :>>inputs;

                 state idle:>>states;
                 state charging:>>states {

                                 state test34:>states{
                                     entry assign charge := p11.temp;
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
             flow :>physicalFlows from constPort.maxCharge to cm1.maxCharge;
         }



         part shadowBattery :>> shadow {
             :>>databases:RelationalDatabase{
                 :>>durationInDays default 30;
             }
         }

        flow :>queryFlows from physicalBattery.p11.voltage to descriptiveBattery.soc.voltage{
            :>>limit = 1;
        }
        flow :>queryFlows from physicalBattery.constPort.nominalVoltage to descriptiveBattery.soc.nominalVoltage{
            :>>limit = 1;
        }

        flow :>queryFlows from physicalBattery.p11.current to descriptiveBattery.LLM_Request.current{
            :>>limit = 1;
        }

        flow :>queryFlows from physicalBattery.p11.temp to descriptiveBattery.temp30.temps{
            :>>limit = 30;
        }

        flow :>queryFlows from physicalBattery.constPort.maxCharge to prescriptiveBattery.chargeStrategyExternal.maxCharge {
            :>> limit = 1;
        }

        flow :>queryFlows from physicalBattery.constPort.maxCharge to prescriptiveBattery.chargeStrategyInternal.maxCharge {
            :>> limit = 1;
        }

        part descriptiveBattery :>> descriptiveModel {

             action soc :> derivedAttributes{
                in attribute voltage:TwinReal:>>inputs;
                out attribute soc:TwinReal:>>outputs;
                in attribute nominalVoltage:TwinReal:>>inputs;

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
                 out attribute llmCurrent:TwinReal[1]:>> outputs;
                 in attribute current:TwinReal:>>inputs;
                 in attribute soc:TwinReal:>>inputs;
             }

             action avgTemp :> derivedAttributes{
                in attribute temps:TwinReal[0..*]:>>inputs;
                out attribute avgTemp:TwinReal:>>outputs;
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
                 out attribute predicted : TwinReal :>> outputs;
                 in attribute current:TwinReal :>>inputs;

             }
         }

        flow :>predictiveToPrescriptiveFlows from predictiveBattery.consForecast.predicted to prescriptiveBattery.chargeStrategyExternal.predictedCurrent;
        flow :>predictiveToPrescriptiveFlows from predictiveBattery.consForecast.predicted to prescriptiveBattery.chargeStrategyInternal.predictedCurrent;
        flow :>prescriptiveToPhysicalFlows from prescriptiveBattery.chargeStrategyInternal.chargeCmd to physicalBattery.p12.charge;
        flow :>prescriptiveToPhysicalFlows from prescriptiveBattery.chargeStrategyInternal.chargeCmd to physicalBattery.p12.charge;

        part prescriptiveBattery :>> prescriptiveModel {

            action chargeStrategyExternal : ExternalStrategy :> prescriptiveStrategies {
                attribute :> strategyType = CustomStrategyType::CONTAINER;
                attribute :> contentPath = "battery/chargeStrategy";

                in attribute predictedCurrent : TwinReal :>> inputs;
                out attribute chargeCmd : TwinReal :>> outputs;
                in attribute maxCharge : TwinReal :>> inputs;
            }

            action chargeStrategyInternal : CustomStrategy :> prescriptiveStrategies {
                in attribute predictedCurrent : TwinReal :>> inputs;
                out attribute chargeCmd : TwinReal :>> outputs;
                in attribute maxCharge : TwinReal :>> inputs;
                in attribute posTest12:Position:>>inputs;

                attribute test12:TwinReal:>local_Attributes = Avg(posTest12.x);
                if LT_real(predictedCurrent, maxCharge) {
                    assign chargeCmd := predictedCurrent;
                    }
            }


         }
     }
}