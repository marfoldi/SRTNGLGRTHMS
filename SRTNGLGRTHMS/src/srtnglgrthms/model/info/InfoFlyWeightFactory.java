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
			case "Buborékrendezés":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/text/BubbleSortInfo.txt"))));
				break;
			case "Beszúró rendezés":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/text/InsertionSortInfo.txt"))));
				break;
			case "Radix \"elõre\"":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/text/ForwardRadixInfo.txt"))));
				break;
			case "Radix \"vissza\"":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/text/BackwardRadixInfo.txt"))));
				break;
			case "Gyorsrendezés":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/text/QuickSortInfo.txt"))));
				break;
			case "Shell rendezés":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/text/ShellSortInfo.txt"))));
				break;
			case "Kupacrendezés":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/text/HeapSortInfo.txt"))));
				break;
			case "Versenyrendezés":
				infoFlyWeight = new InfoFlyWeight(new String(Files.readAllBytes(Paths.get("resources/text/TournamentSortInfo.txt"))));
				break;
			}
			infoFlyWeights.put(algorithmName, infoFlyWeight);
		}
		return infoFlyWeight.getText();
	}
}
