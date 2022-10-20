package ru.otus.spring.spring15;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Spring15Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring15Application.class);
//        ConfigurableApplicationContext ctx = SpringApplication.run(Spring15Application.class, args);

/*
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext( Spring15Application.class );

        // here we works with cafe using interface
        Cafe cafe = ctx.getBean( Cafe.class );

        PublishSubscribeChannel publishSubscribeChannel= (PublishSubscribeChannel) ctx.getBean("foodChannel");
        publishSubscribeChannel.subscribe(Spring15Application::getMessage);

        Collection<OrderItem> items = generateOrderItems();
        // вызов endpoint
        Collection<String> foods = cafe.process( items );
        foods.forEach(food->{
            System.out.println(food);
        });
*/


        /*
        ForkJoinPool pool = ForkJoinPool.commonPool();
        while ( true ) {
            Thread.sleep( 7000 );

            pool.execute( () -> {
                Collection<OrderItem> items = generateOrderItems();
                System.out.println( "New orderItems: " +
                        items.stream().map( OrderItem::getItemName )
                                .collect( Collectors.joining( "," ) ) );
                Collection<Food> food = cafe.process( items );
                System.out.println( "Ready food: " + food.stream()
                        .map( Food::getName )
                        .collect( Collectors.joining( "," ) ) );
            } );
        }*/
    }
}
