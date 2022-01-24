package simulator.do_not_change;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class WorldController {

	public static final int MAX_ROWS = 10;
	public static final int MAX_COLS = 10;
	// Rock
	public static final char CAPITAL_R = 'R';
	public static final char SMALL_R = 'r';
	// Paper
	public static final char CAPITAL_P = 'P';
	public static final char SMALL_P = 'p';
	// Scissors
	public static final char CAPITAL_S = 'S';
	public static final char SMALL_S = 's';
	
	// Created to describe which symbols moved into the same position in the world.
	public static HashMap<Position, LinkedList<Symbol>> world;

	public abstract void symbolsMove(List<Symbol> symbols);

	public abstract void symbolsDie(List<Symbol> symbols);

	public abstract void smallCaseUpgrade(List<SmallCase> symbols);

	public abstract void capitalCaseJump(List<CapitalCase> symbols);

	public abstract void passiveEscape(List<Passive> symbols);

	public abstract void passiveBreed(List<Passive> symbols);
	
	public abstract void aggressiveAttackSmart(List<Aggressive> symbols);
	
	// Returns a 10x10 string with the symbols after the updates of the interation
	public abstract String plotWorld();

}
