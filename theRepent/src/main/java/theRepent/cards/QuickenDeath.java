package theRepent.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theRepent.RepentMod;
import theRepent.actions.QuickenDeathAction;
import theRepent.characters.TheRepent;
import theRepent.powers.AcidPower;
import theRepent.powers.BleedPower;
import theRepent.powers.RedemptionPower;

import java.util.Arrays;

import static theRepent.RepentMod.makeCardPath;

public class QuickenDeath extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = RepentMod.makeID(QuickenDeath.class.getSimpleName());
    public static final String IMG = makeCardPath("QuickenDeath.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheRepent.Enums.COLOR_PURPLE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 2;

    private static final int TRIGGERS = 2;
    private static final int UPGRADE_PLUS_TRIGGERS = 1;

    // /STAT DECLARATION/


    public QuickenDeath() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = TRIGGERS;
        exhaust = true;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractDungeon.actionManager.addToBottom(
                    new QuickenDeathAction(m, p, Arrays.asList(BleedPower.POWER_ID,
                            AcidPower.POWER_ID,
                            PoisonPower.POWER_ID,
                            RedemptionPower.POWER_ID)));
        }
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_TRIGGERS);
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
