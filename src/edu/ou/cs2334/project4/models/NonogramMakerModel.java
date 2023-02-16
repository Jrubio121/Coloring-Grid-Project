// Worked on this class with Keeton, John, and Guillermo 

package edu.ou.cs2334.project4.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * The NonogramMakerModel class contains the actual functionality of the Nonogram puzzle maker.
 * It encapsulated a one-dimensional array of boolean values representing the state of each cell in the grid.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since April 10, 2022
 */
public class NonogramMakerModel {

	private static char EMPTY_CELL_CHAR = '0';
	private static char FILLED_CELL_CHAR = '1';
	private int numRows;
	private int numCols;
	
	/**
	 * This is a boolean array that represents the grid of the model. True represents 1 while false 
	 * represents 0.
	 */
	public boolean[] grid;
	
	

	/**
	 * This method constructs a Nonogram Model given the number of rows and columns. 
	 * @param numRows the number of rows in the model
	 * @param numCols the number of columns in the model
	 */
	public NonogramMakerModel(int numRows, int numCols) {
		this.numRows = numRows;
		this.numCols = numCols;
		
		if(numRows <= 0 || numCols <= 0)
			throw new IllegalArgumentException();
		
		grid = new boolean[numRows * numCols];
		 
	}
	
	/**
	 * This method constructs a Nonogram Model given a file with the grid, rows, and columns.
	 * @param file is a file given to construct a Nonogram Model.
	 * @throws IOException is thrown if the file doesn't contain proper data.
	 */
	public NonogramMakerModel(File file) throws IOException {

		String temp;
		Scanner scnr = new Scanner(file);
		numRows = scnr.nextInt();
		numCols = scnr.nextInt();

		grid = new boolean[numRows * numCols];
		
		scnr.nextLine();

		for(int i = 0; i < numRows + numCols; ++i) {
		scnr.nextLine();
		}

		for(int i = 0; i < numRows; ++i) {
			temp = scnr.nextLine();
			for(int j = 0; j < temp.length();++j) {
				if(temp.charAt(j) == FILLED_CELL_CHAR) {
					setCell(i,j,true);
				}
			}
		}
		

		scnr.close();
}
	/**
	 * This method constructs a Nonogram Model given the name of a file that consists of the grid, rows, and columns.
	 * @param filename is the name of a file used to construct the Nonogram model.
	 * @throws IOException is thrown if the file doesn't contain proper data.
	 */
	public NonogramMakerModel(String filename) throws IOException {

		 NonogramMakerModel temp = new NonogramMakerModel(new File(filename));
		 numRows = temp.getNumRows();
		 numCols = temp.getNumCols();
		 grid = temp.getGrid();

		 
	}
	
	/**
	 * This method returns the boolean array that represents the grid.
	 * @return the boolean array grid.
	 */
	public boolean[] getGrid() {
		return grid; 
	}
	
	/**
	 * This method returns a boolean value representing a cell at a current location, given the row and column index
	 * @param rowIdx is the int index of the row current.
	 * @param colIdx is the int index of the current column.
	 * @return the cell of the grid at the given row and column index.
	 */
	public boolean getCell(int rowIdx, int colIdx) {
		
		return grid[rowIdx*numCols + colIdx];
	}
	
	/**
	 * This method sets the state of a cell at a current location, given the row and column index.
	 * @param rowIdx is the int index of the row current.
	 * @param colIdx is the int index of the current column.
	 * @param state is the boolean value that the cell is being set to.
	 */
	public void setCell(int rowIdx, int colIdx, boolean state) {
		grid[rowIdx*numCols + colIdx] = state;
	}
	
	/**
	 * This method returns the number of rows in the model.
	 * @return the number of rows in the model.
	 */
	public int getNumRows() {
		return numRows;
	}
	
	/**
	 * This method returns the number of columns in the model.
	 * @return the number of columns in the model
	 */
	public int getNumCols() {
		return numCols;

	}

	/**
	 * This method projects and array returning the number of Nonogram numbers in a given array of cell states.
	 * @param cells is a boolean array that represents the cells in a model.
	 * @return The Nonogram numbers of a given array of cell states.
	 */
	public static List<Integer> project(boolean[] cells){
		
	ArrayList<Integer> temp = new ArrayList<Integer>();
	int trueCount = 0;
	for(int i = 0; i < cells.length; ++i) {
		if(cells[i]) {
			++trueCount;
		}
		else {
			if(trueCount > 0) {
				temp.add(trueCount);
				trueCount = 0;
			}
		}
	}
	if(trueCount > 0) {
		temp.add(trueCount);
	}
	if(temp.size() == 0) {
		temp.add(0);
	}
		return temp;
	}
	/**
	 * This method projects an array and returns the Nonogram numbers in a given row.
	 * @param rowIdx the int value of the row index
	 * @return The projection of the row with the given index.
	 */
	public List<Integer> projectRow(int rowIdx){
	
		boolean[] temp = new boolean[numCols];
		for(int i = 0; i < temp.length; ++i) {
			temp[i] = getCell(rowIdx,i);
		}
		return project(temp);
	
	}
	
	/**
	 * This method projects an array and returns the Nonogram numbers in a given column.
	 * @param colIdx the int value of the column index
	 * @return The projection of the column with the given index.
	 */
	public List<Integer> projectCol(int colIdx){
		
		boolean[] temp = new boolean[numRows];
		int count = 0;
		for(int i = 0; i < grid.length; ++i) {
			if(i % numCols == colIdx) {
			temp[count] = grid[i];
			++count;
			}
		}
		
		return project(temp);

	}
	
	/**
	 * This method saves the output of a toString to a text file with the given name.
	 * @param filename the name of a given file.
	 * @throws FileNotFoundException is thrown in a file isn't found.
	 */
	public void saveToFile(String filename) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(filename);
		writer.print(toString());
		writer.close();
	}
	
	/**
	 * This method converts the Nonogram puzzle into a string representation of 0's and 1's and returns it.
	 * @return The string representation of the Nonogram puzzle.
	 */
	public String toString() {
		
		String temp = "";
		List<Integer> integer;
		temp = temp + numRows + " " + numCols + System.lineSeparator();
		for(int i = 0; i < numRows; ++i) {
			integer = projectRow(i);
			for(int j = 0; j < integer.size(); ++j) {
				temp = temp + integer.get(j).toString() + " ";
			}
			temp = temp.trim() + System.lineSeparator();
		}
		for(int i = 0; i < numCols; ++i) {
			integer = projectCol(i);
			for(int j = 0; j < integer.size(); ++j) {
				temp = temp + integer.get(j).toString() + " ";
			}
			temp = temp.trim() + System.lineSeparator();
		}
		for(int i = 0; i < numRows; ++i) {
			for(int j = 0; j < numCols; ++j) {
				if(getCell(i, j)) {
					temp = temp + "1";
				}
				else {
					temp = temp + "0";
				}
			}
			temp = temp + System.lineSeparator();
		}
		return temp.trim();
		
	
	}

	
}
