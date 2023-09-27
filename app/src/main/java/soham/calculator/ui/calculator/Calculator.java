package soham.calculator.ui.calculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import soham.calculator.R;
import soham.calculator.databinding.FragmentCalculatorBinding;
import soham.calculator.utils.CalculatorUtil;

public class Calculator extends Fragment {

    private FragmentCalculatorBinding binding;

    private CalculatorUtil mCalculator; // object of Calculator class
    private TextView mInputValue1TextView; // for TextView ID - input_value_1
    private TextView mInputValue2TextView; // for TextView ID - input_value_2
    private TextView mOperatorTextView; // for TextView ID - input_operation
    private TextView mFinalResultTextView; // for TextView ID - textView_result
    private TextView mCompleteOperation; // for TextView ID - complete_operation
    private double number_one; // first number
    private double number_two; // second number
    private String operation_string; // current operation
    private static int MAX_CHARACTERS = 10;

    private enum operator {
        ADD, SUB, MUL, DIV, MOD, ROOT, POW, NULL
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCalculatorBinding.inflate(inflater, container, false);
        binding.setFragmentCalculator(this);
        initVariables();
        return binding.getRoot();
    }

    public void initVariables() {
        mCalculator = new CalculatorUtil();
        mInputValue1TextView = this.binding.inputValue1; // first number
        mInputValue2TextView = this.binding.inputValue2; // second number
        mOperatorTextView = this.binding.inputOperation; //operation
        mFinalResultTextView = this.binding.textViewResult; // final result
        mCompleteOperation = this.binding.completeOperation; // string containing the numbers and the operation
        operation_string = operator.NULL.name();
    }


    //    method to check where to append the numbers (first or second number)
    private void selectTextViewToAppend(String number) {
        if (operation_string.equals(operator.NULL.name())) {
            if (mInputValue1TextView.getText().toString().contains(".")) {
                MAX_CHARACTERS++;
            }
            if (mInputValue1TextView.getText().length() < MAX_CHARACTERS) {
                mInputValue1TextView.append(number);
                MAX_CHARACTERS = 10;
            } else {
                MAX_CHARACTERS = 10;
                Toast.makeText(this.getContext(), "Cannot have more than 10 numbers", Toast.LENGTH_LONG).show();
            }
        } else {
            if (mInputValue1TextView.getText().toString().contains(".")) {
                MAX_CHARACTERS++;
            }
            if (mInputValue2TextView.getText().length() < MAX_CHARACTERS) {
                mInputValue2TextView.append(number);
                MAX_CHARACTERS = 10;
            } else {
                MAX_CHARACTERS = 10;
                Toast.makeText(this.getContext(), "Cannot have more than 10 numbers", Toast.LENGTH_LONG).show();
            }

        }
    }

    //            handle operations for numbers
    public void onNumClick(View view) {
        if (!mFinalResultTextView.getText().toString().equals("")) {
            onClearClick(view);
        }
        if (view.getId() == this.binding.buttonOne.getId()) {
            selectTextViewToAppend("1");
        } else if (view.getId() == this.binding.buttonTwo.getId()) {
            selectTextViewToAppend("2");
        } else if (view.getId() == this.binding.buttonThree.getId()) {
            selectTextViewToAppend("3");
        } else if (view.getId() == this.binding.buttonFour.getId()) {
            selectTextViewToAppend("4");
        } else if (view.getId() == this.binding.buttonFive.getId()) {
            selectTextViewToAppend("5");
        } else if (view.getId() == this.binding.buttonSix.getId()) {
            selectTextViewToAppend("6");
        } else if (view.getId() == this.binding.buttonSeven.getId()) {
            selectTextViewToAppend("7");
        } else if (view.getId() == this.binding.buttonEight.getId()) {
            selectTextViewToAppend("8");
        } else if (view.getId() == this.binding.buttonNine.getId()) {
            selectTextViewToAppend("9");
        } else if (view.getId() == this.binding.buttonZero.getId()) {
            selectTextViewToAppend("0");
        } else if (view.getId() == this.binding.buttonDot.getId()) {
            if (operation_string.equals(operator.NULL.name())) {
                if (mInputValue1TextView.getText().toString().contains(".")) {
                    Toast.makeText(this.getContext(), "Cannot have more than one decimal point in a number", Toast.LENGTH_LONG).show();
                } else {
                    mInputValue1TextView.append(".");
                }
            } else {
                if (mInputValue2TextView.getText().toString().contains(".")) {
                    Toast.makeText(this.getContext(), "Cannot have more than one decimal point in a number", Toast.LENGTH_LONG).show();
                } else {
                    mInputValue2TextView.append(".");
                }
            }
        }
    }

