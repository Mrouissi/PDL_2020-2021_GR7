package com.wikipediaMatrix;
 
/**
 * 
 * @author Groupe 5
 *
 */
public class ArticleInexistantException extends Exception { 
 
	String message; 
	 
	public ArticleInexistantException(String message) { 
		super(); 
		this.message = message; 
	} 
} 
