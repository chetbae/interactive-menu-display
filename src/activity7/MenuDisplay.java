package activity7;

import java.util.Comparator;
import java.util.function.Function;
import java.util.stream.Stream;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Skeleton code that illustrates the general layout expected. Modify as necessary.
 */
public class MenuDisplay extends Application
{	
	//Comparators for MenuDisplay
	private static final Comparator<MenuItem> sortByAlphabet = Comparator.comparing(MenuItem::getName);
//	private static final Comparator<MenuItem> sortByPrice = Comparator.comparing(MenuItem::getPrice).thenComparing(sortByAlphabet);
	
	//Change this below to choose your preferred sorting method; req #9
	public static final Comparator<MenuItem> DEFAULTSORT = sortByAlphabet;
	
	private static final MenuPanel panel1 = new MenuPanel(Menu.getInstance());
	private static final MenuPanel panel2 = new MenuPanel(Menu.getInstance());
	private static final MenuPanel panel3 = new MenuPanel(Menu.getInstance());
	
	/**
	 * Launches the application.
	 * @param pArgs This program takes no argument.
	 * @throws InterruptedException 
	 */
	public static void main(String[] pArgs) throws InterruptedException 
	{
		Menu.getInstance().registerObserver(panel1, panel2);
        launch(pArgs);
    }
    
	 @Override
	    public void start(Stage pStage) 
	    {
	    	pStage.setTitle("McMartin's");
	    	
	    	Image image = new Image("file:image.png", 650, 200, false, false);
			ImageView imageNode = new ImageView();
	        imageNode.setImage(image);	
	        
	    	BorderPane main = new BorderPane();
	    	main.setPadding(new Insets(20));
	    	main.setStyle("-fx-border-color: yellow;");
	    	main.setMinWidth(650);
	    	ScrollPane scrollpane = new ScrollPane();
	    	scrollpane.setContent(main);
	    	main.setMinWidth(650);
			
	    	main.setLeft(panel1);
	    	main.setCenter(panel3);
	    	main.setRight(panel2);
	    	main.setBottom(new Label(""));
	    	
	    	Label employee = new Label("==== For Employees Only ====");
	    	
	    	VBox wholePanel = new VBox(imageNode, 
	        									scrollpane, 
	        									createControl(),
	        									employee,
	        									createAddItemPanel(),
	        									createRemoveItemPanel());
	    	wholePanel.setAlignment(Pos.CENTER);
	        pStage.setScene(new Scene(wholePanel));
	        pStage.show();
	      
	    }

