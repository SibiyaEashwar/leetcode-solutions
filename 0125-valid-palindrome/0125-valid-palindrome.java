class Solution {
    public boolean isPalindrome(String s) {
        String res="";
        for(int i=0;i<s.length();i++)
        {
            char ch=Character.toLowerCase(s.charAt(i));
            if(Character.isLetterOrDigit(ch))
            {
                res=res+ch;
            }
        }
        String str="";
        for(int i=res.length()-1;i>=0;i--)
        {
            str=str+res.charAt(i);
        }
       return res.equals(str);
        
    }
}