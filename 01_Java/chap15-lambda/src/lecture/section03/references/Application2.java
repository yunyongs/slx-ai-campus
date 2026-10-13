package lecture.section03.references;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Application2 {
    public static void main(String[] args) {
      Account account = new Account("yuny");

      Function<String, Account> function = (str) -> new Account(str);
      Function<String, Account> function2 = Account::new;

      Account account1 = function.apply("yuny");


    }
}
