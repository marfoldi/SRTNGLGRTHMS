package srtnglgrthms.model.algorithm;

import srtnglgrthms.controller.OverviewChartController;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BubbleSort extends ChartAlgorithm {
	private static int outerIdx = 0;
	private static int innerIdx = 1;

	private BubbleSort() {
	}

	private static class SortHolder {
		private static final BubbleSort INSTANCE = new BubbleSort();
	}

	public static BubbleSort getInstance() {
		return SortHolder.INSTANCE;
	}

	public void setDefaults() {
		outerIdx = 0;
		innerIdx = 1;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Cserék", "0"));
	}

	@Override
	public void step() {
		if (innerIdx < data.size() - outerIdx) {
			if (outerIdx > 0) {
				OverviewChartController.setColor(
						data.get(data.size() - outerIdx - 1).getNode(),
						"default");
			}
			OverviewChartController.setColor(data.get(innerIdx - 1).getNode(),
					"swap");
			OverviewChartController.setColor(data.get(innerIdx).getNode(),
					"swap");
			counterData.get(0).incValue();
			if (data.get(innerIdx - 1).getYValue().intValue() > data
					.get(innerIdx).getYValue().intValue()) {
				swap(innerIdx - 1, innerIdx);
				OverviewChartController.setColor(data.get(innerIdx).getNode(),
						"select");
				counterData.get(1).incValue();
			}
			innerIdx++;
			if (innerIdx == data.size() - outerIdx) {
				OverviewChartController.setColor(data.get(innerIdx - 1)
						.getNode(), "done");
				setRestColor();
				innerIdx = 1;
				outerIdx++;
			}
			setRestColor();
		} else {
			OverviewChartController.setColor(data.get(0).getNode(), "done");
		}
	}

	private void setRestColor() {
		for (int i = 0; i < innerIdx - 2; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
	}
}
