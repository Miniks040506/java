/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package varnfun;

/**
 *
 * @author Admin
 */
import java.util.Scanner;

public class VarNFun {

    
    public static void main(String[] args) {
        //playWithVariables();
        //playWithIntegers();
        //playWithDoubles();
        //playWithCharacters();
        //playWithBooleans();
        //printIntegerList();  
        
        int n = getAPositiveInteger();
        //System.out.println("Your number: " + n);
        printIntegerList(n); // truyền tham trị
                            // Pass by value
                            //OOP là pass by reference- truyền tham trị 
    }
    
    public static int getAPositiveInteger() {
        int n;
        Scanner myKeyboard = new Scanner (System.in);
        System.out.print("Input the positive integer: ");
        n = myKeyboard.nextInt();
        
        return n;
    }
    
    //in n số tự nhiên 
    //hiện tượng 1 class bất kì chứa nhiều hàm trùng tên nhưng khác phần tham số 
    //đầu vào, mà phải khác trên datatype chứ ko phải trên biến
    //quá tải hàm OVERLOADING
    //khác vs OVERRIDE
    //gọi là POLYMORPHISM --> tính đa hình
    public static void printIntegerList(int n) {
        System.out.println("The list of " + n + "first integers");
        for (int i =1; i <= n; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
        
    }
    
    //in ra số tự nhiên 1 .... 100
    public static void printIntegerList(long n) {
        System.out.println("The list of 100 first integers");
        //System.out.println("1 2 3 4 5 6 7 8 9 10 11 12 ...");
    
        for (int i = 1; i <= 100; i++) {
            System.out.print(i + " ");
        }
    
    }
   
    public static void playWithBooleans() {
        //C: đúng 1 or > 0; sai 0.
        //Java: đúng true, sai false --> boolean
        
        boolean marriedStatus = true;
        if (marriedStatus == true)
            System.out.println("Đánh đồn có định mới vui");
        else
            System.out.println("Vườn hồng có lối nhưng chưa ai vào");
    }
    
    public static void playWithCharacters() {
        //char chiếm 2 byte nên có thể chơi vs mọi kí tự trong Unicode
        char m = '$';
        System.out.println("unit: " + m);
        
        //chuỗi, xâu
        String name = "Nguyễn Hùng Cường";  //new data type
                                            //object data type 
                                            
        System.out.println("My name is: " + name);
        System.out.println("1st letter of my name is: " + name.charAt(0));
        System.out.println("My name in a lowercase is: " + name.toLowerCase());
        
    }
    
    public static void playWithDoubles() {
        double pi = 3.14;
        System.out.println("pi: " + pi);
        System.out.printf("pi: %.2f\n", pi); // ko dùng %lf
        
        float vat = 0.1F; //quy ước
        System.out.println("vat: " + vat);
          
    }
    
    
    public static void playWithIntegers() {
        int n = 39; //mọi con số xuất hiện trong code
                    //đc gọi là literal value
                    //mọi số nguyên xh trong code default là int
        System.out.println("n: " + n);
        
        long m = 3_000_000_000L; //mặc định là int, cần thêm L
                              //3 tỉ kiểu long
                              //_ phân cách nhưng ko in ra màn hình
        System.out.println("m: " + m);
        
        int status = 0xFA; //0x đằng trc hệ 16
        System.out.println("status: " + status);
        
        int phone = 010; //0 đằng trc hệ 8 octo
        System.out.println("phone: " + phone);
        
    }
    
    
    //static chỉ chơi với static - OOP
    public static void playWithVariables() {
        //biến là một vùng ram đc đặt tên chiếm 1 số byte nhất định
        //tuỳ loại giữa liệu hay thông tin mà nó chứa bên trong
        //biến là cách đặt tên cho 1 đại lượng, 1 giá trị đơn, phức
        
        int age = 20;
        int a = 10, b;
        b = 100;
        //tốn 12 byte trong RAM để lưu trữ những ON-OFF trong RAM
        System.out.println("age: " + age);
        System.out.println("a: " + a);
        System.out.println("b: " + b);
        int yob = 2006;
        age = 2024 - yob;
        System.out.println("age: " + age);
        
        //System.out.println("age: " + 2024 - yob);
        System.out.println("age: " + (2024 - yob));
        
        //like C (dùng printf)
        System.out.printf("age printed by using %% as in C: %d\n", (2024- yob)); 
         
    }
    
}
