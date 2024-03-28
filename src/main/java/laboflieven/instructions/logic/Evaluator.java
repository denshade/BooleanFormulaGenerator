package laboflieven.instructions.logic;

import laboflieven.registers.TemplateRegister;

import java.util.List;
import java.util.Random;

public class Evaluator {
    private final List<TemplateRegister<Boolean>> registers;
    private final List<List<Boolean>> solutionsThatYieldTrue;
    private final List<List<Boolean>> solutionsThatYieldFalse;

    public Evaluator(List<TemplateRegister<Boolean>> registers, List<List<Boolean>> solutionsThatYieldTrue, List<List<Boolean>> solutionsThatYieldFalse) {
        this.registers = registers;
        this.solutionsThatYieldTrue = solutionsThatYieldTrue;
        this.solutionsThatYieldFalse = solutionsThatYieldFalse;
    }

    public boolean evaluate(Formula formula) {
        var r = new Random();
        var a = r.nextInt(3);
        if (a == 0) {
            System.out.println(formula);
        }
        if (!checkValuesThatYieldTrue(registers, solutionsThatYieldTrue, formula)) return false;
        if (!checkValuesThatYieldFalse(registers, solutionsThatYieldFalse, formula)) return false;
        return true;
    }

    private static boolean checkValuesThatYieldTrue(List<TemplateRegister<Boolean>> registers, List<List<Boolean>> solutionsThatYieldTrue, Formula form) {
        for (List<Boolean> scenario : solutionsThatYieldTrue) {
            fillScenarioInRegisters(registers, scenario);
            boolean trueVals = form.evaluate();
            if (!trueVals) {
                return false;
            }
        }
        return true;
    }


    private static boolean checkValuesThatYieldFalse(List<TemplateRegister<Boolean>> registers, List<List<Boolean>> solutionsThatYieldTrue, Formula form) {
        for (List<Boolean> scenario : solutionsThatYieldTrue) {
            fillScenarioInRegisters(registers, scenario);
            boolean falseVals = form.evaluate();

            if(falseVals) {
                return false;
            }
        }
        return true;
    }

    private static void fillScenarioInRegisters(List<TemplateRegister<Boolean>> registers, List<Boolean> scenario) {
        for (int i = 0; i < scenario.size(); i++) {
            if (scenario.size() != registers.size()) {
                throw new IllegalArgumentException("Scenario does not have the same size as the amount of registers " + scenario.size() + " " + registers.size());
            }
            registers.get(i).value = scenario.get(i);
        }
    }

}
