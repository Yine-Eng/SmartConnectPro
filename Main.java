import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.ToggleGroup;
import javafx.collections.ObservableList;

/**
   Smartphone Packages
*/

public class Main extends Application
{
  private CheckBox insuranceCheckBox;
  private CheckBox hotspotCheckBox;
  private RadioButton gig8RadioButton;
  private RadioButton gig16RadioButton;
  private RadioButton gig20RadioButton;
  private ToggleGroup dataGroup;
  private Label totalCostLabel;
  private RadioButton model100RadioButton;
  private RadioButton model110RadioButton;
  private RadioButton model200RadioButton;
  private ToggleGroup phoneGroup;
  private Label optionsCostLabel;
  private final double TAX = 1.06;
  
   public static void main(String[] args)
   {
      // Launch the application.
      launch(args);
   }

   @Override
   public void start(Stage primaryStage)
   {
      // Data plan radio buttons
      gig8RadioButton = new RadioButton("8 Gigabytes per" + 
                                        " month");
      gig16RadioButton = new RadioButton("16 Gigabytes per"+ 
                                         " month");
      gig20RadioButton = new RadioButton("20 Gigabytes per"+ 
                                         " month");
      gig8RadioButton.setSelected(true);
      dataGroup = new ToggleGroup();
      gig8RadioButton.setToggleGroup(dataGroup);
      gig16RadioButton.setToggleGroup(dataGroup);
      gig20RadioButton.setToggleGroup(dataGroup);
      Label dataPrompt = new Label("Select a Data Plan:");
      Label dataDescriptor = new Label("Data Plan Cost:");
      Label dataCostLabel = new Label("$45 per month");
      HBox dataCostHBox = new HBox(10, dataDescriptor, 
                                   dataCostLabel);  
      VBox dataVBox = new VBox(10, dataPrompt, 
                               gig8RadioButton, 
                               gig16RadioButton,
                               gig20RadioButton, 
                               dataCostHBox);

      // Register event handlers for the data plan radio 
      //buttons
      gig8RadioButton.setOnAction(event ->
      {
         dataCostLabel.setText("$45.00 per month");
      });

      gig16RadioButton.setOnAction(event ->
      {
         dataCostLabel.setText("$65.00 per month");
      });

      gig20RadioButton.setOnAction(event ->
      {
         dataCostLabel.setText("$99.00 per month");
      });

      // Phone radio buttons
     model100RadioButton = new RadioButton("Model 100");
     model110RadioButton = new RadioButton("Model 110");
     model200RadioButton = new RadioButton("Model 200");
     model100RadioButton.setSelected(true);
     phoneGroup = new ToggleGroup();
     model100RadioButton.setToggleGroup(phoneGroup);
     model110RadioButton.setToggleGroup(phoneGroup);
     model200RadioButton.setToggleGroup(phoneGroup);
     Label phonePrompt = new Label("Select a phone:");
     Label phoneDescriptor = new Label("Phone Cost:");
     Label phoneCostLabel = new Label("$299.95");
     HBox phoneCostHBox = new HBox(10, phoneDescriptor, 
                                   phoneCostLabel);
     VBox phoneVBox = new VBox(10, 
                              phonePrompt,
                              model100RadioButton,
                              model110RadioButton,
                              model200RadioButton,
                              phoneCostHBox);

      // Register event handlers for the phone radio buttons
     model100RadioButton.setOnAction(event ->
     {
       phoneCostLabel.setText("$299.95");
     });

     model110RadioButton.setOnAction(event ->
     {
       phoneCostLabel.setText("$399.95");
     });

     model200RadioButton.setOnAction(event ->
     {
       phoneCostLabel.setText("$499.95");
     });

     Label totalDescriptor = new Label("Total Cost:");
     totalCostLabel = new Label("$362.95");
     HBox totalCostHBox = new HBox(10, totalDescriptor, 
                                   totalCostLabel);

      // Options
      insuranceCheckBox = new CheckBox("Phone Replacement" + 
                                       " Insurance");
      hotspotCheckBox = new CheckBox("WiFi Hotspot " + 
                                     "Capabilty");
      Label optionsDescriptor = new Label("Options Cost:");
      optionsCostLabel = new Label("$0.00");
      HBox optionsCostHBox = new HBox(10, optionsDescriptor, 
                                      optionsCostLabel);
      VBox optionsVBox = new VBox(10, insuranceCheckBox, 
                                  hotspotCheckBox, 
                                  optionsCostHBox);
  
     // Register event handlers for the check boxes
      insuranceCheckBox.setOnAction(event ->
      {
        updateTotalCost();
      });

      hotspotCheckBox.setOnAction(event ->
      {
        updateTotalCost();
      });

     dataGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
       {
       updateTotalCost();
       });

     phoneGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) ->
      {
      updateTotalCost();
      });

      // Put everything into a VBox
      VBox mainVBox = new VBox(10, dataVBox, phoneVBox, 
                               optionsVBox,
                              totalCostHBox);
      mainVBox.setAlignment(Pos.CENTER);
      mainVBox.setPadding(new Insets(10));

      // Add the main VBox to a scene.
      Scene scene = new Scene(mainVBox);
      scene.getStylesheets().add("smartphonepackages.css");
     
      // Set the scene to the stage aand display it.
      primaryStage.setScene(scene);
      primaryStage.setTitle("Smart Connect Pro");
       
      primaryStage.show();
   }

     private double getOptionsCost()
     {
       double cost = 0.00;
       if (insuranceCheckBox.isSelected())
       {
         cost += 5.00;
       }
       if (hotspotCheckBox.isSelected())
       {
         cost += 10.00;
       }
       return cost;
    }

  private void updateTotalCost()
  {
    double dataPlanCost = getDataPlanCost();
    double phoneCost = getPhoneCost();
    double optionsCost = getOptionsCost();
    optionsCostLabel.setText(String.format("$%,.2f", 
                                           optionsCost));

    double totalCost = dataPlanCost + phoneCost + 
                       optionsCost;
    totalCostLabel.setText(String.format("$%,.2f", 
                                         totalCost));
  }

  private double getDataPlanCost()
  {
    if (gig8RadioButton.isSelected())
    {
      return 45.00;
    }
    else if (gig16RadioButton.isSelected())
    {
      return 65.00;
    }
    else if (gig20RadioButton.isSelected())
    {
      return 99.00;
    }
    else
    {
      return 0.00;
    }
  }


  private double getPhoneCost()
  {
    if (model100RadioButton.isSelected())
    {
      return 299.95 * TAX;
    }
    else if (model110RadioButton.isSelected())
    {
      return 399.95 * TAX;
    }
    else if (model200RadioButton.isSelected())
    {
      return 499.95 * TAX;
    }
    else
    {
      return 0.00;
    }
  }
  
}
