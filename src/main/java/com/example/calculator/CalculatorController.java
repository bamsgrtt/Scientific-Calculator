package com.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

public class CalculatorController {

    @FXML
    private TextField display;


    @FXML
    public void initialize() {
        display.textProperty().addListener((observable, oldValue, newValue) -> {
            double textWidth = newValue.length() * 10;
            display.setPrefWidth(Math.max(textWidth, 200));
        });
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        String value = event.getText();
        if (!value.isEmpty()) {
            processInput(value);
        }

        switch (event.getCode()) {
            case ENTER:
                processInput("=");
                break;
            case BACK_SPACE:
                String currentText = display.getText();
                if (currentText.length() > 0) {
                    display.setText(currentText.substring(0, currentText.length() - 1));
                }
                break;
            default:
                break;
        }
    }

    @FXML
    public void handleButtonClick(javafx.event.ActionEvent event) {
        String value = ((Button) event.getSource()).getText();
        processInput(value);
    }

    private void processInput(String value) {
        if (value.equals("C")) {
            display.clear();
        } else if (value.equals("=")) {
            String result = evaluateExpression(display.getText());
            display.setText(result);
        } else {
            display.appendText(value);
        }
    }

    private String evaluateExpression(String expression) {
        try {
            expression = expression.replace("sin", "sin(");
            expression = expression.replace("cos", "cos(");
            expression = expression.replace("tan", "tan(");
            expression = expression.replace("^", "**");

            Expression exp = new ExpressionBuilder(expression)
                    .functions(
                            new Function("sin", 1) {
                                @Override
                                public double apply(double... args) {
                                    return Math.sin(Math.toRadians(args[0]));
                                }
                            },
                            new Function("cos", 1) {
                                @Override
                                public double apply(double... args) {
                                    return Math.cos(Math.toRadians(args[0]));
                                }
                            },
                            new Function("tan", 1) {
                                @Override
                                public double apply(double... args) {
                                    return Math.tan(Math.toRadians(args[0]));
                                }
                            }
                    )
                    .build();

            double result = exp.evaluate();
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }
}