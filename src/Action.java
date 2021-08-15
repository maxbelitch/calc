import romanNum.RomanNum;

public class Action {
    private String task;
    private String answer;
    private boolean isArabicNums;
    private boolean isRomanNums;

    private int a;
    private int b;

    public Action(String task) {
        this.task = task;
    }

    public void go() {
        String[] numbersAndSign = task.split(" "); //По примерам задачи операнды и оператор должны быть разделены пробелом.

        if (check(numbersAndSign)) { // Если все проверки пройдены, то
            if (isArabicNums) { //Посчитай, если числа арабские
                getAnswerForArabicNums(numbersAndSign);
            }
            if (isRomanNums) { //Посчитай, если числа римские
                getAnswerForRomanNums(numbersAndSign);
            }
        }
    }

    public boolean check(String[] numbersAndSign) {
        if(task.equals("")){
            answer = "Вы ввели пустую строку.";
            return false;
        }
        if (numbersAndSign.length < 3) {
            answer = "Ошибка. Строка не является математической операцией";
            return false;
        }//Проверка является ли строка мат. операцией.

        if (numbersAndSign.length > 3) {
            answer = "Ошибка. Формат математической операции не удовлетворяет заданию - два операнда и один оператор";
            return false;
        }//Проверка количества операндов.

        {
            try {
                a = RomanNum.valueOf(numbersAndSign[0]).ordinal();
                b = RomanNum.valueOf(numbersAndSign[2]).ordinal();
                isRomanNums = true; //Если ошибки не произошло значит перед нами римские числа.
            } catch (Exception e) {
                isRomanNums = false; //Если NumberFormatException значит не римская система.
            }

            try {
                a = Integer.parseInt(numbersAndSign[0]);
                b = Integer.parseInt(numbersAndSign[2]);
                isArabicNums = true; //Если ошибки не произошло значит перед нами арабские числа.
            } catch (Exception e) {
                isArabicNums = false; //Если NumberFormatException значит не числа.
            }

            if (!isArabicNums && !isRomanNums) { //Если не римская и арабская значит разные сисиемы счисления.
                answer = "Ошибка. Используются одновременно разные системы счисления.";
                return false;
            }
        } //Узнаем, какие у нас цифры.

        return true; //Все хорошо, мы точно знаем какая у нас система счисления, все условия соблюдены.
    }

    public void getAnswerForArabicNums(String[] numbersAndSign) {
        switch (numbersAndSign[1]) {
            case "+":
                sum(a, b);
                break;

            case "-":
                difference(a, b);
                break;

            case "/":
                div(a, b);
                break;

            case "*":
                multiply(a, b);
                break;
            default:
                answer = "Ошибка. Неизвестный знак оператора";

        }
    }

    public void getAnswerForRomanNums(String[] numbersAndSign) {
        switch (numbersAndSign[1]) {
            case "+":
                sumForRomanNums(a, b);
                break;

            case "-":
                differenceForRomanNums(a, b);
                break;

            case "/":
                divForRomanNums(a, b);
                break;

            case "*":
                multiplyForRomanNums(a, b);
                break;
            default:
                answer = "Ошибка. Неизвестный знак оператора";

        }
    }

    public void sum(int a, int b) {
        int sum = a + b;
        answer = String.valueOf(sum);
        if (a <= 0 || a > 10 || b <= 0 || b > 10)
            answer = "Ошибка. Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";
    }

    public void sumForRomanNums(int a, int b) {
        int sum = a + b;
        answer = String.valueOf(RomanNum.values()[sum]);
        if (a <= 0 || a > 10 || b <= 0 || b > 10)
            answer = "Ошибка. Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";
    }

    public void difference(int a, int b) {
        int difference = a - b;
        answer = String.valueOf(difference);
        if (a <= 0 || a > 10 || b <= 0 || b > 10)
            answer = "Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";
    }

    public void differenceForRomanNums(int a, int b) {
        int difference = a - b;
        answer = difference <= 0 ? "Ошибка. В римской системе счисления нет нуля и отрицательных чисел." : String.valueOf(RomanNum.values()[difference]);
        if (a <= 0 || a > 10 || b <= 0 || b > 10)
            answer = "Ошибка. Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";
    }

    public void divForRomanNums(int a, int b) {
        try {
            int div = a / b;
            answer = String.valueOf(RomanNum.values()[div]);

            if (a <= 0 || a > 10 || b <= 0 || b > 10)
                answer = "Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";

        } catch (ArithmeticException e) {
            answer = "Ошибка. Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";
        }

    }

    public void div(int a, int b) {
        try {
            int div = a / b;
            answer = String.valueOf(div);
            if (a <= 0 || a > 10 || b <= 0 || b > 10)
                answer = "Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";


        } catch (ArithmeticException e) {
            answer = "Ошибка. Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";
        }

    }

    public void multiplyForRomanNums(int a, int b) {
        int multiply = a * b;
        answer = String.valueOf(RomanNum.values()[multiply]);
        if (a <= 0 || a > 10 || b <= 0 || b > 10)
            answer = "Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";

    }

    public void multiply(int a, int b) {
        int multiply = a * b;
        answer = String.valueOf(multiply);
        if (a <= 0 || a > 10 || b <= 0 || b > 10)
            answer = "Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.";

    }

    @Override
    public String toString() {
        go();
        return answer;
    }

}
