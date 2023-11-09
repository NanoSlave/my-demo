package calc;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws Exception {
        //2+3
        //V-VII
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*" };
        Scanner scn = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String exp = scn.nextLine();
        //Определяем арифметическое действие:
        int actionIndex= -1;
        for (int i = 0; i < actions.length; i++) {
            if(exp.contains(actions[i])){
                actionIndex= i;
                break;
            }
        }
        //Если не нашли арифметического действия, завершаем программу
        if(actionIndex==-1){
            System.out.println("Некорректное выражение");
            return;
        }
        //Делим строчку по арифметическому знаку
        //2+3
        //I+X
        //"2+4", split("\\+")-> {"2"."4"}
        String[] data = exp.split(regexActions[actionIndex]);
        //Определяем, находятся ли числа в одном формате(оба римские или арабские)
        if(converter.isRoman(data[0]) == converter.isRoman(data[1])){
            int a,b;
            //Определяем, римские ли это числа
            boolean isRoman = converter.isRoman(data[0]);
            if(isRoman){
                //если римские, то конвертируем их в арабские
                //X+V
                //X-10
                //V - 5
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[1]);
            }else {
                //если арабские конвертируем их из строки в число
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[1]);
            }
            //выполянем с числами арифметическое действие
            int result;
            switch (actions[actionIndex]){
                default:
                    result = a/b;
                    break;
                case "*":
                    result = a*b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "+":
                    result = a+b;
                    break;
            }
            //15->XV
            if(isRoman){
                //если числа были римские, возвращаем результат в римском числе
                System.out.println(converter.intToRoman(result));
            }
            //если числа были арабские, возвращаем результат в арабском числе
            System.out.println(result);
        }else {
            System.out.println("Числа должны быть в одном формате");
        }
    }
}




