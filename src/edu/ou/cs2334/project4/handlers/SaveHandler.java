package edu.ou.cs2334.project4.handlers;

import java.io.File;
import java.io.FileNotFoundException;

import edu.ou.cs2334.project4.interfaces.Openable;
import edu.ou.cs2334.project4.interfaces.Saveable;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Window;

/**
 * The SaveHandler class is an extension of the AbstractBaseHandler class that implements a handle method 
 * used to handle the saving of a file.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since April 10, 2022
 */
public class SaveHandler extends AbstractBaseHandler implements EventHandler<ActionEvent> {
	
private Saveable saver;
	
	/**
	 * This method constructs a SaveHandler given a Window, FileChooser, and Saveable.
	 * @param window is the given Window.
	 * @param fileChooser is the given FileChooser
	 * @param saver is the given Saveable.
	 */
	public SaveHandler(Window window, FileChooser fileChooser, Saveable saver) {
		
		super(window, fileChooser);
		this.saver = saver; 
		
	}
	
	/**
	 * This method is used to handle saving a file and assigning it an ActionEvent.
	 * @param event is the given ActionEvent.
	 */
	public void handle (ActionEvent event) {
		
		File file = fileChooser.showSaveDialog(window);
		
		if(file != null) {
			try {
				
				saver.save(file.getAbsolutePath());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	
}
