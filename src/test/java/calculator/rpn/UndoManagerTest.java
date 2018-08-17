package calculator.rpn;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
public class UndoManagerTest {

    @Test
    public void apply() {

        UndoManager undoManager = new UndoManager(new DefaultTokenStack());
        undoManager.apply(new NumberLiteralToken(1, "3"));
        undoManager.apply(new NumberLiteralToken(1, "3"));
        assertThat(undoManager.getTokens().size()).isEqualTo(2);
    }

    @Test
    public void applyEmpty() {
        UndoManager undoManager = new UndoManager(new DefaultTokenStack());
        undoManager.applyEmpty();
        assertThat(undoManager.getTokens().size()).isEqualTo(1);
    }

    @Test
    public void apply1() {
        UndoManager undoManager = new UndoManager(new DefaultTokenStack());
        undoManager.apply(new ArrayList<>());
        assertThat(undoManager.getTokens().size()).isEqualTo(1);
    }

    @Test
    public void undo() {
        UndoManager undoManager = new UndoManager(new DefaultTokenStack());
        undoManager.apply(new ArrayList<>());
        assertThat(undoManager.getTokens().size()).isEqualTo(1);
        undoManager.undo();
        assertThat(undoManager.getTokens().size()).isEqualTo(0);
    }
}