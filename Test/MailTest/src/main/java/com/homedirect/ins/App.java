package com.homedirect.ins;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Test Mail!" );
        System.out.println( "Starting...!" );
        System.out.println();
        MailService service = new MailService();
        //service.sendMail("noreply.baohiem@vndirect.com.vn", "quangbk2512@gmail.com", new String[]{}, "Hello!", "it's me!!");

        long now = System.currentTimeMillis();
        System.out.println();
        System.out.println( "=== Send Mail:" );
        service.sendMailDefaultFrom("quangbk2512@gmail.com", new String[]{}, "Hello!", "it's me!!");

        System.out.println();
        System.out.println();
        System.out.println( "-----------:" );
        System.out.println("time in milisecond: " + (System.currentTimeMillis() - now));
        System.out.println( "---END!!" );
    }
}
