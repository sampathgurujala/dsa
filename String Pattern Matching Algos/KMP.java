public class KMP {
    //Refer Leetcode Problem 28. Find the Index of the First Occurrence in a String
    public int findStrPattern(char[] text, char[] pattern)  //  T=O(M+N) ,  S=O(N) where m=text.length, n = pattern.length
    {
        if(text.length<pattern.length || pattern.length<=0)
            return -1;

        int i=1,j=0;
        int prefix[]=new int[pattern.length];
        prefix[0]=0;
        // building Prefix Array where prefix[j] = length of common prefix and suffix for a sub-string starting from 0 to j-1 in pattern
        while(i<pattern.length)
        {

            if(pattern[i]==pattern[j])
            {
                prefix[i]=j+1;
                i++;
                j++;
            }
            else
            {  while(j>0 && pattern[j]!=pattern[i])
                j=prefix[j-1];
                if(j==0  && (pattern[i]!=pattern[j])){
                    prefix[i]=0;
                    i++;
                }

            }
        }
        i=0;
        j=0;
        //Pattern Checking
        while(i<text.length)
        {
            if(pattern[j]==text[i])
            {
                i++;
                j++;
                if(j==pattern.length)
                {
                    return i-j;
                }
            }
            else
            {
                while(j>0 && pattern[j]!=text[i])
                {
                    j=prefix[j-1];
                }
                if(j==0 && text[i]!=pattern[j])
                {
                    i++;
                }
            }
        }
        return  -1;
    }

    public static void main(String[] args)
    {
     KMP kmp = new KMP();
     char[] text= new String("abxabcabcaby").toCharArray();
     char[] pattern= new String("abcaby").toCharArray();
     int idx= kmp.findStrPattern(text,pattern);
     if(idx!=-1)
        System.out.println("Pattern found at : "+idx);
     else
         System.out.println("Pattern not found.");
    }
}