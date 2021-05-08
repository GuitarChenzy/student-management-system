package com.cogu.util;

import java.util.Vector;

import javax.servlet.http.HttpSession;

public class SessionManager {

	private static Vector<HttpSession> vector = new Vector<HttpSession>();

	public static Vector<HttpSession> getVector() {
		return vector;
	}

	/*public static void setVector(Vector<HttpSession> vector) {
		SessionManager.vector = vector;
	}*/

}
