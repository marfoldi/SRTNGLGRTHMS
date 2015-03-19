package srtnglgrthms.model;

public class ShellSort extends SortingAlgorithm{
	private static int j = 1;
	int increment = SortingAlgorithm.getNumbers().length/2;
	int i = increment;
	int temp;
	
	private ShellSort() {}
	
	private static class SortHolder {
        private static final ShellSort INSTANCE = new ShellSort();
    }
	
    public static ShellSort getInstance() {
        return SortHolder.INSTANCE;
    }

	public void step() {
		if (increment > 0) {
			if(i < SortingAlgorithm.getNumbers().length) {
				j = i;
				int temp = data.get(i).getYValue();
				while (j >= increment && data.get(j-increment).getYValue() > temp) {
					data.get(j).setYValue(data.get(j-increment).getYValue());
					setColor(j, "navy");
					setColor(j-increment, "navy");
					j = j - increment;
				}
				data.get(j).setYValue(temp);
				i++;
			}
			else {
			if (increment == 2) {
				increment = 1;
				i=increment;
			} else {
				increment *= (5.0 / 11);
				i = increment;
			}
			step();
		}
		}
	}
}
