//  Key point: 
//      1. Binary operation; 
//      2. Convert the combinations of operations to an effective way.
class LC01702 {
    public String maximumBinaryString(String binary) {
        StringBuilder sBuilder = new StringBuilder(binary);

        return melt(sBuilder);
    }

    public String melt(StringBuilder sBuilder) {
        int index1 = sBuilder.indexOf("0");
        int index2 = sBuilder.indexOf("0", index1 + 1);

        // set 0111...110 -> 1011...111
        while (index2 != -1) {

            if (index1 + 1 == index2) {
                sBuilder.setCharAt(index1, '1');
            } else {

                sBuilder.setCharAt(index1, '1');
                sBuilder.setCharAt(index1 + 1, '0');
                sBuilder.setCharAt(index2, '1');
            }

            index1 = index1 + 1;
            index2 = sBuilder.indexOf("0", index2 + 1);
        }

        return sBuilder.toString();
    }
}

class Main_LC01702 {
    public static void main(String[] args) {
        LC01702 solution = new LC01702();
        String binary = "01111001100000110010";
        System.out.println(solution.maximumBinaryString(binary));
    }
}
