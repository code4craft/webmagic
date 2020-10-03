import java.util.Arrays;
 public class Main
{
    static int arra[] = new int[]{10, 20, 30, 40, 50, 60};
     
    static void rotate_array()
    {
       int a = arra[arra.length-1], i;
       for (i = arra.length-1; i > 0; i--)
          arra[i] = arra[i-1];
       arra[0] = a;
    }
     
   public static void main(String[] args) 
    {
        System.out.println("Original arraay:");
        System.out.println(Arrays.toString(arra));
         
        rotate_array();
         
        System.out.println("Rotated arraay:");
        System.out.println(Arrays.toString(arra));
    }
}
