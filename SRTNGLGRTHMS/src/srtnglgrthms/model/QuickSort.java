package srtnglgrthms.model;

public class QuickSort extends SortingAlgorithm {
	private static int left;
	private static int right;
	private static int i;
	private static int j;
	private static int pivot;
	
	private QuickSort() {
		init();
	}
	
	private static class SortHolder {
        private static final QuickSort INSTANCE = new QuickSort();
    }
	
    public static QuickSort getInstance() {
        return SortHolder.INSTANCE;
    }
    
    private static void init() {
    	left=0;
    	right = SortingAlgorithm.getNumbers().length-1;
    	pivot = data.get(left+(right-left)/2).getYValue();
    	i = left;
    	j = right;
    }
	
	public void step() {
        if (i <= j) {
            while (data.get(i).getYValue() < pivot) {
                i++;
            }
            while (data.get(j).getYValue() > pivot) {
                j--;
            }
            if (i <= j) {
                swap(i,j);
                i++;
                j--;
            }
        	}
        else {
        	if(left < j) {
        		i = left;
        		right = j;
        		j = right;
        		pivot = data.get(left+(right-left)/2).getYValue();
        	}
        	if(i < right)  {
        		left = i;
        		i = left;
        		j = right;
        		pivot = data.get(left+(right-left)/2).getYValue();
        }
        }
	}
}
