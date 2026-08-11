part tettttr;

 package Test{

     private import TwinLibrary::*;

     private import BatteryControlUnitLibrary::*;
     private import TwinTypeSystem::TwinReal;
     private import TwinTypeSystem::*;
     private import UserLibrary::*;
     private import BatteryControlUnitLibrary::*;
     private import PositionThings::*;

     part def Battery :> Twin {

         port p11[30] :> sensors {

              attribute temp    : TwinReal    :> measurements;
              attribute voltage : TwinReal    :> measurements;
              attribute current : TwinReal    :> measurements;
              attribute plug    : TwinBoolean :> measurements;
              attribute pos     : Position    :> measurements;
              attribute pos2     : Position    :> measurements;
              attribute pos3     : Position    :> measurements;
             }

         attribute collectionTest    : TwinReal    :> constAttributes = (2, 3, 4);
         attribute baseFunctionTest : TwinReal    :> constAttributes = DIV_real(y=2, x=10);
         attribute constructorTest : TwinReal    :> constAttributes = new TwinReal(0);
         attribute constructorTestBoolean    : TwinBoolean :> constAttributes = new TwinBoolean(false);
         attribute customCalculationTest     : Position    :> constAttributes = AddPosition(pos_a=new Position(x=10, y=20, z=30), pos_b=new Position(x=0, y=0, z=0));
         attribute geoPositionTest     : Position    :> constAttributes = new GeoPosition(2.3, 3.4, 4.5);

         port p12 :> actuators {
             attribute charge : TwinReal :> commands;
         }


         port p13[23]:>p11;
         port p14[2]:>p13,p11;
         port p15[1]:>p14;
         attribute maxCharge        : TwinReal :> constAttributes = 100;
         attribute nominalVoltage   : TwinReal :> constAttributes = 48;
         attribute defaultNetDemand : TwinReal :> constAttributes = 0;


         :>>databases:RelationalDatabase{
             :>>durationInDays default 30;
         }
         state cm1 :> controlUnit {

             attribute maxCharge:TwinReal :> local_Attributes = maxCharge;

             state idle:>>states;
             state charging:>>states {

                             state test34:>states{
                                 entry assign p12.charge := p11.temp;
                                 do assign p12.charge := 50;
                                 exit assign p12.charge := 100;
                             }
                             entry assign p12.charge := 50;
                             do assign p12.charge := 50;
                             exit assign p12.charge := 100;
                         }

             first idle then idle;
             first idle then idle;
             transition
             first idle
             if p11.plug
             then charging;


             transition
             first idle
             if NOT_bool(p11.plug)
             then idle;


             transition
             first charging
             if NOT_bool(p11.plug)
             then idle;


         }


         attribute soc : TwinReal :> derivedAttributes =
             MULT_real(DIV_real(p11.voltage, nominalVoltage), 100);

         part temp30 :>queryHistory{
                     :>>twinAttribute:TwinReal default p11.temp;
                     :>>since default 30;
                     :>>result:TwinReal[0..*];
                 }

         part charge30:PositionHistory :>groupedQueryHistory{
              :>>twinAttribute:Position default p11.temp;
              :>>since default 30;
              t:>result:PositionQueryResult[0..*];
          }
         state test12 :> descriptiveStateMachine_{
         :>>triggerConfiguration:TimeBasedConfiguration{
                                 :>>triggerInterval_ default 5;
                                 :>>triggerIntervalUnit_ default TimeUnit::MINUTE;
                             }
                     state sa:>>states;
                     state sd:>>states;
                 }
         part LLM_Request :> descriptiveStrategies {
         :>>triggerConfiguration:TimeBasedConfiguration{
                                 :>>triggerInterval_ default 5;
                                 :>>triggerIntervalUnit_ default TimeUnit::MINUTE;
                             }
             in attribute :>> lambdaPath = "battery/LLM_Request";
             in attribute avgTemperature :TwinReal:> inputs default avgTemp;
             in attribute current:TwinReal :> inputs default p11.current;
             attribute llmCurrent:TwinReal:>> outputs:TwinReal[1];
         }
         attribute avgTemp : TwinReal :> derivedAttributes = Avg(temp30.result);


         part consForecast :> predictiveStrategies {
         :>>triggerConfiguration:TimeBasedConfiguration{
                         :>>triggerInterval_ default 5;
                         :>>triggerIntervalUnit_ default TimeUnit::MINUTE;
                     }
             in attribute :>> lambdaPath = "battery/consForecast";
             in attribute avgTemperature:TwinReal :> inputs default avgTemp;
             in attribute current:TwinReal :> inputs default p11.current;
             attribute predicted : TwinReal :> outputs;
         }


             part chargeStrategy :> prescriptiveStrategies {
             :>>triggerConfig_:EventBasedConfiguration{
                                 :>>triggeringAtributes_ default (consForecast.predicted, maxCharge);
                                 :>>onChange_ default true;
                             }
                 in attribute :>> lambdaPath default "battery/chargeStrategy";

                 attribute :>> condition_ default LT_real(consForecast.predicted, maxCharge);
                 attribute chargeCmd : TwinReal :> outputs = p12.charge;
             }




     }


 }