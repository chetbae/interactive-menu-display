package activity7;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SingleFood implements MenuItem
{
	private String aName;
	private double aPrice;
	private FoodCategory aFoodCategory;
	private Set<DietaryCategory> aDietaryCategories = new HashSet<>();

	/*
	 * @param pName is the name of the food item
	 * 
	 * @param pFoodCategory is the category of the food item
	 * 
	 * @param pPrice is the price of the food item
	 * 
	 * @pre pName != null, pFoodCategory != null, pPrice > 0, pDietaryCategories[i] != null
	 */
	public SingleFood(String pName, FoodCategory pFoodCategory, double pPrice, DietaryCategory... pDietaryCategories)
	{
		assert pName != null;
		assert pFoodCategory != null;
		assert pPrice > 0;
		aName = pName;
		aFoodCategory = pFoodCategory;
		aPrice = Math.round(pPrice * 100.0) / 100.0;
		for (int i = 0; i < pDietaryCategories.length; i++)
		{
			assert pDietaryCategories[i] != null;
			aDietaryCategories.add(pDietaryCategories[i]);
		}
	}

	public void setDiets(DietaryCategory... pDietaryCategory)
	{
		for (DietaryCategory d : pDietaryCategory)
		{
			aDietaryCategories.add(d);
		}
	}

	/*
	 * @return String describing MenuItem
	 */
	public String description()
	{
		return aName + "\t$" + String.format("%.2f", aPrice);
	}

	/*
	 * @return aFoodCategory
	 */
	public FoodCategory getFoodCategory()
	{
		return aFoodCategory;
	}

	/*
	 * @param pFoodCategory is the category to be added
	 * 
	 * @pre pFoodCategory != null
	 * 
	 * @post set aFoodCategory to pFoodCategory
	 */
	public void setFoodCategory(FoodCategory pFoodCategory)
	{
		assert pFoodCategory != null;
		aFoodCategory = pFoodCategory;
	}

	@Override
	// the only criteria for equals is the name
	public boolean equals(Object o)
	{
		MenuItem newItem = (MenuItem) o;
		assert newItem != null;
		return newItem.getName().equals(this.getName());
	}

	public MenuItem deepCopy()
	{
		return new SingleFood(getName(), getFoodCategory(), getPrice());
	}

	/*
	 * @param pDietaryCategory is the category to be added
	 * 
	 * @pre pDietaryCategory != null
	 * 
	 * @pre ! aDietaryCategories.contains(pDietaryCategory)
	 * 
	 * @post adds pDietaryCategory to aDietaryCategories
	 */
	public void addDietaryCategory(DietaryCategory pDietaryCategory)
	{
		assert pDietaryCategory != null;
		aDietaryCategories.add(pDietaryCategory);
	}

	/*
	 * 
	 * @param pDietaryCategory is the DietaryCategory to be removed
	 * 
	 * @post removes pDietaryCategory from aDietaryCategories
	 */
	public void removeDietaryCategory(DietaryCategory pDietaryCategory)
	{
		aDietaryCategories.remove(pDietaryCategory);
	}

	/*
	 * @pre pPrice > 0
	 * 
	 * @post sets aPrice to pPrice
	 */

	public void setPrice(double pPrice)
	{
		assert pPrice > 0;
		aPrice = Math.round(pPrice * 100.0) / 100.0;
	}

	/*
	 * @return aName
	 */
	@Override
	public String getName()
	{
		return aName;
	}

	/*
	 * @return aPrice
	 */
	@Override
	public double getPrice()
	{
		return aPrice;
	}

	/*
	 * @return an unmodifiable list of the item's dietary categories
	 */
	@Override
	public Set<DietaryCategory> getDietaryCategories()
	{
		return Collections.unmodifiableSet(aDietaryCategories);
	}

	public String descriptionWithoutPrice()
	{
		return getName();
	}
}
