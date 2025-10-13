import ru.ifmo.se.pokemon.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Battle b = new Battle();
        Pokemon p1 = new Alomomola("Alomomola", 50);
        Pokemon p2 = new Bidoof("Bidoof", 30);
        Pokemon p3 = new Bibarel("Bibarel", 50);
        Pokemon p4 = new Oddish("Oddish", 25);
        Pokemon p5 = new Gloom("Gloom", 40);
        Pokemon p6 = new Bellossom("Bellossom", 55);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}

final class Alomomola extends Pokemon {
    public Alomomola(String name, int level) {
        super(name, level);
        setType(Type.WATER);
        setStats(165, 75, 80, 40, 45, 65); // Высокое HP как у Alomomola
        setMove(new ShadowBall(), new Refresh(), new WakeUpSlap(), new CalmMind());
    }
}


class Gloom extends Oddish {
    public Gloom(String name, int level) {
        super(name, level);
        setStats(60, 65, 70, 85, 75, 40);
        setMove(new SludgeBomb(), new DoubleTeam(), new PoisonPowder());
    }
}


class Oddish extends Pokemon {
    public Oddish(String name, int level) {
        super(name, level);
        setType(Type.GRASS, Type.POISON); // Два типа!
        setStats(45, 50, 55, 75, 65, 30);
        setMove(new SludgeBomb(), new DoubleTeam());
    }
}

class Bidoof extends Pokemon {
    public Bidoof(String name, int level) {
        super(name, level);
        setType(Type.NORMAL);
        setStats(59, 45, 40, 35, 40, 31);
        setMove(new Amnesia(), new Rest(), new Crunch());
    }
}

final class Bibarel extends Bidoof {
    public Bibarel(String name, int level) {
        super(name, level);
        setType(Type.NORMAL, Type.WATER); // Два типа!
        setStats(79, 85, 60, 55, 60, 71);
        setMove(new Amnesia(), new Rest(), new Crunch(), new Bulldoze());
    }
}

final class Bellossom extends Gloom {
    public Bellossom(String name, int level) {
        super(name, level);
        setType(Type.GRASS); // Только один тип после эволюции!
        setStats(75, 80, 95, 90, 100, 50);
        setMove(new SludgeBomb(), new DoubleTeam(), new PoisonPowder(), new QuiverDance());
    }
}


// Физические аттаки
// Wake-Up Slap
final class WakeUpSlap extends PhysicalMove {
    public WakeUpSlap() {
        super(Type.FIGHTING, 70, 1.0);
    }
    @Override protected String describe() { return "использует Звонкую Пощечину"; }
}

// Crunch
final class Crunch extends PhysicalMove {
    public Crunch() {
        super(Type.DARK, 80, 1.0);
    }
    @Override protected String describe() { return "использует Хруст"; }
    @Override protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.2) p.setMod(Stat.DEFENSE, -1);
    }
}

// Bulldoze
final class Bulldoze extends PhysicalMove {
    public Bulldoze() {
        super(Type.GROUND, 60, 1.0);
    }
    @Override protected String describe() { return "использует Снос"; }
    @Override protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.SPEED, -1);
    }
}


// Специальные аттаки
// Shadow Ball
final class ShadowBall extends SpecialMove {
    public ShadowBall() {
        super(Type.GHOST, 80, 1.0);
    }
    @Override protected String describe() { return "использует Теневой Шар"; }
    @Override protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.2) p.setMod(Stat.SPECIAL_DEFENSE, -1);
    }
}

// Sludge Bomb
final class SludgeBomb extends SpecialMove {
    public SludgeBomb() {
        super(Type.POISON, 90, 1.0);
    }
    @Override protected String describe() { return "использует Грязевую Бомбу"; }
    @Override protected void applyOppEffects(Pokemon p) {
        if (Math.random() <= 0.3) Effect.poison(p);
    }
}

// Статусные аттаки
// Refresh
final class Refresh extends StatusMove {
    public Refresh() {
        super(Type.NORMAL, 0, 1.0);
    }
    @Override protected String describe() { return "очищается от статусов"; }
    @Override protected void applySelfEffects(Pokemon p) {
        p.restore(); // Снимает все статусы
    }
}

// Calm Mind
final class CalmMind extends StatusMove {
    public CalmMind() {
        super(Type.PSYCHIC, 0, 1.0);
    }
    @Override protected String describe() { return "успокаивает разум"; }
    @Override protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.SPECIAL_ATTACK, 1);
        p.setMod(Stat.SPECIAL_DEFENSE, 1);
    }
}

// Amnesia
final class Amnesia extends StatusMove {
    public Amnesia() {
        super(Type.PSYCHIC, 0, 1.0);
    }
    @Override protected String describe() { return "впадает в амнезию"; }
    @Override protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.SPECIAL_DEFENSE, 2);
    }
}

// Double Team
final class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL, 0, 1.0);
    }
    @Override protected String describe() { return "создает двойников"; }
    @Override protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.EVASION, 1);
    }
}

// Poison Powder
final class PoisonPowder extends StatusMove {
    public PoisonPowder() {
        super(Type.POISON, 0, 0.75);
    }
    @Override protected String describe() { return "распыляет ядовитый порошок"; }
    @Override protected void applyOppEffects(Pokemon p) {
        Effect.poison(p);
    }
}

// Quiver Dance
final class QuiverDance extends StatusMove {
    public QuiverDance() {
        super(Type.BUG, 0, 1.0);
    }
    @Override protected String describe() { return "исполняет танец стрекозы"; }
    @Override protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.SPECIAL_ATTACK, 1);
        p.setMod(Stat.SPECIAL_DEFENSE, 1);
        p.setMod(Stat.SPEED, 1);
    }
}

final class Rest extends StatusMove {
    public Rest() {
        super(Type.PSYCHIC, 0, 1.0);
    }
    @Override protected String describe() { return "ложится отдыхать"; }
    @Override protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.HP, -(int)p.getStat(Stat.HP)); // Полное лечение
        Effect sleepEffect = new Effect().turns(2).condition(Status.SLEEP);
        p.setCondition(sleepEffect);
    }
}
