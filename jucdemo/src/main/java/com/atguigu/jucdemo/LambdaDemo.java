package com.atguigu.jucdemo;


@FunctionalInterface
interface Foo{
    public int add(int x , int y);
    default int div(int x , int y){
        return x/y;
    }

    static int sub(int x,int y){
        return x-y;
    }
}

public class LambdaDemo {
    public static void main(String[] args) {
     /*   Foo foo = new Foo() {
            @Override
            public int add(int x, int y) {
                System.out.println("Hello");
                return x+y;
            }
        };*/

     Foo foo = (int x , int y)->{
         System.out.println("Hello");
         return x+y;
     };
        System.out.println(foo.add(10, 5));
        System.out.println(foo.div(10, 5));
        System.out.println(Foo.sub(10, 5));

    }




}
