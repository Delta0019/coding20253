import java.util.LinkedList;

class Solution {
    LinkedList<Integer> nums;
    LinkedList<Character> signals;
    char[] chars;

    public int calculate(String s) {
        nums = new LinkedList<>();
        signals = new LinkedList<>();
        chars = s.toCharArray();
        int len = s.length();
        boolean one_signal = true;
        for (int i = 0; i < len; ++i) {
            char c = chars[i];
            if (c == ' ') {
                continue;
            }
            if (Character.isDigit(c)) {
                int num = c - '0';
                one_signal = false;
                while (i + 1 < len && Character.isDigit(chars[i + 1])) {
                    ++i;
                    c = chars[i];
                    num *= 10;
                    num += c - '0';
                }
                nums.add(num);
                continue;
            }
            switch (c) {
                case '(':
                    one_signal = true;
                case '+', ')':
                    signals.add(c);
                    break;
                case '-':
                    if (one_signal) {
                        signals.add('-');
                    } else {
                        signals.add('/');
                        one_signal = false;
                    }
                    break;
                default:
                    System.err.println("Error");
                    break;
            }
        }

        return dealNext();
    }

    public int dealNext() {
        while (!signals.isEmpty() && !nums.isEmpty()) {
            char signal = signals.getFirst();
            int firstNum;
            int secondNum;
            boolean finish = false;
            switch (signal) {
                case '+':
                    signals.pop();
                    firstNum = nums.pop();
                    secondNum = dealBracket();
                    nums.addFirst(firstNum + secondNum);
                    break;
                case '-':
                    signals.pop();
                    firstNum = dealBracket();
                    nums.addFirst(-firstNum);
                    break;
                case '/':
                    signals.pop();
                    firstNum = nums.pop();
                    secondNum = dealBracket();
                    nums.addFirst(firstNum - secondNum);
                    break;
                case '(':
                    firstNum = dealBracket();
                    nums.addFirst(firstNum);
                    break;
                case ')':
                    finish = true;
                    break;
                default:
                    break;
            }
            if (finish) {
                break;
            }
        }
        return nums.pop();
    }

    public int dealBracket() {
        if (signals.isEmpty() || signals.getFirst() != '(') {
            return nums.removeFirst();
        }
        signals.removeFirst();
        int res = dealNext();
        signals.removeFirst();
        return res;
    }
}

class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "21";
        int res = solution.calculate(s);
        System.out.println(res);
    }
}
