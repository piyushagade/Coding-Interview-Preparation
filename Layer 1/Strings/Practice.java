public class Practice{

    // MARK: Reverse words in a string
    public String reverseWordsInAString(String s){
        char[] chars = s.toCharArray();

        reverseString(chars, 0, chars.length - 1;

        int begin = 0;
        for(int end = 0; end < chars.length; end++){
            if(chars[end] == ' '){
                reverseString(chars, begin, end - 1);

                begin = end + 1;
            }
        }

        reverseString(chars, begin, chars.length - 1);

        return String.valueOf(chars);
    }

}