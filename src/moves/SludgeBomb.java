package moves;
import ru.ifmo.se.pokemon.*;

public final class SludgeBomb extends SpecialMove {
    public SludgeBomb() {
        super(Type.POISON, 90, 1.0);
    }
    @Override protected String describe() { return "использует Грязевую Бомбу"; }
    @Override protected void applyOppEffects(Pokemon p) {
        Effect e = new Effect().chance(0.3);
        if (e.success()) Effect.poison(p);
    }
}
