/* The knows API is defined in the parent class Relation.
      boolean knows(int a, int b); */
// 如果一个人知道别人，或者别人不知道他，那他一定不是名人
// if(knows(cand, other) || !knows(other, cand)){
// cand is not cele
public class Solution extends Relation {
    public int findCelebrity(int n) {
        //装进队列
        LinkedList<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++){
            q.addLast(i);
        }
        
        //排除法，让队列剩一个人
        while(q.size() >= 2){
            int cand = q.removeFirst();
            int other = q.removeFirst();
            if(knows(cand, other) || !knows(other, cand)){
                // cand is not cele
                // add other back to q
                q.addFirst(other);
            }else{
                // other is not cele
                // add cand back to q
                q.addFirst(cand);
            }
        }
        
        // 队列剩一个人，判断他是不是满足所有人都认识，但他不认识别人
        int last = q.getFirst();
        for(int i = 0; i < n; i++){
            if(i == last) continue; // 不要忘了特殊情况
            if(knows(last, i) || !knows(i, last)) return -1;
        }
        return last;
    }
}
