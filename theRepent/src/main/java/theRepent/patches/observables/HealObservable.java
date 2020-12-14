package theRepent.patches.observables;

import com.megacrit.cardcrawl.core.AbstractCreature;

import java.util.ArrayList;
import java.util.function.Consumer;

public class HealObservable {
    private static final ArrayList<Consumer<HealEvent>> observers = new ArrayList<>();
    public HealObservable() {
    }

    public void subscribe(Consumer<HealEvent> observer) {
        observers.add(observer);
    }

    public void unsubscribe(Consumer<HealEvent> observer) {
        observers.remove(observer);
    }

    public void notify(AbstractCreature target, AbstractCreature source, int amount) {
        HealEvent event = new HealEvent(target, source, amount);
        observers.forEach(o -> o.accept(event));
    }
}
