package srtnglgrthms.model;

import srtnglgrthms.controller.BarChartController;

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
	
	public void setDefaults() {
		outerIndex = 0;
		innerIndex = 1;
		counterData.clear();
		counterData.add(new Counter("ÖH", 0));
		counterData.add(new Counter("CS", 0));
	}

	@Override
	public void step() {
		if (innerIndex < data.size() - outerIndex) {
			if (outerIndex > 0) {
				BarChartController.setColor(data.get(data.size()-outerIndex-1).getNode(), "default");
			}
			BarChartController.setColor(data.get(innerIndex-1).getNode(), "swap");
			BarChartController.setColor(data.get(innerIndex).getNode(), "swap");
			counterData.get(0).incValue();
			if (data.get(innerIndex - 1).getYValue() > data.get(innerIndex).getYValue()) {
				swap(innerIndex - 1, innerIndex);
				counterData.get(1).incValue();
			}
			innerIndex++;
			if (innerIndex == data.size() - outerIndex) {
				BarChartController.setColor(data.get(innerIndex-1).getNode(), "done");
				setRestColor();
				innerIndex = 1;
				outerIndex++;
			}
			setRestColor();
		}
		else {
			BarChartController.setColor(data.get(0).getNode(), "done");
			BarChartController.getAnimation().stop();
		}
	}

	private void setRestColor() {
		for (int i = 0; i < innerIndex - 2; i++) {
			BarChartController.setColor(data.get(i).getNode(), "default");
		}
	}
}
