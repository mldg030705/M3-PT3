ppackage com.maldonadofirstapp.maldonado_multipurpose_calc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.math.BigDecimal;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.text.DecimalFormat;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewDisplay;
    private StringBuilder equation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewDisplay = findViewById(R.id.textViewDisplay);

        equation = new StringBuilder();

        findViewById(R.id.buttonClear).setOnClickListener(this);
        findViewById(R.id.buttonBracketOpen).setOnClickListener(this);
        findViewById(R.id.buttonBracketClose).setOnClickListener(this);
        findViewById(R.id.buttonPower).setOnClickListener(this);
        findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
        findViewById(R.id.buttonSeven).setOnClickListener(this);
        findViewById(R.id.buttonEight).setOnClickListener(this);
        findViewById(R.id.buttonNine).setOnClickListener(this);
        findViewById(R.id.buttonDivision).setOnClickListener(this);
        findViewById(R.id.buttonSin).setOnClickListener(this);
        findViewById(R.id.buttonFour).setOnClickListener(this);
        findViewById(R.id.buttonFive).setOnClickListener(this);
        findViewById(R.id.buttonSix).setOnClickListener(this);
        findViewById(R.id.buttonMultiplication).setOnClickListener(this);
        findViewById(R.id.buttonCos).setOnClickListener(this);
        findViewById(R.id.buttonOne).setOnClickListener(this);
        findViewById(R.id.buttonTwo).setOnClickListener(this);
        findViewById(R.id.buttonThree).setOnClickListener(this);
        findViewById(R.id.buttonSubtraction).setOnClickListener(this);
        findViewById(R.id.buttonTan).setOnClickListener(this);
        findViewById(R.id.buttonZero).setOnClickListener(this);
        findViewById(R.id.buttonDecimal).setOnClickListener(this);
        findViewById(R.id.buttonEquals).setOnClickListener(this);
        findViewById(R.id.buttonAddition).setOnClickListener(this);
        findViewById(R.id.buttonLog).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String buttonText = button.getText().toString();

        switch (view.getId()) {
            case R.id.buttonClear:
                equation = new StringBuilder();
                break;
            case R.id.buttonEquals:
                calculateResult();
                break;
            case R.id.buttonSquareRoot:
                calculateSquareRoot();
                break;
            default:
                equation.append(buttonText);
                break;
        }

        textViewDisplay.setText(equation.toString());
    }

    private void calculateSquareRoot() {
        try {
            double value = Double.parseDouble(equation.toString());
            double result = Math.sqrt(value);
            equation = new StringBuilder(Double.toString(result));
        } catch (Exception e) {
            equation = new StringBuilder("Error");
        }
    }


    private void calculateResult() {
        try {
            Expression expression = new ExpressionBuilder(equation.toString()).build();
            double result = expression.evaluate();

            DecimalFormat decimalFormat = new DecimalFormat("#.##########");
            String formattedResult = decimalFormat.format(result);

            if (formattedResult.endsWith(".0")) {
                formattedResult = formattedResult.substring(0, formattedResult.length() - 2);
            }

            equation = new StringBuilder(formattedResult);
        } catch (Exception e) {
            equation = new StringBuilder("Error");
        }
    }


    private double eval(final String str) {
        return Double.parseDouble(new org.mozilla.javascript.ContextFactory().call(new org.mozilla.javascript.ContextAction() {
            @Override
            public Object run(org.mozilla.javascript.Context cx) {
                return cx.evaluateString(cx.initStandardObjects(), str, "<cmd>", 1, null);
            }
        }).toString());
    }
}