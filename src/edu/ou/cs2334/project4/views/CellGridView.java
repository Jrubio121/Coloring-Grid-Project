// Worked on this class with Keeton, John, and Guillermo 

package edu.ou.cs2334.project4.views;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * The CellGridView class created a grid pane filled with toggle buttons, it is essentially the primary component
 * of the Nonogram puzzle maker as it allows the user to draw an image; updating the cell states as it goes.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since April 10, 2022
 */
public class CellGridView {

	private ArrayList<ToggleButton> gridButtons;
	private GridPane gridPane;
	private int numRows;
	private int numCols;
	
	/**
	 * This method constructs a CellGridView given the number of rows, columns, and the length of the cell.
	 * @param numRows is the number of rows given.
	 * @param numCols is the number of columns given.
	 * @param cellLength is the length of the given cell.
	 */
	public CellGridView(int numRows, int numCols, int cellLength) {
		
		gridButtons = new ArrayList<ToggleButton>();
		gridPane = new GridPane();
		
		this.numRows = numRows;
		this.numCols = numCols;
		gridPane.setAlignment(Pos.CENTER);
		
		initButtons(numRows, numCols, cellLength);
		
	}
	/**
	 * This method creates buttons based on the number of rows, columns and the length of the cell.
	 * @param numRows the number of rows given.
	 * @param numCols the number of columns given.
	 * @param cellLength the length of the given cell.
	 */
	public void initButtons(int numRows, int numCols, int cellLength) {
		
		gridButtons.clear();
		gridPane.getChildren().clear();
		
		this.numCols = numCols;
		this.numRows = numRows;
		
		for(int i =0; i < numRows * numCols; ++i) {
			
			ToggleButton hi = new ToggleButton();
			hi.setMinHeight(cellLength);
			hi.setMaxHeight(cellLength);
			hi.setPrefHeight(cellLength);
			hi.setMinWidth(cellLength);
			hi.setMaxWidth(cellLength);
			hi.setPrefWidth(cellLength);
			
			gridButtons.add(hi);
			gridPane.add(hi, i % numCols,i / numCols);
		}
		
	}
	
	/**
	 * This method takes a row and column index and returns the ToggleButton at the given position.
	 * @param row the index of the given row.
	 * @param col the index of the given column.
	 * @return The toggle button at the given position.
	 */
	public ToggleButton getToggleButton(int row, int col) {
		return gridButtons.get(row*numCols + col); 
	}
	
	/**
	 * This method returns the number of rows.
	 * @return the number of rows.
	 */
	public int getnNumRows() {
		return numRows;
	}
	
	/**
	 * This method returns the number of columns.
	 * @return the number of columns 
	 */
	public int getNumCols() {
		return numCols;
	}
	
	/**
	 * This method returns the Pane associated with this view.
	 * @return The pane associated with this view.
	 */
	public Pane getPane() {
		return gridPane;
	}
}
