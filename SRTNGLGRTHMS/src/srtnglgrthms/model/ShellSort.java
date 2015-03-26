package srtnglgrthms.model;

import srtnglgrthms.controller.OverviewChartController;

public class ShellSort extends SortingAlgorithm {
	private static int j;
	private static int gap;
	private static int i;
	
	private ShellSort() {
		init();
	}
	
	private static class SortHolder {
        private static final ShellSort INSTANCE = new ShellSort();
    }
	
    public static ShellSort getInstance() {
        return SortHolder.INSTANCE;
    }
    
    private static void init() {
    	j = 1;
    	gap = SortingAlgorithm.getNumbers().length/2;
    	i=gap;
    }

	public void step() {
		if (gap > 0) {
			if(i < SortingAlgorithm.getNumbers().length) {
				j = i;
				int temp = data.get(i).getYValue();
				while (j >= gap && data.get(j-gap).getYValue() > temp) {
					data.get(j).setYValue(data.get(j-gap).getYValue());
					OverviewChartController.setColor(data.get(j).getNode(), "swap");
					OverviewChartController.setColor(data.get(j-gap).getNode(), "swap");
					j = j - gap;
				}
				data.get(j).setYValue(temp);
				i++;
			}
			else {
			if (gap == 2) {
				gap = 1;
				i=gap;
				step();
			} else {
				gap *= (5.0 / 11);
				i = gap;
				step();
			}
			step();
		}
		}
	}

	@Override
	public void setDefaults() {
		// TODO Auto-generated method stub
		
	}
}
