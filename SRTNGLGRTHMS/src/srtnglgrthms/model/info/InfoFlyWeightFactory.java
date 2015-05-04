package srtnglgrthms.model.info;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class InfoFlyWeightFactory {
	private static final HashMap<String, InfoFlyWeight> infoFlyWeights = new HashMap<String, InfoFlyWeight>();

	public static String getInfo(String algorithmName) throws IOException {
		InfoFlyWeight infoFlyWeight = infoFlyWeights.get(algorithmName);

		if (infoFlyWeight == null) {
			switch (algorithmName) {
			case "Bubor�krendez�s":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/txt/BubbleSortInfo.txt"))));
				break;
			case "Besz�r� rendez�s":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/txt/InsertionSortInfo.txt"))));
				break;
			case "Radix \"el�re\"":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/txt/ForwardRadixInfo.txt"))));
				break;
			case "Radix \"vissza\"":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/txt/BackwardRadixInfo.txt"))));
				break;
			case "Gyorsrendez�s":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/txt/QuickSortInfo.txt"))));
				break;
			case "Shell rendez�s":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/txt/ShellSortInfo.txt"))));
				break;
			case "Kupacrendez�s":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/txt/HeapSortInfo.txt"))));
				break;
			case "Versenyrendez�s":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/txt/TournamentSortInfo.txt"))));
				break;
			}
			infoFlyWeights.put(algorithmName, infoFlyWeight);
		}
		return infoFlyWeight.getText();
	}
}
