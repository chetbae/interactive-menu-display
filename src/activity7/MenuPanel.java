package activity7;

import java.util.function.Function;
import java.util.stream.Stream;

import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MenuPanel extends Parent implements Observer {
	private Label aTitle = new Label("default");
	private VBox aPanel = new VBox();
	private ModelData aModel;
	private Function<ModelData, Stream<MenuItem>> aConfig = new Function<ModelData, Stream<MenuItem>>(){

		@Override
		public Stream<MenuItem> apply(ModelData t) {
			// TODO Auto-generated method stub
			return t.getItems().stream();
		}
		
	};

	public MenuPanel(ModelData pModel) {
		aModel = pModel;
		getChildren().add(new VBox(aTitle, new Label(), aPanel));
		update();
	}

	public void updateSetting(String pName, Function<ModelData, Stream<MenuItem>> pfunction) {
		aConfig = pfunction;
		aTitle.setText(pName);
		update();
	}

	@Override
	public void update() {
		aPanel.getChildren().clear();
		Stream<MenuItem> temp = aConfig.apply(aModel);
		temp.sorted(MenuDisplay.DEFAULTSORT).forEach(m -> addToLabel(m));
	}

	private void addToLabel(MenuItem pItem) {
		aPanel.getChildren().add(new Label(pItem.description()));
	}

}
