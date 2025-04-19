class HW3163 {

    public String compressedString(String word) {
        String comp = "";

        char pre = word.charAt(0);
        int len = 1;
        for (int i = 1; i < word.length(); ++i) {
            char next = word.charAt(i);
            if (next == pre) {
                if (len == 9) {
                    comp = comp + "9" + pre;
                    len = 1;
                } else {
                    len++;
                }
            } else {
                comp = comp + Integer.toString(len) + pre;
                len = 1;
                pre = next;
            }
        }

        comp = comp + Integer.toString(len) + pre;

        return comp;
    }
}