import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class ScriptEngineTest {

    @Test
    public void createExpression() {

        ScriptEngine scriptEngine = new ScriptEngine();

        Expression expression = scriptEngine.createExpression("1 2 3");
        assertThat(expression.getNodes().size() == 3);

    }
}