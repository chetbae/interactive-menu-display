package activity7;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class ComboItem implements MenuItem {
	private final List<MenuItem> aMenuItems = new ArrayList<>();
	private double aDiscount;
	private String aName;
	private FoodCategory aFoodCategory;

	public ComboItem(String pName, double pDiscount, FoodCategory pFoodCategory, MenuItem... pMenuItems) {
		aDiscount = pDiscount;
		aName = pName;
		aFoodCategory = pFoodCategory;

		for (int i = 0; i < pMenuItems.length; i++) {
			aMenuItems.add(pMenuItems[i]);
		}
	}

	/*
	 * @param pMenuItem is the MenuItem to be added to the combo
	 * 
	 * @pre pMenuItem != null
	 * 
	 * @post adds pMenuItem to aMenuItems
	 */
	public void addMenuItem(MenuItem pMenuItem) {
		assert pMenuItem != null;
		aMenuItems.add(pMenuItem);
	}

	/*
	 * @param pMenuItem is the MenuItem to be removed from the combo
	 * 
	 * @post removes pMenuItem from aMenuItems
	 */
	public void removeMenuItem(MenuItem pMenuItem) {
		aMenuItems.remove(pMenuItem);
	}

	/*
	 * @return names of all items in combo
	 */
	@Override
	public String getName() {
		return aName;
	}

	/*
	 * @return price of combo
	 */
	@Override
	public double getPrice() {
		double toReturn = 0.0;

		for (MenuItem item : aMenuItems) {
			toReturn += item.getPrice();
		}
		// return Double.valueOf(String.format("%.2f", toReturn * (1 - aDiscount)));
		return toReturn * (1 - aDiscount);

	}

	/*
	 * @return aFoodCategory
	 */
	public FoodCategory getFoodCategory() {
		return aFoodCategory;
	}

	/*
	 * @return discount for combo
	 */
	public double getDiscount() {
		return aDiscount;
	}

	/*
	 * @param pDiscount is discount to be applied to combo
	 * 
	 * @pre pDiscount >= 0
	 * 
	 * @post set aDiscount to pDiscount
	 */
	public void setDiscount(double pDiscount) {
		assert pDiscount > 0;
		aDiscount = pDiscount;
	}

	/*
	 * Runs in O(nm) but n and m are likely to always be relatively small
	 * 
	 * @return list of dietary categories common between all items
	 */
	@Override
	public Set<DietaryCategory> getDietaryCategories() {
		Set<DietaryCategory> toReturn = new HashSet<>(aMenuItems.get(0).getDietaryCategories());
		Set<DietaryCategory> toRemove = new HashSet<>();
		for (MenuItem item : aMenuItems) {

			for (DietaryCategory category : toReturn) {

				if (!item.getDietaryCategories().contains(category))
					toRemove.add(category);
			}
		}
		toReturn.removeAll(toRemove);
		return toReturn;
	}

	/*
	 * @return String describing item
	 */
	@Override
	public String description() {
		return getName() + "\t$" + String.format("%.2f", getPrice());
	}

	public MenuItem deepCopy() {
		return new ComboItem(this.aName, this.getDiscount(), this.aFoodCategory, (MenuItem[]) aMenuItems.toArray());
	}

	@Override
	public String descriptionWithoutPrice() {
		return "Combo: " + getName();
	}
}
