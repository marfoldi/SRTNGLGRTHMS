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
	      }
	      return null;
	}
}