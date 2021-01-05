package activity7;

import java.util.Set;

public abstract class AbstractDecorator implements MenuItem
{
	protected MenuItem aMenuItem;

	protected AbstractDecorator(MenuItem pMenuItem)
	{
		assert pMenuItem != null;
		this.aMenuItem = pMenuItem;
	}

	/*
	 * @return aMenuItem.getName()
	 */
	@Override
	public String getName()
	{
		return aMenuItem.getName();
	}

	/*
	 * @return price of the item
	 */
	@Override
	public abstract double getPrice();

	/*
	 * @return aFoodCategory
	 */
	public FoodCategory getFoodCategory()
	{
		return this.aMenuItem.getFoodCategory();
	}

	/*
	 * @return a String describing the item
	 */
	@Override
	public final String description()
	{
		String[] desc = this.aMenuItem.description().split("\\t"); // splits the string on tab
		String toReturn = desc[0] + "\t" + subDescription() + "\t";
		for (int i = 1; i < desc.length - 1; i++)
		{
			toReturn += desc[i] + "\t";
		}

		toReturn += "$" + String.format("%.2f", getPrice()); // use getPrice() to account for change to price

		return toReturn;

	}

	/*
	 * @return appropriate modified description of item
	 */
	protected abstract String subDescription();

	/*
	 * @return aMenuItem.getDietaryCategories
	 */
	@Override
	public Set<DietaryCategory> getDietaryCategories()
	{
		return aMenuItem.getDietaryCategories();
	}

	public String descriptionWithoutPrice()
	{
		return aMenuItem.descriptionWithoutPrice();
	}

	public MenuItem deepCopy()
	{
		return aMenuItem.deepCopy();
	}
}