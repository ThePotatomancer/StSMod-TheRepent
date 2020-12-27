package theRepent.util;

import com.megacrit.cardcrawl.blights.AbstractBlight;

public class Blights {
    public static void spawnBlightAndObtain(float x, float y, AbstractBlight blight) {
        blight.spawn(x, y);
        blight.obtain();
        blight.isObtained = true;
        blight.isAnimating = false;
        blight.isDone = false;
        blight.flash();
    }
}
