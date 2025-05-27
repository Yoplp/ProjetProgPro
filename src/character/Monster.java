package character;

public class Monster extends Character {
	private boolean weakToMagic;
	private boolean weakToPhysical;

	public Monster(String name, int attack, int health, boolean weakToMagic, boolean weakToPhysical) {
	    super(name, attack, health);
	    this.weakToMagic = weakToMagic;
	    this.weakToPhysical = weakToPhysical;
	}
	
	public Monster(String name, int attack, int health) {
	    this(name, attack, health, false, false);
	}
	
	public boolean isWeakToMagic() {
	    return weakToMagic;
	}

	public boolean isWeakToPhysical() {
	    return weakToPhysical;
	}

    public void attaquer() {
        System.out.println(name + " vous attaque !");
    }
}
