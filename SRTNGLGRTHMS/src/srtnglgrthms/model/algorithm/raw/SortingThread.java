package srtnglgrthms.model.algorithm.raw;

import java.util.ArrayList;
import java.util.List;

import srtnglgrthms.controller.SortingThreadListener;
import srtnglgrthms.model.BenchmarkData;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">M�rf�ldi P�ter Bence</a>
 */
public abstract class SortingThread extends Thread {
	private SortingThreadListener listener;
	protected static List<BenchmarkData> benchmarkData = new ArrayList<BenchmarkData>();
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

	public static List<BenchmarkData> getBenchmarkData() {
		return benchmarkData;
	}

	public abstract void doRun();
}