package org.example.satterdi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    static void main(){
        AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext();
                Employee emp = ctx.getBean(Employee.class);
                emp.displayAddress();
                ctx.close();
    }
}
