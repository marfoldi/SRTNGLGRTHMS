package srtnglgrthms.controller;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class ScreenSize {
	private static final Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();

	public static double getHeight() {
		return visualBounds.getHeight();
	}
	
	public static double getWidth() {
		return visualBounds.getWidth();
	}
}
