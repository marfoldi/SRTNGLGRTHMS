package srtnglgrthms.controller;

import srtnglgrthms.model.BubbleSort;
import srtnglgrthms.model.ForwardRadix;
import srtnglgrthms.model.InsertionSort;
import srtnglgrthms.model.QuickSort;
import srtnglgrthms.model.ShellSort;
import srtnglgrthms.model.SortingAlgorithm;

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
	      }
	      return null;
	}
}