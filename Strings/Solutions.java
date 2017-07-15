public class Solution(){

    // MARK: Reverse a string
    public String reverseString(String str){
        if(str.length() <= 1) return str;
        
        int begin = 0;
        int end = str.length();

        char[] chars = str.toCharArray();   // String object is immutable

        while(begin < end){
            char temp = chars[begin];
            chars[begin] = chars[end];
            chars[end] = temp;

            begin++;
            end--;
        }

        return String.valueOf(chars);
    }

    // MARK: Roman to integer
    public int romanToInteger(String s){
        if(s.length == 0) return 0;

        int[] nums = new int[s.length()];
        int result = 0;

        for(int i = 0; i < s.length(); i++){
            switch(s.charAt(i)){
                case 'M' : nums[i] = 1000; break;
                case 'D': nums[i] = 500; break;
                case 'C': nums[i] = 100; break;
                case 'L': nums[i] = 50; break;
                case 'X': nums[i] = 10; break;
                case 'V': nums[i] = 5; break;
                case 'I': nums[i] = 1; break;
            }
        }

        for(int i = 0; i < s.length - 1; i++){
            if(nums[i] < nums[i + 1])
                result -= nums[i];
            else
                result += nums[i]; 
        }

        result += nums[nums.length - 1];

        return result;
    }

    // MARK: Integer to roman (Similar to integer to english)
    public integerToEnglish(int number){
        String space = " ";
        String[] thousands = {"", "one thousand", "two thousand", "three thousand", "four thousand"};   //! Arrays are defined with {}
        String[] hundreds = {"" , "one hundred, Two hundred, Three hundred", "Four hundred", "Five hundred", "Six hundred", "Seven hundred", "Eight hundred","Nine hundred"};
        String[] tens = {"", "ten", "twenty", "thirty", "fourty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        String[] teens = {"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] ones = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

        // Todo: Incorporate teens
        return thousands[number / 1000] + space + hundreds[(number / 100) % 10] + space + tens[number / 10) % 10] + space + ones[number % 10]; 
    }

    public integerToRoman(int number){
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return M[number / 1000] + C[(number % 1000) / 100] + X[(number % 100) /10] + I[number % 10];
    }

    // MARK: Reverse words in a string
    public String reverseWordsInAString(String s){
        if(s.length() == 0) return s;

        s = s.trim();

        s = reverse(s, 0, s.length() - 1);

        int left = 0;
        for(int right = 0; right < s.length(); right++){
            if(s.charAt(right) == ' '){
                s = reverse(string, left, right - 1);   //! Don't forget the - 1
                left = right + 1;
            }
        }
        
        //! Handle case if the sentence has only one word
        s = reverse(s, left, s.length() - 1);

        return s;
    }

    // MARK: Valid parenthesis
    public boolean validateParenthesis(String s){
        if(s.length() == 0) return true;
        else if(s.length() == 1) return false;

        Stack<Character> stack = new Stack<>();

        for(char c : s){
            if(c == '(')
                s.push(')');
            else if(c == ' {')
                s.push('}');
            else if(c == '[')
                s.push(']');

            else if(s.isEmpty() || c != s.pop())
                return false;
        }
    return stack.isEmpty(); // This condition checking is required if the 's' is like "( [ ] "

    // MARK: Longest common prefix of a set of strings
    public String longestCommonPrefix(String[] words){
        if(words.length == 0) return null;
        if(words.length == 1) return words[0];
        
        String prefix = words[0];
        for(int i = 1; i < word.length; i++){
            while(words[i].indexOf(prefix) != 0)
                prefix = prefix.substring(0, prefix.length() - 1);  //! substring(m, n) give the substring with m inclusive, but n exclusive.
        }

        return prefix;
    }

    // MARK: Group anagrams, or, Friendly words
    //* See: HashMaps

    // MARK: Letter combinations of a phone number
    public List<String> letterCombinations(String digits){
        String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};    //! Arrays are defined using {} braces.
        List<String> result = new LinkedList<>();                                               //! Use LinkedList for this problem
        
        result.add("");
        for(int i = 0; i < digits.length; i++){
            char digit = digits[i];

            while(result.peek().length() == i){
                String base = result.remove();
                for(char c : map[digit].toCharArray())
                    result.add(base + c);
            }
        }
        return result;
    }

    // MARK: Add two numbers represented as strings
    // First index is MSB. 
    // (Similar to add two LinkedLists)
    public String addBinary(String a, String b){
        StringBuilder sb = new StringBuilder();

        int sum = 0;
        int cur1 = a.length() - 1;
        int cur2 = b.length - 1;

        while(cur1 >= 0 || cur2 >=0){
            if(cur1 >= 0) sum += a.charAt(cur1) - '0';
            if(cur2 >= 0) sum += b.charAt(cur2) - '0';

            sb.append(sum % 10);
            sum /= 10;
            cur1--;
            cur2--;
        }
        if(sum == 1) sb.append("1");

        return sb.reverse().toString();     //! StringBuilder class has a reverse() function
    }

    // MARK: Reverse vowels in a string
    public String reverseVowels(String s){
        if(s.length == 0) return s;

        String vowels = "aeiouAEIOU";       // Or use a HashSet to store vowels
        char[] chars = s.toCharArray();

        int begin = 0;
        int end = s.length() - 1;

        while(begin < end){
            if(vowels.indexOf(s.charAt(begin)) == -1){      // First bring begin pointer to a vowel
                begin++;
                continue;                                   //! Use 'If' instead of while, and dont forget 'continue'
            }

            if(vowels.indexOf(s.charAt(end)) == -1){        // Next, bring end pointer on a vowel
                end--;
                continue;
            }

            char temp = chars[begin];                       // Swap only when both begin and end pointers are on a vowel
            chars[begin] = chars[end];
            chars[end] = temp;

            begin++;
            end--;
        }
        return String.valueOf(chars);
    }

    // MARK: Implement strStr()
    public int strStr(String needle, String haystack){
        int len = needle.length();

        for(int i = 0; i < haystack.length() - needle.length() + 1; i++){
            if(haystack.substring(i, i + needle.length()).equals(needle))
                return i;
        }
        return -1;
    }

}