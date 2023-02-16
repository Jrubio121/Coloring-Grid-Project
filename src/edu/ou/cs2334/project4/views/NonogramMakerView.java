// Worked on this class with Keeton, John, and Guillermo 

package edu.ou.cs2334.project4.views;



import java.util.*;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * The NonogramMakerView class creates a border pane with the graphical interface for the Nonogram puzzle maker.
 * It is used to initialize the menu bar and all the menu items that lie within.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since April 10, 2022
 */
public class NonogramMakerView {

	private BorderPane borderpane;
	private MenuBar menubar;
	private CellGridView cellGridView;
	private HashMap<String, MenuItem> menuItemsMap;
	
	/**
	 * This is a static final string representing the menu open item
	 */
	public final static String MENU_ITEM_OPEN = "MENU_ITEM_OPEN";
	/**
	 * This is a static final string representing the menu save item
	 */
	public final static String MENU_ITEM_SAVE = "MENU_ITEM_SAVE";
	/**
	 * This is a static final string representing the menu exit item
	 */
	public final static String MENU_ITEM_EXIT = "MENU_ITEM_EXIT";
	
	/**
	 * This method constructs a NonogramMakerView given the number of rows and columns and the length of the cell
	 * @param numRows is the number of rows given.
	 * @param numCols is the number of columns given.
	 * @param cellLength is the length of the given cell.
	 */
	public NonogramMakerView(int numRows, int numCols, int cellLength) {
				
		menuItemsMap = new HashMap<String, MenuItem>();
		
		borderpane = new BorderPane();
		cellGridView = new CellGridView(numRows, numCols, cellLength);		
		
		initMenuBar();
		
		borderpane.setCenter(cellGridView.getPane());
		borderpane.setTop(menubar);
	
	}
	
	/**
	 * This method is used to initialize the menuBar and menuItemsMap, by creating a File Menu with MenuItems 
	 * Open, Save, and Exit.
	 */
	private void initMenuBar() {
		
		Menu menu = new Menu("_File");
		MenuItem open = new MenuItem("_Open");
		MenuItem save = new MenuItem("_Save");
		MenuItem exit = new MenuItem("_Exit");
		
		menuItemsMap.put(MENU_ITEM_OPEN, open);
		menuItemsMap.put(MENU_ITEM_SAVE, save);
		menuItemsMap.put(MENU_ITEM_EXIT, exit);
				
		exit.setOnAction((ActionEvent t) -> {Platform.exit();});
		
		menu.getItems().add(open);
		menu.getItems().add(save);
		menu.getItems().add(exit);

		
		menubar = new MenuBar();

		menubar.getMenus().add(menu);
	}
	
	/**
	 * 
	 * @param name is the string representation of the given key.
	 * @return The MenuItem associated with the given key string.
	 */
	public MenuItem getMenuItem(String name) {
				
		return menuItemsMap.get(name);
	}
	
	/**
	 * This method returns the pane associated with this view.
	 * @return The pane associated with this view.
	 */
	public Pane getPane(){
		return borderpane;
	}
	
	/**
	 * This method creates buttons according the the parameters using the cellGridView variable.
	 * @param numRows is the numbers of rows.
	 * @param numCols is the number of columns.
	 * @param cellLength is the length of the cell given.
	 */
	public void initButtons(int numRows, int numCols, int cellLength) {
		cellGridView.initButtons(numRows, numCols, cellLength);
	}
	
	/**
	 * This method takes a row and column index and returns the toggle button at that given position,
	 * in reference to the cellGridView variable.
	 * @param row is the index of the current row position
	 * @param col is the index of the current column position
	 * @return The toggleButton at a given position corresponding to the cellGridView variable
	 */
	public ToggleButton getToggleButton(int row, int col) {
		return cellGridView.getToggleButton(row, col); 
	}
	
	/**
	 * This method returns the number of rows using the cellGridView variable.
	 * @return the number of rows corresponding to the cellGridView variable.
	 */
	public int getNumRows() {
		return cellGridView.getnNumRows();
		
	}
	
	/**
	 * This method returns the number of columns using the cellGridView variable.
	 * @return the number of columns corresponding to the cellGridView variable.
	 */
	public int getNumCols() {
		return cellGridView.getNumCols();
	}
	
}
