package srtnglgrthms.model;

import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import javafx.scene.Node;

public class HeapSort {
	private static int j = 1;
	private static int i = 0;
	private static int total;
	 private static void swap(Integer[] arr, int a, int b)
	    {
		 	int tmp = arr[a];
	        arr[a] = arr[b];
	        arr[b] = tmp;
	    }

	    private static void heapify(Integer[] arr, int i)
	    {
	        int lft = i * 2;
	        int rgt = lft + 1;
	        int grt = i;

	        if (lft <= total && arr[lft].compareTo(arr[grt]) > 0) grt = lft;
	        if (rgt <= total && arr[rgt].compareTo(arr[grt]) > 0) grt = rgt;
	        if (grt != i) {
	            swap(arr, i, grt);
	            heapify(arr, grt);
	        }
	    }

	    public static void sort(Integer[] arr)
	    {
	        total = arr.length - 1;

	        for (int i = total / 2; i >= 0; i--)
	            heapify(arr, i);

	        for (int i = total; i > 0; i--) {
	            swap(arr, 0, i);
	            total--;
	            heapify(arr, 0);
	        }
	    }
	
	public static void HeapStep(ObservableList<XYChart.Data<String,Integer>> data) {
		int temp;
		for (int i = 0; i < data.size(); i++) {
			Node n = data.get(i).getNode();
			n.setStyle("-fx-bar-fill: orange;");
		}
		Node node = data.get(j - 1).getNode();
		node.setStyle("-fx-bar-fill: navy;");
		node = data.get(j).getNode();
		node.setStyle("-fx-bar-fill: navy;");
		if (data.get(j - 1).getYValue() > data.get(j)
				.getYValue()) {
			temp = data.get(j - 1).getYValue();
			data.get(j - 1)
					.setYValue(data.get(j).getYValue());
			data.get(j).setYValue(temp);
		}
		j++;
		if (j == data.size()-i) {
			j = 1;
			i++;
		}
	}
}
