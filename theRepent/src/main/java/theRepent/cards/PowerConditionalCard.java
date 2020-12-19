package theRepent.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class PowerConditionalCard extends AbstractDynamicCard {
    private final String powerId;

    public PowerConditionalCard(final String id,
                                final String img,
                                final int cost,
                                final CardType type,
                                final CardColor color,
                                final CardRarity rarity,
                                final CardTarget target,
                                final String powerId) {
        super(id, img, cost, type, color, rarity, target);
        this.powerId = powerId;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean anyMonsterHasPower = false;
        String[] extendedDescriptions = languagePack.getCardStrings(cardID).EXTENDED_DESCRIPTION;
        for(final AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster.hasPower(powerId)) {
                anyMonsterHasPower = true;
                break;
            }
        }

        if (anyMonsterHasPower) {
            return super.canUse(p, m);
        }
        else if (extendedDescriptions != null && extendedDescriptions[0] != null) {
            cantUseMessage = languagePack.getCardStrings(cardID).EXTENDED_DESCRIPTION[0];
        }
        return false;
    }

    @Override
    public boolean cardPlayable(AbstractMonster m) {
        boolean result = super.cardPlayable(m);
        String[] extendedDescriptions = languagePack.getCardStrings(cardID).EXTENDED_DESCRIPTION;
        if (result) {
            if (target == CardTarget.SELF) {
                result = AbstractDungeon.player.hasPower(powerId);
            }
            else if (target == CardTarget.ENEMY) {
                result = m != null && m.hasPower(powerId);
            }

            if (!result && extendedDescriptions != null && extendedDescriptions[1] != null) {
                cantUseMessage = languagePack.getCardStrings(cardID).EXTENDED_DESCRIPTION[1];
            }
        }
        return result;
    }
}
