package srtnglgrthms.model;

public class TournamentSort extends SortingAlgorithm {
	
	private TournamentSort() {}
	
	private static class SortHolder {
        private static final TournamentSort INSTANCE = new TournamentSort();
    }
	
    public static TournamentSort getInstance() {
        return SortHolder.INSTANCE;
    }
	
	@Override
	public void setDefaults() {
	}
	
	@Override
	public void step() {
	}
	
	
	public static Runnable sort = () -> {
	};
}