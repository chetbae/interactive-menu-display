package activity7;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuPanel extends Parent implements Observer
{
	private Label aTitle = new Label("default");
	private VBox aPanel = new VBox();
	private ModelData aModel;
	
	public MenuPanel(ModelData pModel) 
	{
//		VBox p = new VBox();
//		aPanel.setStyle(STYLE);
//    	aPanel.setPrefWidth(200);
//    	Label title = new Label("Title");
//    	title.setStyle("-fx-font-weight: bold");
//    	aPanel.getChildren().add(title);
//    	aPanel = p;
		aModel = pModel;
		getChildren().add(new VBox(aTitle, new Label(), aPanel));
		update();
    }

	@Override
	public void update()
	{
		aPanel.getChildren().clear();
		
		aModel.getItems()
			.stream()
			.sorted(MenuDisplay.DEFAULTSORT)
			.forEach( m -> addToLabel(m));
	}
	
	private void addToLabel(MenuItem pItem)
	{
		aPanel.getChildren().add(new Label(pItem.description()));
	}
	
	private void addTitle(String pTitle)
	{
		aTitle.setText(pTitle);;
	}
	
//	public static void main(String[] pArgs) 
//	{
//		
//		ModelData md = Menu.getInstance();
//		MenuPanel mp = new MenuPanel(md);
//		
//	}
}
