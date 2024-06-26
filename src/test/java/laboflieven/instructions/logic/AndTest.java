package laboflieven.instructions.logic;

import laboflieven.registers.TemplateRegister;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AndTest {

    @Test
    void andCheck() {

        TemplateRegister<Boolean> a = new TemplateRegister<>("A", true);
        TemplateRegister<Boolean> b = new TemplateRegister<>("B", false);
        var and = new And();
        and.setLeft(new RegisterFormula(a));
        and.setRight(new RegisterFormula(b));
        assertFalse(and.evaluate());
    }

    @Test
    void andCheckTrue() {
        TemplateRegister<Boolean> a = new TemplateRegister<>("A", true);
        TemplateRegister<Boolean> b = new TemplateRegister<>("B", true);

        var and = new And();
        and.setLeft(new RegisterFormula(a));
        and.setRight(new RegisterFormula(b));
                assertTrue(and.evaluate());
    }

}