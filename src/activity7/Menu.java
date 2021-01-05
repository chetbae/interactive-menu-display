package activity7;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import java.util.stream.Collectors;  


/**
 * Represents a menu that can be displayed in the menu display.
 */
public class Menu implements Model
{
	/**
	 * SINGLETON Object
	 */
	private static Menu MENU = new Menu();

	private ObservableList<MenuItem> aMenuItems = FXCollections.observableArrayList();
	private List<Observer> aObserverList = new ArrayList<>();

	/*
	 * Inaccessible Constructor
	 */
	private Menu()
	{
		// Listener for changes to Menu
		aMenuItems.addListener(new ListChangeListener<>()
		{

			@Override
			public void onChanged(Change<? extends MenuItem> arg0)
			{
				notifyObservers();
			}

		});

		/*
		 * Example basic menu
		 */
//		aMenuItems.add(new SingleFood("Coffee", FoodCategory.get("Drink"), 2.00));
//		aMenuItems.add(new SingleFood("Tea", FoodCategory.get("Drink"), 1.75));
//		aMenuItems.add(new SingleFood("Beer", FoodCategory.get("Drink"), 2.50));
//
//		aMenuItems.add(new SingleFood("Poutine", FoodCategory.get("main"), 3.00));
//		aMenuItems.add(new SingleFood("Spaghetti", FoodCategory.get("main"), 5.75));
//		aMenuItems.add(new SingleFood("Soup", FoodCategory.get("main"), 2.50));
//		aMenuItems.add(new SingleFood("Okinimiyaki", FoodCategory.get("main"), 5.00));
//
//		aMenuItems.add(new SingleFood("Chips", FoodCategory.get("snack"), 1.00));
//		aMenuItems.add(new SingleFood("Peanuts", FoodCategory.get("snack"), 1.00));
//		aMenuItems.add(new SingleFood("Chocolate Bar", FoodCategory.get("snack"), 1.75));
		/*
		 * 
		 */
	}


	/**
	 * Takes no arguments
	 * 
	 * @return Singleton Menu Object
	 */
	public static Menu getInstance()
	{
		return MENU;
	}

	/**
	 * Ensures no 2 items can have the same name
	 */
	public void addItem(MenuItem pItem)
	{
		if (aMenuItems.stream()
					.map(m -> m.getName())
					.anyMatch(m -> pItem.getName().toLowerCase().equals(m.toLowerCase())) ) 
		{ System.out.println(pItem.getName() + " already exists!");}
		else 
		{
		aMenuItems.add(pItem);
		}
	}

	public void removeItem(String pName)
	{
		aMenuItems.removeIf(m -> m.getName().equals(pName));
	}

	@Override
	public void registerObserver(Observer... pObserver)
	{
		for (Observer o : pObserver)
		{
			aObserverList.add(o);
		}
	}

	@Override
	public void unregisterObserver(Observer... pObserver)
	{
		for (Observer o : pObserver)
		{
			aObserverList.remove(aObserverList.indexOf(o));
		}
	}

	@Override
	public void notifyObservers()
	{
		for (Observer o : aObserverList)
		{
			o.update();
		}
	}

	@Override
	public ArrayList<MenuItem> getItems()
	{
		return new ArrayList<>(aMenuItems);
	}
	
	
	/**
	 * A filter method that returns a list of MenuItem from the Menu that has a price lower than pPrice
	 * 
	 * @pre pPrice >= 0
	 * */
	public List<MenuItem> filter(double pPrice){
		
		assert pPrice >= 0;
		
		List<MenuItem> res = aMenuItems.stream()
			.filter(mi -> mi.getPrice() < pPrice)
			.collect(Collectors.toList());

		return res;
	}
	
	/**
	 * A filter method that returns a list of MenuItem from the Menu that has the FoodCategory pCategory
	 * 
	 * @pre pCategory != null
	 * */
	public List<MenuItem> filter(FoodCategory pCategory) {
		
		assert pCategory != null; 
		
		List<MenuItem> res = aMenuItems.stream()
				.filter(mi -> mi.getFoodCategory().equals(pCategory))
				.collect(Collectors.toList());
		
		return res;
	}
	
	/**
	 * A filter method that returns a list of MenuItem from the Menu that has the combination of Dietary Category pDietCat 
	 * 
	 * @pre pDietCat != null
	 * */
	public List<MenuItem> filter(Set<DietaryCategory> pDietCat) {
		
		assert pDietCat != null; 
		
		List<MenuItem> res = aMenuItems.stream()
				.filter(mi -> mi.getDietaryCategories().equals(pDietCat))
				.collect(Collectors.toList());
		
		return res;
	}
	
}
