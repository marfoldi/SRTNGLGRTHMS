package srtnglgrthms.model.algorithm;

import srtnglgrthms.controller.OverviewChartController;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BubbleSort extends ChartAlgorithm {
	private static int outerIndex;
	private static int innerIndex;

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
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Cserék", "0"));
	}

	@Override
	public void step() {
		if (innerIndex < data.size() - outerIndex) {
			if (outerIndex > 0) {
				OverviewChartController.setColor(
						data.get(data.size() - outerIndex - 1).getNode(),
						"default");
			}
			OverviewChartController.setColor(data.get(innerIndex - 1).getNode(),
					"swap");
			OverviewChartController.setColor(data.get(innerIndex).getNode(),
					"swap");
			counterData.get(0).incValue();
			if (data.get(innerIndex - 1).getYValue().intValue() > data
					.get(innerIndex).getYValue().intValue()) {
				swap(innerIndex - 1, innerIndex);
				OverviewChartController.setColor(data.get(innerIndex).getNode(),
						"select");
				counterData.get(1).incValue();
			}
			innerIndex++;
			if (innerIndex == data.size() - outerIndex) {
				OverviewChartController.setColor(data.get(innerIndex - 1)
						.getNode(), "done");
				setRestColor();
				innerIndex = 1;
				outerIndex++;
			}
			setRestColor();
		} else {
			OverviewChartController.setColor(data.get(0).getNode(), "done");
		}
	}

	private void setRestColor() {
		for (int i = 0; i < innerIndex - 2; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
	}
}
