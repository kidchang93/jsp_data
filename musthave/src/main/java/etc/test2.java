package etc;

import java.util.StringTokenizer;

public class test2 {

  public static void main(String[] args) {

    String keyword = "soccer,baseball,basketball"; // 구분자는 콤마(,)

    splitTest(keyword); // split 나누기

    System.out.println("                  ");
    System.out.println("                  ");

    stringTokenizerTest(keyword); //stringTokenizerTest 나누기
  }

  //stringTokenizerTest 메소드
  public static void stringTokenizerTest(String str){

    System.out.println("===== stringTokenizerTest() Start =====");
    StringTokenizer tokenizer = new StringTokenizer(str,",");

    for(int i = 0; tokenizer.hasMoreTokens(); i++){
      System.out.println(i + "번쨰 : " + tokenizer.nextToken());
    }

    System.out.println("===== stringTokenizerTest() End =====");

  }

  //splitTest 메소드
  public static void splitTest(String str){

    System.out.println("===== splitTest() Start =====");
    String split[] = str.split(",");

    int index = 0;
    for(String data : split){
      System.out.println(index + "번쨰 : " + data);
      index++;
    }

    System.out.println("===== splitTest() End =====");

  }

}
