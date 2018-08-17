package calculator.rpn;

import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;

@Data
public class NumberLiteralToken extends Token {

    private String literal;

    private Number numberObj;

    public NumberLiteralToken(int id, String literal){
        super(id);
        this.literal = literal;

    }

    public NumberLiteralToken(int id, Number number){
        super(id);
        this.numberObj = number;

    }

    public Number getNumber(){
        if (numberObj != null) {
            return numberObj;
        }

        if (NumberUtils.isDigits(literal)){
            return Long.valueOf(literal);
        }
        return Double.valueOf(literal);
    }

    @Override
    public String toString(){
        Number value = getNumber();
        if (value instanceof Double) {
            DecimalFormat decimalFormat = new DecimalFormat("0.##########");
            return decimalFormat.format(value);
        }else{
            return value.toString();
        }
    }

    @Override
    void accept(TokenVisitor visitor) {
        visitor.visit(this);
    }
}
