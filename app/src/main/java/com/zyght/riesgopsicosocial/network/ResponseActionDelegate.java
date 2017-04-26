/*******************************************************************************
 * Copyright (c) 2014. Zyght
 * All rights reserved. 
 * 
 ******************************************************************************/
package com.zyght.riesgopsicosocial.network;

/**
 * 
 * 
 * @author Arley Mauricio Duarte Palomino
 * 
 */
public interface ResponseActionDelegate {
	void didSuccessfully(String message);

	void didNotSuccessfully(String message);
}