    //            handle operations for operators
    public void onOperatorClick(View view) {
        if (!mInputValue1TextView.getText().toString().equals("")) {
            if (view.getId() == this.binding.buttonPlus.getId()) {
                operation_string = operator.ADD.name();
                mOperatorTextView.setText("+");
            } else if (view.getId() == this.binding.buttonMinus.getId()) {
                operation_string = operator.SUB.name();
                mOperatorTextView.setText("-");
            } else if (view.getId() == this.binding.buttonMultiply.getId()) {
                operation_string = operator.MUL.name();
                mOperatorTextView.setText("x");
            } else if (view.getId() == this.binding.buttonDivide.getId()) {
                operation_string = operator.DIV.name();
                mOperatorTextView.setText("/");
            } else if (view.getId() == this.binding.buttonMod.getId()) {
                operation_string = operator.MOD.name();
                mOperatorTextView.setText("%");
            } else if (view.getId() == this.binding.buttonPow.getId()) {
                operation_string = operator.POW.name();
                mOperatorTextView.setText("^");
            } else if (view.getId() == this.binding.buttonRoot.getId()) {
                operation_string = operator.ROOT.name();
                mOperatorTextView.setText("√");
            } else {
                operation_string = operator.NULL.name();
            }

        }
        else if (view.getId() == this.binding.buttonRoot.getId()) {
            mInputValue1TextView.setText("1");
            operation_string = operator.ROOT.name();
            mOperatorTextView.setText("√");
        }
        else {
            Toast.makeText(this.getContext(), "Enter first number before operation", Toast.LENGTH_LONG).show();
        }
    }

    public void onEqualsClick(View view) {
//        handle equals click
        if (mInputValue1TextView.getText().toString().equals("") || mOperatorTextView.getText().toString().equals("") || mInputValue2TextView.getText().toString().equals("")) {
            Toast.makeText(this.getContext(), "Enter the numbers and the operation", Toast.LENGTH_LONG).show();
        } else {
            number_one = Double.parseDouble(mInputValue1TextView.getText().toString());
            number_two = Double.parseDouble(mInputValue2TextView.getText().toString());
            String operation;

            switch (operator.valueOf(operation_string)) {
                case ADD:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.addition(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_plus) + mInputValue2TextView.getText().toString();
                    break;
                case SUB:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.subtraction(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_minus) + mInputValue2TextView.getText().toString();
                    break;
                case MUL:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.multiplication(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_mul) + mInputValue2TextView.getText().toString();
                    break;
                case DIV:
                    try {
                        mFinalResultTextView.setText(String.valueOf(mCalculator.division(number_one, number_two)));
                        operation = mInputValue1TextView.getText().toString() + getString(R.string.button_div) + mInputValue2TextView.getText().toString();
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(this.getContext(), getString(R.string.error), Toast.LENGTH_LONG).show();
                        operation = "";
                        clearAll();
                    }
                    break;
                case MOD:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.modulus(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_mod) + mInputValue2TextView.getText().toString();
                    break;
                case ROOT:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.squareRoot(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_root) + mInputValue2TextView.getText().toString();
                    break;
                case POW:
                    mFinalResultTextView.setText(String.valueOf(mCalculator.power(number_one, number_two)));
                    operation = mInputValue1TextView.getText().toString() + getString(R.string.button_pow) + mInputValue2TextView.getText().toString();
                    break;
                case NULL:
                    mFinalResultTextView.setText(getString(R.string.error));
                    operation = "";
                    break;
                default:
                    operation = "";
                    break;
            }
            mCompleteOperation.setText(operation);
            clearAll();
        }
    }

    //    handle clear click
    public void onClearClick(View view) {
        clearAll();
        mFinalResultTextView.setText("");
        mCompleteOperation.setText("");
    }

    //    clearing most values (needed many times, so created a method to reduce code duplication
    public void clearAll() {
        mInputValue1TextView.setText("");
        mOperatorTextView.setText("");
        mInputValue2TextView.setText("");
        number_one = 0;
        number_two = 0;
        operation_string = operator.NULL.name();
    }

    //    handle backspace click (the ImageButton) in the layout
    public void onBackspaceClick(View view) {
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clearAll();
                return false;
            }
        });
        if (!mInputValue2TextView.getText().toString().equals("")) {
            backspaceImplementation(mInputValue2TextView);
        } else {
            if (!mOperatorTextView.getText().toString().equals("")) {
                backspaceImplementation(mOperatorTextView);
            } else {
                if (!mInputValue1TextView.getText().toString().equals("")) {
                    backspaceImplementation(mInputValue1TextView);
                }
            }
        }
    }

    private void backspaceImplementation(TextView view) {
        String backspace = view.getText().toString();
        backspace = backspace.substring(0, backspace.length() - 1);
        view.setText(backspace);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}