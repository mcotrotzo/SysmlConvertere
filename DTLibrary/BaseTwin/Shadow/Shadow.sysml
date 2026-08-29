library package ShadowLibrary {
    private import PhysicalTwinLibrary::*;
    private import TwinTypeSystem::*;
    part def Database {
        
        attribute durationInDays : TwinInteger default -1;
    }

    part def Shadow {
        part databases : Database[0..*];
    }

    

    part def RelationalDatabase specializes Database {
        
    }

    part def KeyValueDatabase specializes Database {
    }

    
    
    
    
}