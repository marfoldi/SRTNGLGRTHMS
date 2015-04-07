package srtnglgrthms.model;


public class SortingAlgorithmFactory {
	public static SortingAlgorithm getAlgorithm(String algorithmName){
		switch(algorithmName) {
	      case "Bubor�krendez�s":
	    	  return BubbleSort.getInstance();
	      case "Besz�r�rendez�s":
	    	  return InsertionSort.getInstance();
	      case "Radix \"el�re\"":
	    	  return ForwardRadix.getInstance();
	      case "Gyorsrendez�s":
	    	  return QuickSort.getInstance();
	      case "Shell rendez�s":
	    	  return ShellSort.getInstance();
	      case "Kupac rendez�s":
	    	  return HeapSort.getInstance();
	      case "Versenyrendez�s":
	    	  return TournamentSort.getInstance();
	      }
	      return null;
	}
}