package srtnglgrthms.model;

public class BubbleSort extends SortingAlgorithm {
	private static int outerIndex = 0;
	private static int innerIndex = 1;

	private BubbleSort() {
	}

	private static class SortHolder {
		private static final BubbleSort INSTANCE = new BubbleSort();
	}

	public static BubbleSort getInstance() {
		return SortHolder.INSTANCE;
	}

	public void step() {
		if (innerIndex < data.size() - outerIndex) {
			if (outerIndex > 0) {
				setColor(data.size() - outerIndex - 1, "default");
			}
			setColor(innerIndex - 1, "swap");
			setColor(innerIndex, "swap");
			if (data.get(innerIndex - 1).getYValue() > data.get(innerIndex).getYValue()) {
				swap(innerIndex - 1, innerIndex);
			}
			innerIndex++;
			if (innerIndex == data.size() - outerIndex) {
				setColor(innerIndex - 1, "done");
				setRestColor();
				innerIndex = 1;
				outerIndex++;
			}
			setRestColor();
		}
		else {
			setColor(0, "done");
		}
	}

	private void setRestColor() {
		for (int i = 0; i < innerIndex - 2; i++) {
			setColor(i, "default");
		}
	}
}
