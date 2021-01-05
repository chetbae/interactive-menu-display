package activity7;

import java.util.List;
import java.util.ArrayList;

public class FoodCategory
{
	private String aName;
	private static final List<FoodCategory> FOOD_CATEGORIES = new ArrayList<>();

	/*
	 * @param pName is name of FoodCategory to be instantiated
	 * 
	 * @pre pName != null
	 * 
	 * @post initializes new instance of FoodCategory
	 */
	private FoodCategory(String pName)
	{
		assert pName != null;
		aName = pName;
	}

	/*
	 * @param pName is name of FoodCategory to be retrieved or created
	 * 
	 * @pre pName != null
	 * 
	 * @post returns FoodCategory with name pName
	 */
	public static FoodCategory get(String pName)
	{
		assert pName != null;
		for (FoodCategory category : FOOD_CATEGORIES)
		{
			if (category.getName() == pName)
				return category;
		}
		FoodCategory newFoodCategory = new FoodCategory(pName);
		FOOD_CATEGORIES.add(newFoodCategory);
		return newFoodCategory;
	}
	
	public static List<FoodCategory> values() {
		return new ArrayList<>(FOOD_CATEGORIES);
	}

	/*
	 * @return aName
	 */
	public String getName()
	{
		return aName;
	}
}
