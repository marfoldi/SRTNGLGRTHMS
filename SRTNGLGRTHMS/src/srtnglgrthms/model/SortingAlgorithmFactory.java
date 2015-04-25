package srtnglgrthms.model;

import srtnglgrthms.model.algorithm.BackwardRadix;
import srtnglgrthms.model.algorithm.BubbleSort;
import srtnglgrthms.model.algorithm.ForwardRadix;
import srtnglgrthms.model.algorithm.HeapSort;
import srtnglgrthms.model.algorithm.InsertionSort;
import srtnglgrthms.model.algorithm.QuickShort;
import srtnglgrthms.model.algorithm.ShellSort;
import srtnglgrthms.model.algorithm.SortingAlgorithm;
import srtnglgrthms.model.algorithm.TournamentSort;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">M�rf�ldi P�ter Bence</a>
 */
public class SortingAlgorithmFactory {
	public static SortingAlgorithm getAlgorithm(String algorithmName) {
		switch (algorithmName) {
		case "Bubor�krendez�s":
			return BubbleSort.getInstance();
		case "Besz�r� rendez�s":
			return InsertionSort.getInstance();
		case "Radix \"el�re\"":
			return ForwardRadix.getInstance();
		case "Radix \"vissza\"":
			return BackwardRadix.getInstance();
		case "Gyorsrendez�s":
			return QuickShort.getInstance();
		case "Shell rendez�s":
			return ShellSort.getInstance();
		case "Kupacrendez�s":
			return HeapSort.getInstance();
		case "Versenyrendez�s":
			return TournamentSort.getInstance();
		}
		return null;
	}
}