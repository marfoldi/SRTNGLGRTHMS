package srtnglgrthms.model;

import srtnglgrthms.controller.BarChartController;


public class InsertionSort extends SortingAlgorithm{
	private static int innerIndex = 1;
	private static int outerIndex = innerIndex-1;
	private static boolean firstStep = true;
	
	private InsertionSort() {}
	
	private static class SortHolder {
        private static final InsertionSort INSTANCE = new InsertionSort();
    }
	
    public static InsertionSort getInstance() {
        return SortHolder.INSTANCE;
    }
    
	public void setDefaults() {
		outerIndex = 0;
		innerIndex = 1;
		firstStep=true;
		counterData.clear();
		counterData.add(new Counter("ÖH", 0));
		counterData.add(new Counter("CS", 0));
	}

    @Override
	public void step() {
		if(firstStep){
			BarChartController.setColor(data.get(outerIndex+1).getNode(), "select");
			firstStep = false;
			return;
		}
		if (data.get(outerIndex).getYValue() <= data.get(outerIndex+1).getYValue()) {
			setRestColor("default");
			if(innerIndex<data.size()-1) innerIndex++;
			else {
				setRestColor("done");
				BarChartController.getAnimation().stop();
				return;
			}
			outerIndex=innerIndex-1;
			BarChartController.setColor(data.get(outerIndex+1).getNode(), "select");
			if (data.get(innerIndex-1).getYValue() < data.get(innerIndex).getYValue()) {
				counterData.get(0).incValue();
			}
		}
		else if (data.get(outerIndex).getYValue() > data.get(outerIndex+1).getYValue()) {
			counterData.get(0).incValue();
			swap(outerIndex+1, outerIndex);
			counterData.get(1).incValue();
			BarChartController.setColor(data.get(outerIndex+1).getNode(), "swap");
			BarChartController.setColor(data.get(outerIndex).getNode(), "select");
			if (outerIndex>0) outerIndex--;
		}
	}
	
	private void setRestColor(String color) {
		for (int i = 0; i < data.size(); i++) {
			BarChartController.setColor(data.get(i).getNode(), color);
		}
	}
}
