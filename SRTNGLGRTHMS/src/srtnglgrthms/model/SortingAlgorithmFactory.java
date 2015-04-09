package srtnglgrthms.model;

import srtnglgrthms.model.algorithm.BackwardRadix;
import srtnglgrthms.model.algorithm.BubbleSort;
import srtnglgrthms.model.algorithm.ForwardRadix;
import srtnglgrthms.model.algorithm.HeapSort;
import srtnglgrthms.model.algorithm.InsertionSort;
import srtnglgrthms.model.algorithm.QuickSort;
import srtnglgrthms.model.algorithm.ShellSort;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.TournamentSort;


public class SortingAlgorithmFactory {
	public static SortingAlgorithm getAlgorithm(String algorithmName){
		switch(algorithmName) {
	      case "Bubor�krendez�s":
	    	  return BubbleSort.getInstance();
	      case "Besz�r�rendez�s":
	    	  return InsertionSort.getInstance();
	      case "Radix \"el�re\"":
	    	  return ForwardRadix.getInstance();
	      case "Radix \"vissza\"":
	    	  return BackwardRadix.getInstance();
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