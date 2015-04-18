package srtnglgrthms.model.algorithm;

import java.util.LinkedList;

import srtnglgrthms.controller.OverviewChartController;
import srtnglgrthms.model.CounterData;
import srtnglgrthms.model.RecursiveParameter;

public class QuickSort extends ChartAlgorithm {
	private static int begin;
	private static int end;
	private static int partitionIndex;
	private static int partitionHelpIndex;
	private static int pivot;
	private static int pivotIndex;
	private static boolean partitioned;
	
	private QuickSort() {}
	
	private static class SortHolder {
        private static final QuickSort INSTANCE = new QuickSort();
    }
	
    public static QuickSort getInstance() {
        return SortHolder.INSTANCE;
    }
    
    public void setDefaults() {
    	begin = 0;
    	end = numbers.length-1;
    	partitionIndex = begin;
    	partitionHelpIndex = begin;
    	partitioned = false;
    	pivotIndex = end;
    	pivot = data.get(pivotIndex).getYValue().intValue();
    	recursiveCall = new LinkedList<>();
		counterData.clear();
		counterData.add(new CounterData("Összehasonlítások", "0"));
		counterData.add(new CounterData("Mozgatások", "0"));
		counterData.add(new CounterData("Vezérelem", "t["+ Integer.toString(begin+(end-begin)/2)+ "]"));
	}
	
	public void step() {
		setRestColor(begin, end);
		OverviewChartController.setColor(data.get(pivotIndex).getNode(), "select");
		if(!partitioned) {
			if(partitionHelpIndex<end) {
				counterData.get(0).incValue();
				OverviewChartController.setColor(data.get(partitionIndex).getNode(), "swap");
				OverviewChartController.setColor(data.get(partitionHelpIndex).getNode(), "swap");
				if(data.get(partitionHelpIndex).getYValue().intValue()<=pivot) {
					counterData.get(1).incValue();
					if(partitionHelpIndex != partitionIndex) swap(partitionHelpIndex, partitionIndex);
					partitionIndex++;
				}
				partitionHelpIndex++;
				return;
			}
			counterData.get(1).incValue();
			swap(partitionIndex, end);
			OverviewChartController.setColor(data.get(partitionIndex).getNode(), "select");
			OverviewChartController.setColor(data.get(end).getNode(), "swap");
			pivotIndex = partitionIndex;
			partitioned = true;
		}
		else {
			if(begin<partitionIndex-1) recursiveCall.add(new RecursiveParameter(begin, partitionIndex-1));
			if(partitionIndex+1<end) recursiveCall.add(new RecursiveParameter(partitionIndex+1, end));
			if(!recursiveCall.isEmpty()) {
				RecursiveParameter nextParameters = recursiveCall.remove();
			    begin=(int) nextParameters.getFirstParameter();
			    end=(int) nextParameters.getSecondParameter();
			    partitioned=false;
			    partitionIndex=begin;
			    partitionHelpIndex=begin;
			    pivotIndex = end;
			    pivot = data.get(pivotIndex).getYValue().intValue();
		        }	
		}
	}
	
	private void setRestColor(int begin, int end) {
		for (int i = begin; i < end; i++) {
			OverviewChartController.setColor(data.get(i).getNode(), "default");
		}
		for(int i=0; i<begin-1; ++i) {
			OverviewChartController.setColor(data.get(i).getNode(), "done");
		}
		for(int i=end+1; i<data.size(); ++i) {
			OverviewChartController.setColor(data.get(i).getNode(), "done");
		}
	}
}