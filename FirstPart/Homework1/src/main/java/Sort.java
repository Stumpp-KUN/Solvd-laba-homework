public class Sort {
    public static void main(String[] args) {
        int[] array={4,123,41,634,-32};

        for(int i=0;i<array.length;i++)
            System.out.print(array[i]+" ");
        sort(array);

        System.out.println();

        for(int i=0;i<array.length;i++)
            System.out.print(array[i]+" ");
    }

    public static void sort(int[] array){
        int n=array.length;

        if(n==1) return;

        int mid=n/2;
        int[] l=new int[mid];
        int[] r=new int[n-mid];

        for(int i=0;i<mid;i++)
            l[i]=array[i];
        for(int i=mid;i<n;i++)
            r[i-mid]=array[i];

        sort(l);
        sort(r);
        merge(array,l,r);
    }

    private static void merge(int[] array,int[] l,int[] r){
        int left=l.length;
        int right=r.length;
        int id=0;
        int i=0;
        int j=0;
        while(i<left&&j<right){
            if(l[i]<r[j]){
                array[id]=l[i];
                i++;
            }
            else{
                array[id]=r[j];
                j++;
            }
            id++;
        }
        for(int ll=i;ll<left;ll++)
            array[id++]=l[ll];
        for(int rr=j;rr<right;rr++)
            array[id++]=r[rr];
    }
}
