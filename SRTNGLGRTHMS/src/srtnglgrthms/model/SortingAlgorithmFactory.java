package srtnglgrthms.model;


public class SortingAlgorithmFactory {
	public static SortingAlgorithm getAlgorithm(String algorithmName){
		switch(algorithmName) {
	      case "Buborékrendezés":
	    	  return BubbleSort.getInstance();
	      case "Beszúrórendezés":
	    	  return InsertionSort.getInstance();
	      case "Radix \"elõre\"":
	    	  return ForwardRadix.getInstance();
	      case "Gyorsrendezés":
	    	  return QuickSort.getInstance();
	      case "Shell rendezés":
	    	  return ShellSort.getInstance();
	      case "Kupac rendezés":
	    	  return HeapSort.getInstance();
	      case "Versenyrendezés":
	    	  return TournamentSort.getInstance();
	      }
	      return null;
	}
}