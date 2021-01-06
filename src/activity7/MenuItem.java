package activity7;

import java.util.Set;

public interface MenuItem {
	/*
	 * @return name of MenuItem
	 */
	public String getName();

	/*
	 * @return price of MenuItem
	 */
	public double getPrice();

	/*
	 * @return FoodCategory of MenuItem
	 */
	public FoodCategory getFoodCategory();

	/*
	 * @return String describing MenuItem
	 */
	public default String description() {
		String toReturn = getName() + "\t";
		for (DietaryCategory dc : getDietaryCategories()) {
			toReturn += dc.toString() + "\t";
		}
		toReturn += "$" + getPrice();
		return toReturn;
	}

	/*
	 * @return list of dietary categories applying to MenuItem
	 */
	public Set<DietaryCategory> getDietaryCategories();

	public String descriptionWithoutPrice();

	public MenuItem deepCopy();
}
