import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {
  public static void main(String[] args) throws NumberFormatException {
    Scanner scanner = new Scanner(System.in);
    String string;
    boolean flag = true;

//Цикл выполняется до тех пор, пока пользователь не введет валидную строку
    while (flag == true){
      string = scanner.nextLine();
      if (validation(string)){
        unpackingString(string.toCharArray());
        flag = false;
      }else System.out.println("Неккоректный ввод");
    }
  }


  public static String unpackingString(char[] chArray) throws NumberFormatException{
    String returnString = "";
    int counter = 0;
    int indexOfBegin = 0;
    int multiplier = 0;

    for(int i = 0; i< chArray.length; i++){


      if(Character.isDigit(chArray[i]) && counter == 0){
        multiplier = Integer.parseInt(String.valueOf(chArray));
      }


      if(Character.isLetter(chArray[i]) && counter == 0){
        returnString = returnString + chArray[i];
      }

      if (chArray[i] == '['){
        counter++;
        if (counter == 1){
          indexOfBegin = i;
        }
      }

      if (chArray[i] == ']'){
        counter--;
        if (counter == 0){
          //Применение рекурсии
          returnString += unpackingString(Arrays.copyOfRange(chArray, indexOfBegin + 1, i)).repeat(multiplier);
          indexOfBegin = 0;
          multiplier = 0;
        }
      }
    }
    return returnString;
  }
// Валидация строки
  public static boolean validation(String string){
    int counter = 0;

    for (int i = 0; i<string.length();i++){
      if (Character.isDigit(string.toCharArray()[i]))
        if (string.toCharArray()[i+1]!='[') {
          return false;
        }

      if (string.toCharArray()[i] == '['){
        counter++;
      }

      if (string.toCharArray()[i] == ']') {
        counter--;
        if (counter<0) {
          return false;
        }
      }
    }

    if (counter!=0) {
      return false;
    }

    return (Pattern.matches("[a-zA-Z\\[\\]0-9]+",string));
  }
}

