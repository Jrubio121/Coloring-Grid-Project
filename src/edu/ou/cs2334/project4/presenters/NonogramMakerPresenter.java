// Worked on this class with Keeton, John, and Guillermo
// Plus additional support from people on discord


package edu.ou.cs2334.project4.presenters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import edu.ou.cs2334.project4.handlers.OpenHandler;
import edu.ou.cs2334.project4.handlers.SaveHandler;
import edu.ou.cs2334.project4.handlers.ToggleButtonEventHandler;
import edu.ou.cs2334.project4.interfaces.Openable;
import edu.ou.cs2334.project4.interfaces.Saveable;
import edu.ou.cs2334.project4.models.NonogramMakerModel;
import edu.ou.cs2334.project4.views.NonogramMakerView;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Window;

/**
 * The NonogramMakerPresenter is the actual brain of the Nonogram puzzle maker, it connects the 
 * model data with the graphical interface to be able to use the Nonogram puzzle maker itself.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since April 10, 2022
 */

public class NonogramMakerPresenter implements Openable, Saveable {

	private NonogramMakerView view;
	private NonogramMakerModel model;
	private int cellLength;
	
	/**
	 * This method constructs NonogramMakerPresenter given the number of rows and colums and the length of the cell.
	 * @param numRows is the number of rows given.
	 * @param numCols is the number of columns given.
	 * @param cellLength is the length of the given cell.
	 */
	public NonogramMakerPresenter(int numRows, int numCols, int cellLength) {
		
		model = new NonogramMakerModel(numRows, numCols);
		view = new NonogramMakerView(numRows, numCols, cellLength);
		this.cellLength = cellLength;
		
		init();
		
	}
	
	/**
	 * This method returns the view's pane's scene's window and catches any NullPointerExceptions.
	 * @return The view's pane's scene's window.
	 */
	private Window getWindow() {
		
		try {
			return view.getPane().getScene().getWindow();
		}
		catch(NullPointerException e) {
			return null;
		}
		
	}
	
	/**
	 * This method initializes the toggle buttons, binds the toggle buttons to the model, 
	 * and configures the menu item actions.
	 */
	private void init() {
		
		initToggleButtons();
		bindToggleButtons();
		configureMenuItems();
	}
	
	/**
	 * This method calls the view's initButtons method using the model's row and column count
	 * if the window is not null it executes getWindow().sizeToScene().
	 */
	private void initToggleButtons() {
		
		view.initButtons(model.getNumRows(), model.getNumCols(), cellLength);
		
		if(getWindow() != null) {
			getWindow().sizeToScene();
		}
	}
	
	/**
	 * This method checks if the view's toggle button matches the model's cell state and
	 * adds a ToggleButtonEventHandler to each of them.
	 */
	private void bindToggleButtons() {
		
		for(int row = 0; row < model.getNumRows(); ++row) {
			
			for(int col =0; col <model.getNumCols(); ++col) {
				
				view.getToggleButton(row, col).setSelected(model.getCell(row, col));
				ToggleButtonEventHandler temp = new ToggleButtonEventHandler(model, row, col);
				ActionEvent event = new ActionEvent();
				view.getToggleButton(row, col).addEventHandler(ActionEvent.ACTION, temp);
				
			}
		}
	}
	
	/**
	 * This method sets the event handlers for the open and save buttons in order to make them function.
	 */
	private void configureMenuItems() {
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileChooser.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_OPEN)
		    .setOnAction(new OpenHandler(getWindow(), fileChooser, this));
		
		FileChooser fileSave = new FileChooser();
		fileSave.setTitle("Save");
		fileSave.setInitialFileName("save");
		fileSave.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"));
		fileSave.setInitialDirectory(new File("."));
		view.getMenuItem(NonogramMakerView.MENU_ITEM_SAVE)
		   .setOnAction(new SaveHandler(getWindow(), fileSave, this));

			
	}
	
	/**
	 * This method returns the Pane associated with this view.
	 * @return The pane associated with this view.
	 */
	public Pane getPane() {
		return view.getPane();
	}
	
	/**
	 * This method opens a given file by constructing a NonogramMakerModel with the given file.
	 * @throws IOException is thrown if the file given contains unreadable data.
	 */
	public void open(File file) throws IOException {
		
		model = new NonogramMakerModel(file);
		init();
	}
	
	/**
	 * This method calls the model's saveToFile method given the filename in order to save the model to the file.
	 * @throws FileNotFoundException is thrown is the given file is not found.
	 */
	public void save(String filename) throws FileNotFoundException {
		
		model.saveToFile(filename);
	}
	
}
