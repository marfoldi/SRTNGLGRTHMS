package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.controller.OverviewDoubleChartController;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart.Data;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class BackwardRadix extends RadixAlgorithm {
	private static int index;
	private static int actualSeries;
	private static String direction;
	private static ObservableList<Data<String, Number>> listOne;
	private static ObservableList<Data<String, Number>> listTwo;

	private BackwardRadix() {
	}

	private static class SortHolder {
		private static final BackwardRadix INSTANCE = new BackwardRadix();
	}

	public static BackwardRadix getInstance() {
		return SortHolder.INSTANCE;
	}

	@Override
	public void setDefaults() {
		listOne = OverviewDoubleChartController.getListOne();
		listTwo = OverviewDoubleChartController.getListTwo();
		OverviewDoubleChartController.reloadLists();
		actualSeries = 0;
		actualDigit = getMaxDigit() - 1;
		begin = 0;
		index = 0;
		end = numbers.length - 1;
		lower = begin;
		upper = end;
		recursiveCall = new LinkedList<>();
		direction = "forward";
		counterData.clear();
		counterData.add(new CounterData("Vizsgálatok", "0"));
		counterData.add(new CounterData("Aktuálsi bit", Integer
				.toString(actualDigit + 1)));
	}

	@Override
	public void step() {
		if (actualDigit >= 0) {
			if (lower <= upper && index <= end && index >= begin
					&& begin <= end) {
				if (actualSeries % 2 == 0) {
					if (fillWithZeros(
							Integer.toBinaryString((int) listOne.get(index)
									.getYValue())).charAt(actualDigit) == '0') {
						listTwo.get(lower).setYValue(
								(Integer) listOne.get(index).getYValue());
						OverviewChartController.setColor(listTwo.get(lower)
								.getNode(), "swap");
						lower++;
					} else if (fillWithZeros(
							Integer.toBinaryString((int) listOne.get(index)
									.getYValue())).charAt(actualDigit) == '1') {
						listTwo.get(upper).setYValue(
								(Integer) listOne.get(index).getYValue());
						OverviewChartController.setColor(listTwo.get(upper)
								.getNode(), "select");
						upper--;
					}
					counterData.get(0).incValue();
					listOne.get(index).setYValue(0);
				} else {
					if (fillWithZeros(
							Integer.toBinaryString((int) listTwo.get(index)
									.getYValue())).charAt(actualDigit) == '0') {
						listOne.get(lower).setYValue(
								listTwo.get(index).getYValue());
						OverviewChartController.setColor(listOne.get(lower)
								.getNode(), "swap");
						lower++;
					}
					if (fillWithZeros(
							Integer.toBinaryString((int) listTwo.get(index)
									.getYValue())).charAt(actualDigit) == '1') {
						listOne.get(upper).setYValue(
								listTwo.get(index).getYValue());
						OverviewChartController.setColor(listOne.get(upper)
								.getNode(), "select");
						upper--;
					}
					counterData.get(0).incValue();
					listTwo.get(index).setYValue(0);
				}
				if (direction == "forward")
					index++;
				else
					index--;
			} else {
				if (recursiveCall.isEmpty()) {
					recursiveCall.add(new RecursiveParameter(0, lower - 1,
							actualDigit - 1, "forward"));
					recursiveCall.add(new RecursiveParameter(lower,
							numbers.length - 1, actualDigit - 1, "backward"));
				}
				if (!recursiveCall.isEmpty()) {
					RecursiveParameter nextParameters = recursiveCall.remove();
					direction = nextParameters.getStringParameter();
					if (direction == "forward") {
						actualSeries++;
						begin = 0;
						end = (int) nextParameters.getSecondParameter();
						lower = begin;
						upper = numbers.length - 1;
						actualDigit = (int) nextParameters.getThirdParameter();
						index = begin;
					} else {
						begin = (int) nextParameters.getFirstParameter();
						end = numbers.length - 1;
						actualDigit = (int) nextParameters.getThirdParameter();
						index = end;
					}
					if (actualDigit > -1)
						counterData.get(1).setValue(
								Integer.toString(actualDigit + 1));
					else
						counterData.get(1).setValue("1");
				}
			}
		} else {
			if (index <= end) {
				if (actualSeries % 2 == 0) {
					listTwo.get(index)
							.setYValue(listOne.get(index).getYValue());
					listOne.get(index).setYValue(0);
					OverviewChartController.setColor(listTwo.get(index)
							.getNode(), "done");
				} else {
					listOne.get(index)
							.setYValue(listTwo.get(index).getYValue());
					listTwo.get(index).setYValue(0);
					OverviewChartController.setColor(listOne.get(index)
							.getNode(), "done");
				}
				index++;
				return;
			}
			if (index > end && index < numbers.length) {
				if (actualSeries % 2 == 0) {
					listTwo.get(index)
							.setYValue(listOne.get(upper).getYValue());
					listOne.get(upper).setYValue(0);
					OverviewChartController.setColor(listTwo.get(index)
							.getNode(), "done");
				} else {
					listOne.get(index)
							.setYValue(listTwo.get(upper).getYValue());
					listTwo.get(upper).setYValue(0);
					OverviewChartController.setColor(listOne.get(index)
							.getNode(), "done");
				}
				index++;
				upper--;
				return;
			} else {
				for (int i = 0; i < numbers.length; ++i) {
					OverviewChartController.setColor(data.get(i).getNode(),
							"done");
				}
			}
		}
	}
}
