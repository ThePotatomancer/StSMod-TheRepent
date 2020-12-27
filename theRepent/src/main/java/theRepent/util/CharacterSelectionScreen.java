package theRepent.util;

import basemod.BaseMod;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;

import java.util.ArrayList;

public class CharacterSelectionScreen {
    public static void updateCharacter(PlayerClass playerClass) {
        ArrayList<CharacterOption> options = CardCrawlGame.mainMenuScreen.charSelectScreen.options;
        int index = 0;
        for (CharacterOption option : options) {
            if (option.c.chosenClass.equals(playerClass)) {
                break;
            }
            index++;
        }
        if (index != options.size()) {
            AbstractPlayer character = CardCrawlGame.characterManager.getCharacter(playerClass);
            Hitbox oldCharacterOptionHb = options.get(index).hb;
            CharacterOption newCharacterOption = new CharacterOption(character.getLocalizedCharacterName(),
                    CardCrawlGame.characterManager.recreateCharacter(playerClass),
                    ImageMaster.loadImage(BaseMod.playerSelectButtonMap.get(playerClass)),
                    ImageMaster.loadImage(BaseMod.playerPortraitMap.get(playerClass)));
            newCharacterOption.hb.move(oldCharacterOptionHb.cX, oldCharacterOptionHb.cY);
            options.set(index, newCharacterOption);
        }
    }
}
