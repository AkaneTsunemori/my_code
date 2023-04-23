package code.hot.g;

import java.util.ArrayList;
import java.util.List;

public class a_48_93_restore_ip_address {

    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        List<String> tempRes = new ArrayList<>();
        ipProcess(s, 0, tempRes);
        return res;
    }

    private void ipProcess(String s, int index, List<String> tempRes) {
        if (index == s.length() && tempRes.size() == 4) {
            String join = String.join(".", tempRes);
            res.add(join);
        } else if (tempRes.size() < 4) {
            {
                for (int i = index + 1; i <= s.length(); ++i) {
                    if ((i - index >= 2 && s.charAt(index) == '0') ) {
                        continue;
                    }
                    if(Integer.parseInt(s.substring(index, i)) > 255){
                        return;
                    }
                    tempRes.add(s.substring(index, i));
                    ipProcess(s, i, tempRes);
                    tempRes.remove(tempRes.size() - 1);
                }
            }
        }
    }
}