	private static HBox createControl()
    {
    	HBox control = new HBox();
    	control.setPadding(new Insets(5));
        control.setAlignment(Pos.CENTER);
        
        RadioButton button1 = new RadioButton("Drinks/Snacks");  
        RadioButton button2 = new RadioButton("3Cheap/3Expensive");
        
        Function<ModelData, Stream<MenuItem>> b1a = new Function<ModelData, Stream<MenuItem>>() {

			@Override
			public Stream<MenuItem> apply(ModelData t) {
				// TODO Auto-generated method stub
				return t.getItems().stream().filter(m -> m.getFoodCategory().getName().equals("drink"));
			}
        };
        Function<ModelData, Stream<MenuItem>> b1b = new Function<ModelData, Stream<MenuItem>>() {

			@Override
			public Stream<MenuItem> apply(ModelData t) {
				// TODO Auto-generated method stub
				return t.getItems().stream().filter(m -> m.getFoodCategory().getName().equals("snack"));
			}
        };
                Function<ModelData, Stream<MenuItem>> b2a = new Function<ModelData, Stream<MenuItem>>() {

			@Override
			public Stream<MenuItem> apply(ModelData t) {
				// TODO Auto-generated method stub
				return t.getItems().stream().sorted((a,b) -> Double.compare(a.getPrice(), b.getPrice())).limit(3);
			}
        };
        Function<ModelData, Stream<MenuItem>> b2b = new Function<ModelData, Stream<MenuItem>>() {

			@Override
			public Stream<MenuItem> apply(ModelData t) {
				// TODO Auto-generated method stub
				return t.getItems().stream().sorted((a,b) -> Double.compare(b.getPrice(), a.getPrice())).limit(3);
			}
        };
        
    	ToggleGroup group = new ToggleGroup();
    	button1.setToggleGroup(group);
    	button2.setToggleGroup(group);
    	control.getChildren().add(button1);
    	control.getChildren().add(button2);
    	
    	//from geeksforgeeks
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>()  
        { 
            public void changed(ObservableValue<? extends Toggle> ob, Toggle o, Toggle n) 
            { 
  
                RadioButton rb = (RadioButton)group.getSelectedToggle(); 
  
                if (rb != null) { 
                    if( rb.getText().equals("Drinks/Snacks")){
                    	panel1.updateSetting("Drinks", b1a);
                    	panel2.updateSetting("Snacks", b1b);
                    	System.out.println("1");
                    }
                    else if( rb.getText().equals("3Cheap/3Expensive")){
                    	panel1.updateSetting("Cheapest 3", b2a);
                    	panel2.updateSetting("Most Expensive 3", b2b);
                    	System.out.println("2");

                    }
                } 
            } 
        }); 
    	
    	
        ((RadioButton)control.getChildren().get(0)).setSelected(true);
        return control;
    }
        
    
    /**
     * @param pName != null
     * @param pPrice >= 0
     */
    private void addToMenu(String pName, String pPrice, String pType, 
    		boolean isSpecial, String pSpecialDiscount, boolean isSizeable, String pSizeableDif, 
    		CheckBox[] pDiets)
    {
    	try
    	{
    		//Checking
    		MenuItem finalFood;
    		SingleFood baseFood = new SingleFood(pName, FoodCategory.get(pType), Double.parseDouble(pPrice));
    		
    		for( CheckBox diet : pDiets )
    		{
    			if( diet.isSelected() ) baseFood.addDietaryCategory(DietaryCategory
    					.valueOf(diet.getText().toUpperCase()));
    		}
    		finalFood = baseFood;
    		
    		if( isSpecial ) finalFood = new SpecialDecorator(finalFood, (Double.parseDouble(pSpecialDiscount)/100));
    		if( isSizeable ) finalFood = new SizeDecorator(finalFood, Integer.parseInt(pSizeableDif));
    		
	    	Menu.getInstance().addItem(finalFood);
    	
    	} catch (Exception e) 
    	{
    		System.out.println("Please make sure you have entered proper values!");
    	}
    }
    
    /**
     * Creates a panel for inputting and creating MenuItems
     */
    private VBox createAddItemPanel()
    {
    	//Name input
    	Label name = new Label("Name: ");
    	TextField name1 = new TextField();
    	name1.setMaxWidth(100);
    	
    	//Price input
    	Label price = new Label("	Price: ");
    	TextField price1 = new TextField();
    	price1.setMaxWidth(100);

    	//Category combobox
    	Label category = new Label("	Category: ");
    	TextField category1 = new TextField();
    	price1.setMaxWidth(100);

    	//Diet checkboxes
    	Label diet = new Label("      Diet Options: ");
    	CheckBox vege = new CheckBox();
    	vege.setText("Vegetarian");
    	CheckBox vegan = new CheckBox();
    	vegan.setText("Vegan");
    	CheckBox gf = new CheckBox();
    	gf.setText("Gluten_Free");
    	VBox dietOptions = new VBox(vege, vegan, gf);

    	//Second row
    	Label option = new Label("Options: 	");
    	
    	//Special option
    	CheckBox special = new CheckBox();
    	special.setText("On Special ");
    	TextField special2 = new TextField();
    	special2.setPromptText("Discount(%)");
    	special2.setMaxWidth(100);

    	//Sizeable option
    	CheckBox size = new CheckBox();
    	size.setText("Sizeable ");
    	TextField size2 = new TextField();
    	size2.setPromptText("Difference($)");
    	size2.setMaxWidth(100);

    	//Button
    	Button bt1 = new Button("Add Item");    	
    	
    	HBox addTop = new HBox(name, name1, price, price1, category, category1, new Label("     "), bt1);
    	HBox addBottom = new HBox(option, special, special2, new Label("     "), size, size2, diet, dietOptions);
    	VBox addPanel = new VBox(new Label("Add Items: "), addTop, new Label(), addBottom);
        	
    	//Add listener to add to menu for button
    	bt1.setOnAction( event -> addToMenu(
    								name1.getText(), 
    								price1.getText(), 
    								category1.getText(),
    								special.isSelected(), special2.getText(),
    								size.isSelected(), size2.getText(),
    								new CheckBox[] {vege, vegan, gf}));
    	
   		addPanel.setPadding(new Insets(20));
   		addPanel.setStyle("-fx-border-color: red;");
    	return addPanel;
    }
    
    /**
     * Create Panel for removing MenuItems
     */
    private VBox createRemoveItemPanel()
   	{
    	//Name input
    	Label name = new Label("Name: ");
    	TextField name1 = new TextField();
    	
    	Button bt = new Button("Remove Item");
    	
    	bt.setOnAction( event -> Menu.getInstance().removeItem(name1.getText().toLowerCase()));
    	
   		VBox removePanel = new VBox(new Label("Remove Items: "), new HBox(name, name1, new Label("      "), bt));
   		removePanel.setPadding(new Insets(20));
   		removePanel.setStyle("-fx-border-color: red;");
   		return removePanel;
   	}
}
