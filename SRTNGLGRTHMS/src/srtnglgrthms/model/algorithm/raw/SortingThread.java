package srtnglgrthms.model.algorithm.raw;

import srtnglgrthms.controller.SortingThreadListener;

/**
 *
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public abstract class SortingThread extends Thread {
	private SortingThreadListener listener;
	protected int[] numbers;
	protected long comparisonCounter = 0; // Increment this counter whenever a
	// comparison takes place
	protected long swapCounter = 0; // Increment this counter whenever a swap
									// takes
	// place
	protected long moveCounter = 0; // Increment this counter whenever a move
									// takes

	// place

	public final void setListener(final SortingThreadListener listener) {
		this.listener = listener;
	}

	private final void notifyListeners() {
		listener.notifyOfThreadComplete(this);
	}

	@Override
	public final void run() {
		try {
			doRun();
		} finally {
			notifyListeners();
		}
	}

	public abstract void doRun();
}