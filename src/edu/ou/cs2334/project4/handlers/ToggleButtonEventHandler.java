
package edu.ou.cs2334.project4.handlers;

import edu.ou.cs2334.project4.models.NonogramMakerModel;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;

/**
 * The ToggleButtonEventHandler is a class that implements EventHandler in order to update the cell
 * in the model with the corresponding state of the toggle button on the grid.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since April 10, 2022
 */
public class ToggleButtonEventHandler implements EventHandler<ActionEvent> {

	private NonogramMakerModel model;
	private int rowIdx;
	private int colIdx;
	
	/**
	 * This Method constructs a ToggleButtonEventHandler given a model and the index of both row and column.
	 * @param model the given NonogramMakerModel 
	 * @param rowIdx the index of the row given.
	 * @param colIdx the index of the column given.
	 */
	public ToggleButtonEventHandler(NonogramMakerModel model, int rowIdx, int colIdx) {
		
		this.model = model;
		this.rowIdx = rowIdx;
		this.colIdx = colIdx;
		
	}
	
	/**
	 * This method gets the current state of the toggle button and sets the cell in the model
	 * at the same position with the same boolean value.
	 */
	public void handle(ActionEvent event) {
		
		ToggleButton temp = (ToggleButton)event.getSource();
		model.setCell(rowIdx, colIdx, temp.isSelected());
		
	}
	

}
