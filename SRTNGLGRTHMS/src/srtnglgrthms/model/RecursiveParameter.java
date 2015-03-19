package srtnglgrthms.model;

public class RecursiveParameter {
	private int begin;
	private int end;
	private int digit;
	private String dir;

	public RecursiveParameter(int begin, int end, int digit, String dir) {
		this.begin = begin;
		this.end = end;
		this.digit = digit;
		this.dir = dir;
	}
	
	public RecursiveParameter(int begin, int end) {
		this(begin, end, 0, null);
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public int getDigit() {
		return digit;
	}

	public String getDirection() {
		return dir;
	}
}
