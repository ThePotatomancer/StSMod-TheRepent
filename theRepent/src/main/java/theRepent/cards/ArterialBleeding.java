package theRepent.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theRepent.RepentMod;
import theRepent.characters.TheRepent;
import theRepent.powers.ArterialBleedingPower;
import theRepent.powers.BleedPower;

import static theRepent.RepentMod.makeCardPath;

public class ArterialBleeding extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(ArterialBleeding.class.getSimpleName());
    public static final String IMG = makeCardPath("ArterialBleeding.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int DURATION = 2;
    private static final int UPGRADE_PLUS_DURATION = 1;

    // /STAT DECLARATION/


    public ArterialBleeding() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DURATION;
        exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new ArterialBleedingPower(m, p, magicNumber)));
    }

    public boolean cardPlayable(AbstractMonster m) {
        if (m != null && m.hasPower(BleedPower.POWER_ID)) {
            return super.cardPlayable(m);
        }
        else {
            this.cantUseMessage = null;
            return false;
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean anyMonsterHasBleed = false;
        for(final AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            if (monster.hasPower(BleedPower.POWER_ID)) {
                anyMonsterHasBleed = true;
                break;
            }
        }

        if (anyMonsterHasBleed) {
            return super.canUse(p, m);
        }
        else {
            return false;
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DURATION);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
