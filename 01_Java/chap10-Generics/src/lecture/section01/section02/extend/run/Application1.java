package lecture.section01.section02.extend.run;

import lecture.section01.section02.extend.Rabbit;
import lecture.section01.section02.extend.RabbitFarm;

public class Application1 {

    /*
    * extends 키워드를 사용하면 특정 타입만 사용하도록 제한, 타입의 자식클래스만 사용 가능
     */
    RabbitFarm<Rabbit> farm1 = new RabbitFarm<>();

}
