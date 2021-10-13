/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {//注意children实际上是list！！！
        val = _val;
        children = _children;
    }
};
*/

class Codec {
    // Encodes a tree to a single string.
    //dfs
    public String serialize(Node root) {
        if(root==null) return "";
        StringBuilder str = new StringBuilder();
        str.append(root.val);
        if(root.children.isEmpty()) return str.toString();//难点，考虑没孩子的情况，children是list
        str.append("[");
        for(Node c: root.children){
            str.append(serialize(c));//难点，对孩子用函数名递归
            str.append(",");
        }
        str.deleteCharAt(str.length()-1);//删掉多出来的逗号
        str.append("]");
        return str.toString();
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        //特殊情况，注意string是isempty
        if(data.isEmpty()) return null;
        //找父母和孩子的分界点
        int index=data.indexOf("[");
        //如果没孩子,唯一情况是只有一个根结点
        if(index==-1) return new Node(Integer.parseInt(data),new ArrayList<>());
        //如果有孩子，要截出父母和孩子
        String val = data.substring(0,data.indexOf("["));//左包右不包
        Node root=new Node(Integer.parseInt(val),new ArrayList<>());
        //注意左包右不包，所以index是[，不能算。len-1是]不会包
        List<String> dataLst=parse(data.substring(index+1,data.length()-1);
        for(String c:dataLst){
            root.children.add(deserialize(c));//递归
        }
        return root;
    }
    
    // 解析形为"child_1,child_2...child_n"的字符串
    // 将其分为多个字符串，分别代表child_1,child_2...child_n
    private List<String> parse(String data){
        List<String> dataLst=new ArrayList<>();
        StringBuilder str=new StringBuilder();
        int bracketPair=0;
        for(char c: data.toCharArray()){
            if(c=="[") bracketPair++;//难点
            else if(c=="]") bracketPair--;
            else if(c==","){
                dataLst.add(str.toString());
                str.setLength(0);//清零重新存下一个字符串
                continue;//不让sb存逗号，但保留“[]”作为识别孩子的hint
            }
            str.append(str);//把每个字符组合成字符串存进去
        }
        return dataLst;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
