// Worked on this class with Keeton, John, and Guillermo 
// Plus additional support from people on discord

package edu.ou.cs2334.project4;

import java.util.ArrayList;
import java.util.List;

import edu.ou.cs2334.project4.presenters.NonogramMakerPresenter;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The Main class is an extension of the Application class in JavaFX used to create 
 * an application window and display the options menu along with the actual Nonogram puzzle maker.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since April 10, 2022
 */
public class Main extends Application {

	private static final int IDX_NUM_COLS = 1;
	private static final int IDX_NUM_ROWS = 0;
	private static final int IDX_CELL_SIZE = 2;


	/**
	 * This method is used to call the inherited launch method and launch the application.
	 * @param args is a String array of arguments.
	 */
	public static void main(String[] args) {
	
		launch(args);
	}

	/**
	 * This method is used to start the application by creating a Scene with NonogramMakerPresenter 
	 * and setting it on the primary stage.
	 * @param primaryStage is the Stage given.
	 */
	public void start(Stage primaryStage) throws Exception {

		List<String> para = new ArrayList<String>();
		
		
		para = getParameters().getUnnamed();
		int row = Integer.parseInt(para.get(IDX_NUM_ROWS));
		int col = Integer.parseInt(para.get(IDX_NUM_COLS));
		int cell = Integer.parseInt(para.get(IDX_CELL_SIZE));
				
		NonogramMakerPresenter temp = new NonogramMakerPresenter(row,col,cell);
		
		Scene scene = new Scene(temp.getPane());

		primaryStage.setScene(scene);
		scene.getStylesheets().add("/style.css");
		
		primaryStage.setTitle("Nonogram Maker");
		primaryStage.show();
	}
	

}
