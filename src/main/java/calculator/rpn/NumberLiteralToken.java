package calculator.rpn;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
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
        if (StringUtils.isNotEmpty(literal) && NumberUtils.isCreatable(literal)){
            return  toNumber();
        }
        throw new IllegalArgumentException(String.format(" %s is not a valid number", literal));
    }

    private Number toNumber() {
        Number value;
        try {
            value = Integer.valueOf(literal);
        } catch (NumberFormatException e) {
            try {
                value = Long.valueOf(literal);
            } catch (NumberFormatException e1) {
                value = Double.valueOf(literal);
            }
        }
        return value;
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
