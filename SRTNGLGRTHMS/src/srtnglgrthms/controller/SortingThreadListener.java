package srtnglgrthms.controller;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public interface SortingThreadListener {
	void notifyOfThreadComplete(final Thread thread);
}