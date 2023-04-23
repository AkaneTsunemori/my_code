package code.hot.g;

import java.util.ArrayList;
import java.util.List;

public class a_49_22_generate_parenthesis {
    //    "()"->"(())","()()"-> (()()) ((()))  (()())  (())()   (())() ()(()) ()()() ()()()
    List<String> res = new ArrayList<>();

    public List<String> generateParenthesis(int n) {



        process(n, 0, 0, "");
        return res;

    }

    private void process(int n, int left, int right, String rc) {
        if (left == n && right == n) {
            res.add(rc);
        } else if (left == n) {
            process(n, left, right + 1, rc + ")");
        } else if (left < n && right < left) {
            process(n, left + 1, right, rc + "(");
            process(n, left, right + 1, rc + ")");
        } else if (left == right) {
            process(n, left + 1, right, rc + "(");
        }
    }





}
