package activity7;

public class SizeDecorator extends AbstractDecorator implements Sizeable {

	private double aPriceChange;

	public SizeDecorator(MenuItem pMenuItem, double pPriceChange) {
		super(pMenuItem);
		aPriceChange = pPriceChange;
	}

	@Override
	public double getPrice() {
		return super.aMenuItem.getPrice();
	}

	public double getPrice(Size pSize) {
		double price = (pSize.ordinal() - 1) * aPriceChange + this.getPrice(); // MEDIUM equals default price

		return price;
	}

	@Override
	protected String subDescription() {

		return Size.SMALL.toString() + " $" + String.format("%.2f", getPrice(Size.SMALL)) + "\t"
				+ Size.MEDIUM.toString() + " $" + String.format("%.2f", getPrice(Size.MEDIUM)) + "\t"
				+ Size.LARGE.toString() + " $" + String.format("%.2f", getPrice(Size.LARGE));
	}

	public MenuItem getItem(Size pSize) {
		return new SizeItem(this.aMenuItem, this.aPriceChange, pSize);
	}

	private class SizeItem extends SizeDecorator {
		private Size aSize;

		protected SizeItem(MenuItem pMenuItem, double pPriceChange, Size pSize) {

			super(pMenuItem, pPriceChange);
			this.aSize = pSize;
		}

		@Override
		public double getPrice() {
			return (aSize.ordinal() - 1) * aPriceChange + super.aMenuItem.getPrice();
		}

		@Override
		protected String subDescription() {
			return aSize.toString();

		}
	}
}
