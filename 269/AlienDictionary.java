// 用于统计排在每种字母后面的所有字母
Map<Character, List<Character>> map = new HashMap<>();
// 拓扑排序用的访问数组
int[] visited = new int[26];
// 用于统计words中存在哪些字母
boolean[] has = new boolean[26];
public String alienOrder(String[] words) {
    // 统计words中存在哪些字母
    for(int i=0;i<words.length;i++){
        String current=words[i];
        for(int j=0;j<current.length();j++){
            has[current.charAt(j)-'a']=true;
        }
    }
    // 相邻2单词比较，统计排在每种字母后面的所有字母
    for(int i=1;i<words.length;i++){
        String pre = words[i-1];// 前单词
        String current=words[i];// 当前单词
        int index=0;// 单词下标
        
        // 比较2单词同一下标
        while(index<pre.length() && index<current.length()){
            // 前单词当前字符
            char p = pre.charAt(index);
            // 当前单词当前字符
            char c = current.charAt(index);
            // 2字符不同
            if(p!=c){
                // 将当前字母放入前字母的后续列表中
                List<Character> l=map.getOrDefault(p,new ArrayList<>());
                l.add(c);
                map.put(p, l);
                break;
            }
            index++;
        }
    }
    // 返回结果
    String res="";
    // 循环dfs每种字符
    for(int i=0;i<26;i++){
        // 如果该字母没有出现过，跳过
        if(!has[i]) continue;
        // 如果存在非法排序，返回空
        if(!dfs((char)(i+'a'))) return res;
    }
    // 因为拓扑排序是反向遍历，所以将结果倒序打印出来。
    for(int i=resList.size()-1;i>=0;i--){
        res+=resList.get(i);
    }
    return res;
}
List<Character> resList = new ArrayList<>();
// 拓扑排序（dfs）
boolean dfs(char c){
    if(visited[c-'a']==1) return false;
    if(visited[c-'a']==2) return true;
    visited[c-'a']=1;
    List<Character> list = map.get(c);
    if(list!=null) {
        for(Character next : list){
            if(!dfs(next)) return false;
        }
    }
    visited[c-'a']=2;
    resList.add(c);
    return true;
}
