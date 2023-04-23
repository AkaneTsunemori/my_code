package code.hot.e;

public class a_26_415_add_strings {
    public static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int l1 = num1.length()-1;
        int l2 = num2.length()-1;
        int ans = 0;
        int sum = 0;
        while(l1>=0&&l2>=0){
            sum = num1.charAt(l1)-'0'+num2.charAt(l2)-'0'+ans;
            ans = sum/10;
            sb.append(sum%10);
            l1--;
            l2--;
        }
        while(l1>=0){
            sum = num1.charAt(l1--)-'0'+ans;
            ans = sum/10;
            sb.append(sum%10);
        }
        while(l2>=0){
            sum = num2.charAt(l2--)-'0'+ans;
            ans = sum/10;
            sb.append(sum%10);
        }
        if(ans>0)sb.append(ans);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addStrings("1", "9"));
    }
}
