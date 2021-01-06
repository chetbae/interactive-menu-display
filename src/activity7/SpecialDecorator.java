package activity7;

public class SpecialDecorator extends AbstractDecorator
{
	private double aDiscount; // ratio of price that is reduced

	/*
	 * @pre pMenuItem != null
	 * 
	 * @pre pDiscount >= 0
	 * 
	 * @post initializes instance of SpecialDecorator
	 */
	public SpecialDecorator(MenuItem pMenuItem, double pDiscount)
	{
		super(pMenuItem);
		assert pDiscount >= 0;
		aDiscount = pDiscount;
	}

	/*
	 * @return price with discount
	 */
	@Override
	public double getPrice()
	{
		return super.aMenuItem.getPrice() * (1.00 - aDiscount);
	}

	/*
	 * @return aDiscount
	 */
	public double getDiscount()
	{
		return aDiscount;
	}

	/*
	 * @param pDiscount is discount to be attached to special
	 * 
	 * @pre pDiscount >= 0
	 * 
	 * @post sets discount
	 */
	public void setDiscount(double pDiscount)
	{
		assert pDiscount >= 0;
		aDiscount = pDiscount;
	}

	@Override
	protected String subDescription()
	{

		return aDiscount * 100 + "% off!";

	}

//	public static void main(String[] args)
//	{
//		MenuItem m1 = new SingleFood("Burger", FoodCategory.get("Sandwich"), 2.00);
//		MenuItem m4 = new SingleFood("Coke", FoodCategory.get("Drink"), 1.50);
//
//		MenuItem c1 = new ComboItem("Burger and Coke", 0.50, FoodCategory.get("Combo"), m1, m4);
//		MenuItem mc1 = new SpecialDecorator(c1, 0.10);
//		MenuItem mc2 = new SizeDecorator(mc1, 0.1);
//
//		MenuItem m3 = new SpecialDecorator(m1, .20);
//		MenuItem m2 = new SizeDecorator(m3, .50);
//
//		SizeDecorator sd = new SizeDecorator(m4, 0.50);
//		MenuItem smallsd = sd.getItem(Size.SMALL);
//		MenuItem specialSsd = new SpecialDecorator(smallsd, 0.10);
//
//		MenuItem c2 = new ComboItem("Burger and Small Coke", 0.2, FoodCategory.get("Combo"), m1, smallsd);
//		MenuItem sd1 = new SizeDecorator(c2, 0.10);
//		MenuItem spsd1 = new SpecialDecorator(sd1, 0.10);
//
//		System.out.println(spsd1.description());
//		MenuItem spc2 = new SpecialDecorator(c2, 0.10);
//
//		System.out.println(sd1.description());
//
//		System.out.println(smallsd.description());
//		System.out.println(specialSsd.description());
//		System.out.println(c2.description());
//		System.out.println(spc2.description());
//
		// MenuItem m3 = new SpecialDecorator(m2, .20);

		// System.out.println(c1.description());
		// System.out.println(mc1.description());
		// System.out.println(mc2.description());
		// System.out.println(m3.description());
		// System.out.println(m1.description());
		// System.out.println(m2.description());
//
//	}
}
