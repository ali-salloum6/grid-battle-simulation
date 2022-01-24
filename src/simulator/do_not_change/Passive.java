package simulator.do_not_change;

public interface Passive {
	
	// Move away from ANY type of different symbol - within the sightDistance
	public abstract void escape();
	
	// Move closer to similar symbols - within the sightDistance
	public abstract void moveBreed();

}
