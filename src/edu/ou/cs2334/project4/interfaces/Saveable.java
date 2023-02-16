package edu.ou.cs2334.project4.interfaces;

import java.io.FileNotFoundException;

/**
 * The Saveable interface is used to directly specify that a class has a special method to handle
 * saving a file. It allows the SaveHandler class to handle file saving.
 * 
 * @author Jared Rubio
 * @version 1.0
 * @since April 10, 2022
 */
public interface Saveable {

	/**
	 * This method is used to specify that a class has a special method to handle saving a file.
	 * @param filename is the string of the file's name.
	 * @throws FileNotFoundException is thrown if the file is not found.
	 */
	void save(String filename) throws FileNotFoundException;
}
