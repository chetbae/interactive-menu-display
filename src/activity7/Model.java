package activity7;

/**
 * Any concrete implementation of a Model will follow the OBSERVER design pattern
 */
interface Model extends ModelData
{ 
    public void registerObserver(Observer... pObserver); 
    
    public void unregisterObserver(Observer... pObserver); 
    
    public void notifyObservers(); 
}