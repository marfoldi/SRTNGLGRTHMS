package srtnglgrthms.model.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import srtnglgrthms.controller.OverviewChartController;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class ShellSort extends ChartAlgorithm {
	private static Integer[] gapArray;
	private static int gapIndex;
	private static int outerIndex;
	private static int innerIndex;
	private static boolean isSelected;

	private ShellSort() {
	}

	private static class SortHolder {
		private static final ShellSort INSTANCE = new ShellSort();
	}

	public static ShellSort getInstance() {
		return SortHolder.INSTANCE;
	}

	public void setDefaults() {
		gapArray = generateGapArray();
		gapIndex = 0;
		outerIndex = gapArray[gapIndex] - 1;
		innerIndex = outerIndex - gapArray[gapIndex];
		isSelected = false;
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Mozgatások", "0"));
		counterData.add(new CounterData("Lépésköz", "0"));
	}
	
	private static Integer[] generateGapArray() {
		int last2ind = 0;
		int last3ind = 0; 
		List<Integer> pratt = new ArrayList<>();

		pratt.add(1);
		for (int i=1; i < numbers.length; ++i) { 
		if (pratt.get(last2ind)*2 < pratt.get(last3ind)*3) { 
			pratt.add(pratt.get(last2ind)*2); 
			last2ind++; 
		} 
		else if (pratt.get(last2ind)*2 > pratt.get(last3ind)*3) { 
			pratt.add(pratt.get(last3ind)*3); 
			last3ind++; 
		} 
		else { 
			pratt.add(pratt.get(last2ind)*2); 
			last2ind++; 
			last3ind++; 
		} 
		if(pratt.get(i)>=numbers.length) break;
		}
		Collections.reverse(pratt);
		pratt.remove(0);
		return pratt.toArray(new Integer[0]);
	}

	@Override
	public void step() {
		if (!isSelected) {
			setRestColor("default");
			if (outerIndex < data.size() - 1) {
				outerIndex++;
				OverviewChartController.setColor(
						data.get(outerIndex).getNode(), "select");
				innerIndex = outerIndex - gapArray[gapIndex];
			} else if (gapIndex < gapArray.length - 1) {
				gapIndex++;
				outerIndex = gapArray[gapIndex];
				innerIndex = outerIndex - gapArray[gapIndex];
				OverviewChartController.setColor(
						data.get(outerIndex).getNode(), "select");
			} else {
				setRestColor("done");
			}
			counterData.get(2).setValue(Integer.toString(gapArray[gapIndex]));
			isSelected = true;
			return;
		} else if (outerIndex < data.size()) {
			setRestColor("default");
			if (data.get(innerIndex + gapArray[gapIndex]).getYValue().intValue() >= data
					.get(innerIndex).getYValue().intValue()) {
				OverviewChartController.setColor(
						data.get(innerIndex).getNode(), "swap");
				OverviewChartController.setColor(
						data.get(innerIndex + gapArray[gapIndex]).getNode(),
						"select");
				isSelected = false;
			} else if (data.get(innerIndex + gapArray[gapIndex]).getYValue()
					.intValue() < data.get(innerIndex).getYValue().intValue()) {
				swap(innerIndex + gapArray[gapIndex], innerIndex);
				counterData.get(1).incValue();
				OverviewChartController.setColor(
						data.get(innerIndex).getNode(), "select");
				OverviewChartController.setColor(
						data.get(innerIndex + gapArray[gapIndex]).getNode(),
						"swap");
				if (innerIndex - gapArray[gapIndex] >= 0)
					innerIndex = innerIndex - gapArray[gapIndex];
				else
					isSelected = false;
			}
			counterData.get(0).incValue();
		} else
			isSelected = false;
	}

	private void setRestColor(String color) {
		for (int i = 0; i < data.size(); i++) {
			OverviewChartController.setColor(data.get(i).getNode(), color);
		}
	}
}
