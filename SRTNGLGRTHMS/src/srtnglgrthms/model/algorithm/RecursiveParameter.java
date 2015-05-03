package srtnglgrthms.model.algorithm;

/**
 * 
 * @author <a href="mailto:marfoldi@caesar.elte.hu">Márföldi Péter Bence</a>
 */
public class RecursiveParameter {
	private double firstParameter;
	private double secondParameter;
	private double thirdParameter;
	private String stringParameter;

	public RecursiveParameter(double firstParameter, double secondParameter,
			double thirdParameter, String stringParameter) {
		this.firstParameter = firstParameter;
		this.secondParameter = secondParameter;
		this.thirdParameter = thirdParameter;
		this.stringParameter = stringParameter;
	}

	public RecursiveParameter(double firstParameter, double secondParameter) {
		this(firstParameter, secondParameter, 0, null);
	}

	public RecursiveParameter(double firstParameter, double secondParameter,
			double thirdParameter) {
		this(firstParameter, secondParameter, thirdParameter, null);
	}

	public double getFirstParameter() {
		return firstParameter;
	}

	public double getSecondParameter() {
		return secondParameter;
	}

	public double getThirdParameter() {
		return thirdParameter;
	}

	public String getStringParameter() {
		return stringParameter;
	}
}
