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

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class SortingAlgorithmFactory {
	public static SortingAlgorithm getAlgorithm(String algorithmName) {
		switch (algorithmName) {
		case "Buborékrendezés":
			return BubbleSort.getInstance();
		case "Beszúró rendezés":
			return InsertionSort.getInstance();
		case "Radix \"elõre\"":
			return ForwardRadix.getInstance();
		case "Radix \"vissza\"":
			return BackwardRadix.getInstance();
		case "Gyorsrendezés":
			return QuickSort.getInstance();
		case "Shell rendezés":
			return ShellSort.getInstance();
		case "Kupacrendezés":
			return HeapSort.getInstance();
		case "Versenyrendezés":
			return TournamentSort.getInstance();
		}
		return null;
	}
}