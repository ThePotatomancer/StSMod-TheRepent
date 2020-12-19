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

public class ArterialBleeding extends PowerConditionalCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(ArterialBleeding.class.getSimpleName());
    public static final String IMG = makeCardPath("ArterialBleeding.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;
    private static final String CONDITIONAL_POWER_ID = BleedPower.POWER_ID;

    private static final int COST = 2;

    private static final int DURATION = 2;
    private static final int UPGRADE_PLUS_DURATION = 1;

    // /STAT DECLARATION/


    public ArterialBleeding() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, CONDITIONAL_POWER_ID);
        magicNumber = baseMagicNumber = DURATION;
        exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new ApplyPowerAction(m, p, new ArterialBleedingPower(m, p, magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_DURATION);
            initializeDescription();
        }
    }
}
